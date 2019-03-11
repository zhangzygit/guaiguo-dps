package top.guaiguo.springdps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringDpsWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDpsWebApplication.class, args);
	}
}
