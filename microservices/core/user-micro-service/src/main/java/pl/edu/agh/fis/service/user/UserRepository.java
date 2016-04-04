package pl.edu.agh.fis.service.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.Resource;
import pl.edu.agh.fis.entity.user.UserEntity;

/**
 * Created by wemstar on 2016-02-23.
 */

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends CrudRepository<UserEntity,Long> {

    Resource<UserEntity> findByLogin(@Param("login") String login);
}
