package net.test.jwtappdemo;

import net.test.jwtappdemo.rest.AuthenticationRestControllerV1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class JwtAppDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAppDemoApplication.class, args);
	}

}
