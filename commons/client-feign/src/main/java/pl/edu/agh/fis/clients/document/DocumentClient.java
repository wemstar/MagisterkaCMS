package pl.edu.agh.fis.clients.document;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.fis.dto.document.DocumentDTO;

/**
 * Created by wemstar on 2016-08-14.
 */
@FeignClient("document-pdf-microservice")
public interface DocumentClient {

    @RequestMapping(method = RequestMethod.GET, value = "/document/{id}")
    Resource<DocumentDTO> getDocument(@PathVariable("id") String id);
}
