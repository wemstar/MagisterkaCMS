package pl.edu.agh.fis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class SimpleMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleMicroServiceApplication.class, args);
	}

	@RequestMapping(value = "/hura",method = RequestMethod.GET)
	public String hura(){
		return "hura";
	}
}
