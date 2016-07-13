package pl.edu.agh.fis.repository.custom;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.model.document.DocumentEntity;
import pl.edu.agh.fis.repository.DocumentRepository;
import pl.edu.agh.fis.utils.CommentUtils;
import pl.edu.agh.fis.utils.UserCommonUtils;
import pl.edu.agh.fis.utils.UserFilter;

import java.util.List;

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

    @Autowired
    UserFilter userFilter;

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
    public void updateDocument(@PathVariable String id, @RequestBody DocumentEntity entity) {
        entity.id = id;
        entity.activities.add(commentUtils.createChangeAction());
        entity.verificationSteps.stream().filter( p -> p.id == null).forEach( p -> p.id = new ObjectId().toHexString());
        repository.save(entity);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/document")
    public ResponseEntity<?> getAllUserDocuments(){
        Iterable<DocumentEntity> allEntity = repository.findAll();
        List<DocumentEntity> filteredEntities =  userFilter.filterDocuments(allEntity);
        return ResponseEntity.ok(new Resources<>(filteredEntities));
    }
}
