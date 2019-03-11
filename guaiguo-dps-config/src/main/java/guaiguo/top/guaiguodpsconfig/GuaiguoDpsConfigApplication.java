package guaiguo.top.guaiguodpsconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
@EnableEurekaClient
public class GuaiguoDpsConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuaiguoDpsConfigApplication.class, args);
    }

}
