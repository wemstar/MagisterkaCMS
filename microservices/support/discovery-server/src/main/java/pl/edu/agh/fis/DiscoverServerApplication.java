package pl.edu.agh.fis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@EnableEurekaServer
@SpringBootApplication
public class DiscoverServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoverServerApplication.class, args);
	}
}
