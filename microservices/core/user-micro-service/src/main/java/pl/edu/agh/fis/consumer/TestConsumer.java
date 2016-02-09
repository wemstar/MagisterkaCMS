package pl.edu.agh.fis.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.fis.clients.GetTest;

/**
 * Created by wemstar on 2016-02-09.
 */
@RestController
public class TestConsumer {

    @Autowired
    GetTest getTest;

    @RequestMapping(path = "/test2",method = RequestMethod.GET)
    public String returnResp() {
        return getTest.getStrings("hr");
    }
}
