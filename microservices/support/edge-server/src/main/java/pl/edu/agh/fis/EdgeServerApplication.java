package pl.edu.agh.fis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@RequestMapping("/")
@EnableFeignClients
@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableTurbine
public class EdgeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgeServerApplication.class, args);
	}

	@Bean
	public ShaPasswordEncoder encoder() {
		return new ShaPasswordEncoder(256);
	}


	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void login(){}
}
