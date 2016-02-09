package pl.edu.agh.fis.clients;

/**
 * Created by wemstar on 2016-02-09.
 */
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wemstar on 2016-02-09.
 */
@FeignClient("simple-micro-service")
public interface GetTest {

    @RequestMapping(method = RequestMethod.POST, path = "/test2")
    String getStrings(String req);
}

