package pl.edu.agh.fis;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import pl.edu.agh.fis.entity.user.UserEntity;
import pl.edu.agh.fis.entity.user.UserGroupEntity;

/**
 * Created by wemstar on 2016-07-02.
 */
@Configuration
public class UserRepositoryConfig extends RepositoryRestConfigurerAdapter {


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(UserEntity.class);
        config.exposeIdsFor(UserGroupEntity.class);
    }
}
