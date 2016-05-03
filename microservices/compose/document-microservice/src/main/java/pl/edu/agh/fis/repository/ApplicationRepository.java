package pl.edu.agh.fis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.edu.agh.fis.application.ApplicationEntity;

/**
 * Created by wemstar on 2016-04-13.
 */
@RepositoryRestResource(collectionResourceRel = "application", path = "application")
public interface ApplicationRepository extends CrudRepository<ApplicationEntity,String> {
}
