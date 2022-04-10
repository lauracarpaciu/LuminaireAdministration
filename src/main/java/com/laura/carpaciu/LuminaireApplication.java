package com.laura.carpaciu;

//@SpringBootApplication is a convenience annotation that adds all of the following:
//@Configuration: Tags the class as a source of bean definitions for the application context.	
//@EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
//@ComponentScan: Tells Spring to look for other components, configurations, and services
import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.laura.carpaciu.config.FieldsValidationConfig;
import com.laura.carpaciu.config.PdfGeneratorConfig;


@EnableAsync
@Import({ SecurityConfig.class, PdfGeneratorConfig.class, FieldsValidationConfig.class })
@EnableAspectJAutoProxy
@ComponentScan
@SpringBootApplication(scanBasePackages = {
        "com.laura.carpaciu"
})
@EnableAutoConfiguration
@EnableJpaRepositories("com.laura.carpaciu.dao")
@EnableJpaAuditing
@ComponentScan(basePackages = { "com.laura.carpaciu.*" })
@EntityScan("com.lauracarpaciu.*")
public class LuminaireApplication {
	public static void main(String[] args) {
		SpringApplication.run(LuminaireApplication.class, args);
	}

}
