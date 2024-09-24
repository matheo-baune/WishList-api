package com.baune.wishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.baune.wishlist")
public class ProjectWishListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectWishListApplication.class, args);
    }

}
