package pl.edu.agh.fis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.edu.agh.fis.model.activity.CommentEntity;

/**
 * Created by wemstar on 2016-06-20.
 */
@RepositoryRestResource(collectionResourceRel = "commentDTO", path = "commentDTO")
public interface CommentRepository extends CrudRepository<CommentEntity,String> {
}
