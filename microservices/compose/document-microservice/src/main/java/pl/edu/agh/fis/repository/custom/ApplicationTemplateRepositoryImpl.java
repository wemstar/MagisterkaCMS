package pl.edu.agh.fis.repository.custom;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.model.application.template.ApplicationTemplateEntity;
import pl.edu.agh.fis.repository.ApplicationTemplateRepository;
import pl.edu.agh.fis.utils.CommentUtils;
import pl.edu.agh.fis.utils.UserCommonUtils;
import pl.edu.agh.fis.utils.UserFilter;

import java.util.List;

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

    @Autowired
    UserFilter userFilter;

    @RequestMapping(method = RequestMethod.POST, value = "/template")
    @HystrixCommand(fallbackMethod = "defaultCreateNewApplicationTemplate")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewApplicationTemplate(@RequestBody ApplicationTemplateEntity entity) {
        entity.author = userCommonUtils.getCurrentUserId();
        repository.save(entity);
    }

    public void defaultCreateNewApplicationTemplate(ApplicationTemplateEntity entity) {
        repository.save(entity);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/template/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateApplicationTemplate(@PathVariable String id, @RequestBody ApplicationTemplateEntity entity) {
        entity.id = id;
        entity.verificationSteps.stream().filter( p -> p.id == null).forEach( p -> p.id = new ObjectId().toHexString());
        entity.activities.add(commentUtils.createChangeAction());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/template")
    public ResponseEntity<?> getAllUserApplicationTeamplate(){
        Iterable<ApplicationTemplateEntity> allEntity = repository.findAll();
        List<ApplicationTemplateEntity> filteredEntities =  userFilter.filterTemplates(allEntity);
        return ResponseEntity.ok(new Resources<>(filteredEntities));
    }
}
