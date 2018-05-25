package com.jk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableCaching
@MapperScan("com.jk.mapper")
@ImportResource("classpath:spring-dubbo-provider.xml")
public class GjobProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(GjobProviderApplication.class, args);
	}
}
