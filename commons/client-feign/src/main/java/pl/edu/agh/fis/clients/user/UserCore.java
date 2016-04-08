package pl.edu.agh.fis.clients.user;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.dto.use.UserDTO;

/**
 * Created by wemstar on 2016-02-27.
 */
@FeignClient("user-micro-service")
public interface UserCore {
    @RequestMapping(method = RequestMethod.GET, path = "/user")
    Resource<UserDTO> getUsers();

    @RequestMapping(method = RequestMethod.GET, path = "/user/search/findByLogin",params = "login")
    UserDTO getUserByLogin(@RequestParam("login") String login);

    @RequestMapping(method = RequestMethod.GET, path = "/user/{id}")
    Resource<UserDTO> getUser(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.POST,path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    Resource<UserDTO> createUser(@RequestBody UserDTO user);
}
