package pl.edu.agh.fis;

import com.mongodb.Mongo;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class DocumentMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentMicroserviceApplication.class, args);
	}

}
