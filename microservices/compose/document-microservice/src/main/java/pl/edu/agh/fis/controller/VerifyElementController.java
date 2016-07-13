package pl.edu.agh.fis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.service.VerifyElementService;
import pl.edu.agh.fis.utils.ElementType;

import java.util.List;

/**
 * Created by wemstar on 2016-07-03.
 */
@RestController
public class VerifyElementController {

    @Autowired
    VerifyElementService service;

    @RequestMapping(value = "verify/{type}/{id}",method = RequestMethod.POST)
    public void verifyElement(@PathVariable ElementType type, @PathVariable String id, @RequestBody List<String> verifyIds) {

        switch (type) {
            case APPLICATION:
                service.verifyApplication(id, verifyIds);
                break;
            case APPLICATION_TEMPLATE:
                service.verifyTemplate(id, verifyIds);
                break;
            case DOCUMENT:
                service.verifyDocument(id, verifyIds);
                break;
        }
    }
}
