package com.laura.carpaciu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import({SecurityConfig.class, PdfGeneratorConfig.class, FieldsValidationConfig.class})
@EnableAspectJAutoProxy
@EntityScan
@ComponentScan
public class LuminaireApplication {
	 public static void main(String[] args) {
		    SpringApplication.run(LuminaireApplication.class, args);
		  }

}
