package pl.edu.agh.fis.clients.application;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.fis.dto.application.ApplicationDTO;

/**
 * Created by wemstar on 2016-09-03.
 */
@FeignClient("document-server")
public interface ApplicationClient {

    @RequestMapping(method = RequestMethod.GET, value = "/application/{id}")
    Resource<ApplicationDTO> getApplication(@PathVariable("id") String id);
}
