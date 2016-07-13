package pl.edu.agh.fis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.edu.agh.fis.model.application.template.ApplicationTemplateEntity;

/**
 * Created by wemstar on 2016-06-06.
 */
@RepositoryRestResource(collectionResourceRel = "template", path = "template")
public interface ApplicationTemplateRepository extends CrudRepository<ApplicationTemplateEntity,String> {
}
