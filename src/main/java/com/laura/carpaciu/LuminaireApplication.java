package com.laura.carpaciu;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.laura.carpaciu.config.FieldsValidationConfig;
import com.laura.carpaciu.config.PdfGeneratorConfig;

@SpringBootApplication
@EnableAsync
@Import({ SecurityConfig.class, PdfGeneratorConfig.class, FieldsValidationConfig.class })
@EnableAspectJAutoProxy
@EntityScan
@ComponentScan
public class LuminaireApplication {
	public static void main(String[] args) {
		SpringApplication.run(LuminaireApplication.class, args);
	}

}
