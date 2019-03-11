package top.guaiguo.guaiguodpseureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GuaiguoDpsEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuaiguoDpsEurekaApplication.class, args);
    }

}
