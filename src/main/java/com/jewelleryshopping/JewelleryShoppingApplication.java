package com.jewelleryshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.jewelleryshopping.entities")
@ComponentScan(basePackages="com.jewelleryshopping.*")
@EnableJpaRepositories("com.jewelleryshopping.repositories")
public class JewelleryShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(JewelleryShoppingApplication.class, args);
	}

}
