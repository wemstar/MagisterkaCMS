package pl.edu.agh.fis;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.MediaType;
import pl.edu.agh.fis.application.ApplicationEntity;
import pl.edu.agh.fis.application.template.ApplicationTemplateEntity;
import pl.edu.agh.fis.document.DocumentEntity;

/**
 * Created by wemstar on 2016-04-24.
 */
@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        //config.setDefaultMediaType(new MediaType("application","x-spring-data-verbose+json"));
        config.exposeIdsFor(DocumentEntity.class);
        config.exposeIdsFor(ApplicationEntity.class);
        config.exposeIdsFor(ApplicationTemplateEntity.class);
    }
}
