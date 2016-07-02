package pl.edu.agh.fis.repository.custom;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.application.ApplicationEntity;
import pl.edu.agh.fis.repository.ApplicationRepository;
import pl.edu.agh.fis.utils.CommentUtils;
import pl.edu.agh.fis.utils.UserCommonUtils;

/**
 * Created by wemstar on 2016-07-02.
 */
@RepositoryRestController
public class ApplicationRepositoryImpl {

    @Autowired
    ApplicationRepository repository;

    @Autowired
    UserCommonUtils userCommonUtils;

    @Autowired
    CommentUtils commentUtils;

    @RequestMapping(method = RequestMethod.POST, value = "/application")
    @HystrixCommand(fallbackMethod = "defaultCreateNewApplication")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewDocument(@RequestBody ApplicationEntity entity) {
        entity.author = userCommonUtils.getCurrentUserId();
        repository.save(entity);
    }

    public void defaultCreateNewApplication(ApplicationEntity entity) {
        repository.save(entity);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/application/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable String id, @RequestBody ApplicationEntity entity) {
        entity.id = id;
        entity.activities.add(commentUtils.createChangeAction());
    }
}
