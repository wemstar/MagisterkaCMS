package pl.edu.agh.fis.clients.user;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.fis.dto.user.UserDTO;
import pl.edu.agh.fis.dto.user.UserGroupDTO;

/**
 * Created by wemstar on 2016-02-28.
 */
@FeignClient("user-micro-service")
public interface UserGroupCore {

    @RequestMapping(method = RequestMethod.GET, path = "/group")
    Resource<UserGroupDTO> getUsers();

    @RequestMapping(method = RequestMethod.GET, path = "/group/{id}")
    Resource<UserGroupDTO> getUser(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.POST,path = "/group", consumes = MediaType.APPLICATION_JSON_VALUE)
    Resource<UserGroupDTO> createUser(@RequestBody UserDTO user);
}
