package com.kshrd.derphsar_api;


import com.google.common.collect.Lists;
import com.kshrd.derphsar_api.service.implement.FilesStorageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
//@ComponentScan("com.kshrd.*")
public class DerphsarApiApplication{

	@Autowired
	FilesStorageServiceImp filesStorageServiceImp;

	public static void main(String[] args) {
		SpringApplication.run(DerphsarApiApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("*");
//			}
//		};
//	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer(){
//		return addCorsMappings(registry)->{
//			registry.addMapping("/**").allowedOrigins("http://localhost:3000","http://104.197.139.95:1600")
//					.allowedMethods( "POST","DELETE", "OPTIONS", "PUT","GET","PATCH");
//		}
//	}
}
