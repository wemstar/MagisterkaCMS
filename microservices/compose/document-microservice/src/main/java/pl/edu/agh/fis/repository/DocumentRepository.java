package pl.edu.agh.fis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.edu.agh.fis.document.DocumentEntity;

/**
 * Created by wemstar on 2016-04-13.
 */
@RepositoryRestResource(collectionResourceRel = "document", path = "document")
public interface DocumentRepository extends CrudRepository<DocumentEntity,String> {
}
