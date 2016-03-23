package pl.edu.agh.fis.service.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.edu.agh.fis.entity.user.UserGroupEntity;

/**
 * Created by wemstar on 2016-02-27.
 */
@RepositoryRestResource(collectionResourceRel = "group", path = "group")
public interface UserGroupRepository extends CrudRepository<UserGroupEntity,Long> {
}
