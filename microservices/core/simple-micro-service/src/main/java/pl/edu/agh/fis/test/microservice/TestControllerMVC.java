package pl.edu.agh.fis.test.microservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wemstar on 2016-02-08.
 */
@EnableCircuitBreaker
@RestController
public class TestControllerMVC {

    @RequestMapping(path = "/test")
    @HystrixCommand(fallbackMethod = "testFail")
    public String test() {
        throw new RuntimeException();
    }

    public String testFail() {
        return "testFail";
    }

    @RequestMapping(path = "/test2",method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod = "testFail2")
    public String test2(@RequestBody  String req) {
        return req + "test";
    }

    public String testFail2(String req) {
        return req + "fail";
    }
}
