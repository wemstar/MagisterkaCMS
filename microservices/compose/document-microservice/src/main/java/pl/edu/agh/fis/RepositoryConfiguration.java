package pl.edu.agh.fis;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import pl.edu.agh.fis.model.application.ApplicationEntity;
import pl.edu.agh.fis.model.application.template.ApplicationTemplateEntity;
import pl.edu.agh.fis.model.document.DocumentEntity;
import pl.edu.agh.fis.model.verification.VerificationStep;

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
        config.exposeIdsFor(VerificationStep.class);
    }
}
