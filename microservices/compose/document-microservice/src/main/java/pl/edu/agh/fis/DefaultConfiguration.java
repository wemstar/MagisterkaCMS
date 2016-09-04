package pl.edu.agh.fis;

import com.mongodb.Mongo;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.fis.utils.ElementType;
import pl.edu.agh.fis.utils.ElementTypeConverter;

import java.beans.PropertyEditor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wemstar on 2016-04-10.
 */

@Configuration

public class DefaultConfiguration {

    @Bean(destroyMethod="close")
    public Mongo mongo() throws IOException {
        return new EmbeddedMongoBuilder()
                .version("2.4.5")
                .bindIp("127.0.0.1")
                .port(12346)
                .build();
    }

    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer customEditor = new CustomEditorConfigurer();
        Map<Class<?>, Class<? extends PropertyEditor>> mapping = new HashMap<>();
        mapping.put(ElementType.class, ElementTypeConverter.class);
        customEditor.setCustomEditors(mapping);
        return customEditor;
    }
}
