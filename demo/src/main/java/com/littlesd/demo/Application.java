package com.littlesd.demo;

import com.littlesd.demo.feign.support.FeignClient;
import com.littlesd.demo.feign.support.FeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@FeignClients({
        @FeignClient(
                name = "github", scanPackages = {
                "com.littlesd.demo.feign.github"
        })
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
