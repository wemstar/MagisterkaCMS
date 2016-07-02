package pl.edu.agh.fis;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RestController
@EnableCircuitBreaker
public class DocumentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentMicroserviceApplication.class, args);
	}

	@HystrixCommand(fallbackMethod = "test2")
	@RequestMapping("/hura")
	void test() {

	}

	void test2() {

	}

}
