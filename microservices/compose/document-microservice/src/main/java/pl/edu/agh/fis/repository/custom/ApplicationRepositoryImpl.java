package pl.edu.agh.fis.repository.custom;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.model.application.ApplicationEntity;
import pl.edu.agh.fis.repository.ApplicationRepository;
import pl.edu.agh.fis.utils.CommentUtils;
import pl.edu.agh.fis.utils.UserCommonUtils;
import pl.edu.agh.fis.utils.UserFilter;

import java.util.List;

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

    @Autowired
    UserFilter userFilter;

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
        entity.verificationSteps.stream().filter( p -> p.id == null).forEach( p -> p.id = new ObjectId().toHexString());
        entity.activities.add(commentUtils.createChangeAction());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/application")
    public ResponseEntity<?> getAllUserApplication(){
        Iterable<ApplicationEntity> allEntity = repository.findAll();
        List<ApplicationEntity> filteredEntities =  userFilter.filterApplications(allEntity);
        return ResponseEntity.ok(new Resources<>(filteredEntities));
    }
}
