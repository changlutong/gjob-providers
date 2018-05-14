package com.jk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan("com.jk.mapper")
@ImportResource("classpath:spring-dubbo-provider.xml")
public class GjobProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(GjobProviderApplication.class, args);
	}
}
