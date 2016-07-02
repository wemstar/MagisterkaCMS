package pl.edu.agh.fis.repository.custom;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.application.template.ApplicationTemplateEntity;
import pl.edu.agh.fis.repository.ApplicationTemplateRepository;
import pl.edu.agh.fis.utils.CommentUtils;
import pl.edu.agh.fis.utils.UserCommonUtils;

/**
 * Created by wemstar on 2016-07-02.
 */
@RepositoryRestController
public class ApplicationTemplateRepositoryImpl {

    @Autowired
    ApplicationTemplateRepository repository;

    @Autowired
    UserCommonUtils userCommonUtils;

    @Autowired
    CommentUtils commentUtils;

    @RequestMapping(method = RequestMethod.POST, value = "/template")
    @HystrixCommand(fallbackMethod = "defaultCreateNewApplicationTemplate")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewDocument(@RequestBody ApplicationTemplateEntity entity) {
        entity.author = userCommonUtils.getCurrentUserId();
        repository.save(entity);
    }

    public void defaultCreateNewApplicationTemplate(ApplicationTemplateEntity entity) {
        repository.save(entity);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/template/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable String id, @RequestBody ApplicationTemplateEntity entity) {
        entity.id = id;
        entity.activities.add(commentUtils.createChangeAction());
    }
}
