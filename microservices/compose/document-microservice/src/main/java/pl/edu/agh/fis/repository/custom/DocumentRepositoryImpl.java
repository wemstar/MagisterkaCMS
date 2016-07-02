package pl.edu.agh.fis.repository.custom;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.activity.ActionType;
import pl.edu.agh.fis.activity.ActivityEntity;
import pl.edu.agh.fis.activity.CommentEntity;
import pl.edu.agh.fis.application.ApplicationEntity;
import pl.edu.agh.fis.clients.edge.SessionClients;
import pl.edu.agh.fis.clients.user.UserCore;
import pl.edu.agh.fis.document.DocumentEntity;
import pl.edu.agh.fis.dto.user.UserDTO;
import pl.edu.agh.fis.repository.DocumentRepository;
import pl.edu.agh.fis.utils.CommentUtils;
import pl.edu.agh.fis.utils.UserCommonUtils;

/**
 * Created by wemstar on 2016-07-02.
 */
@RepositoryRestController
public class DocumentRepositoryImpl {

    @Autowired
    DocumentRepository repository;

    @Autowired
    UserCommonUtils userCommonUtils;

    @Autowired
    CommentUtils commentUtils;

    @RequestMapping(method = RequestMethod.POST, value = "/document")
    @HystrixCommand(fallbackMethod = "defaultCreateNewDocument")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewDocument(@RequestBody DocumentEntity entity) {
        entity.author = userCommonUtils.getCurrentUserId();
        repository.save(entity);
    }

    public void defaultCreateNewDocument(DocumentEntity entity) {
        repository.save(entity);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/document/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable String id, @RequestBody DocumentEntity entity) {
        entity.id = id;
        entity.activities.add(commentUtils.createChangeAction());
        repository.save(entity);
    }
}
