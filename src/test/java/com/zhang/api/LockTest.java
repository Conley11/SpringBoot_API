package com.zhang.api;

import com.zhang.api.service.LockService;

public class LockTest extends Thread {
    private LockService service;

    public LockTest(LockService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
    public static void main(String[] args) {
    	LockService service = new LockService();
    	/*for (int i = 0; i < 50; i++) {
    		LockTest threadA = new LockTest(service);
    		threadA.start();
    	}*/
    	service.test1();
    }
}

