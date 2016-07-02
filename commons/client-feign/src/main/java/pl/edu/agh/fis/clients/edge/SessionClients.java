package pl.edu.agh.fis.clients.edge;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.fis.dto.user.UserDTO;

/**
 * Created by wemstar on 2016-07-02.
 */
@FeignClient("edge-server")
public interface SessionClients {
    @RequestMapping(method = RequestMethod.GET, value = "/current/user/name")
    String getCurrentUser();

}
