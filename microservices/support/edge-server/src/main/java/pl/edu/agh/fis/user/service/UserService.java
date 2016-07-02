package pl.edu.agh.fis.user.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wemstar on 2016-06-30.
 */

@RestController
public class UserService {

    @RequestMapping(path = "/current/user/name",method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "defaultGetCurrentUserLogin")
    public String getCurrentUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public String defaultGetCurrentUserLogin() {
        return "user";
    }
}
