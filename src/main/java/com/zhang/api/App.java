package com.zhang.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@MapperScan(value = "com.zhang")
@EnableSwagger2
public class App {
	public static void main( String[] args )
	{
		SpringApplication.run(App.class, args);
	}
	/*@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
 
            System.out.println("Let's inspect the beans provided by Spring Boot:");
 
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
 
        };
    }*/
}
