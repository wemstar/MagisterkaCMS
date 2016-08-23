package pl.edu.agh.fis.pdf.config;

import com.mongodb.Mongo;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

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
}
