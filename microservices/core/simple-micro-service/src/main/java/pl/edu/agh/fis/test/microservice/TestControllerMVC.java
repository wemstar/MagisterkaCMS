package pl.edu.agh.fis.test.microservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.fis.builder.dto.user.UserDTOBuilder;
import pl.edu.agh.fis.clients.user.UserCore;
import pl.edu.agh.fis.dto.user.UserDTO;

/**
 * Created by wemstar on 2016-02-08.
 */
@EnableCircuitBreaker
@RestController
public class TestControllerMVC {

    @Autowired
    private UserCore userCore;

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

    @RequestMapping(path = "/user/create",method = RequestMethod.GET)
    public Resource<UserDTO> createUser() {
        UserDTO user = UserDTOBuilder.anUserDTO()
                .id(1L)
                .email("email")
                .login("login1")
                .password("pass2")
                .build();
        Resource<UserDTO> res = userCore.createUser(user);
        return res;
    }
}
