package com.zhang.api.web;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

@RestController
@RequestMapping("/redis")
public class RedisLockController {
	private String lock_key = "redis_lock"; //锁键

    protected long internalLockLeaseTime = 30000;//锁过期时间

    private long timeout = 999999; //获取锁的超时时间

    
    //SET命令的参数 
    SetParams params = SetParams.setParams().nx().px(internalLockLeaseTime);

    @Autowired
    JedisPool jedisPool;

    /**
     * 加锁
     * @param id
     * @return
     */
    public boolean lock(String id){
        Jedis jedis = jedisPool.getResource();
        Long start = System.currentTimeMillis();
        try{
            for(;;){
                //SET命令返回OK ，则证明获取锁成功
                String lock = jedis.set(lock_key, id, params);
                if("OK".equals(lock)){
                    return true;
                }
                //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                long l = System.currentTimeMillis() - start;
                if (l>=timeout) {
                    return false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            jedis.close();
        }
    }
    /**
     * 解锁
     * @param id
     * @return
     */
    public boolean unlock(String id){
        Jedis jedis = jedisPool.getResource();
        String script =
                "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                        "   return redis.call('del',KEYS[1]) " +
                        "else" +
                        "   return 0 " +
                        "end";
        try {
            Object result = jedis.eval(script, Collections.singletonList(lock_key), 
                                    Collections.singletonList(id));
            if("1".equals(result.toString())){
                return true;
            }
            return false;
        }finally {
            jedis.close();
        }
    }    
    int count = 0;
    
    @RequestMapping("/index")
    @ResponseBody
    public String index() throws InterruptedException {

        int clientcount =10;
        CountDownLatch countDownLatch = new CountDownLatch(clientcount);

        ExecutorService executorService = Executors.newFixedThreadPool(clientcount);
        long start = System.currentTimeMillis();
        for (int i = 0;i<clientcount;i++){
            executorService.execute(() -> {
            
                //通过Snowflake算法获取唯一的ID字符串
                String id = UUID.randomUUID().toString();
                try {
                    lock(id);
                    count++;
                }finally {
                    unlock(id);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("执行线程数:{"+clientcount+"},总耗时:{"+(end-start)+"},count数为:{"+count+"}");
        return "Hello";
    }
}