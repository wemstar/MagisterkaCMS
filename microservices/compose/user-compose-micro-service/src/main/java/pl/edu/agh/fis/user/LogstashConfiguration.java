package pl.edu.agh.fis.user;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.fis.config.LogstashLogbackConfig;

import javax.annotation.PostConstruct;

/**
 * Created by wemstar on 2016-03-02.
 */
@Configuration
public class LogstashConfiguration {

    @Value("${logstash-adres}")
    private String logstashAdres;

    @Value("${spring.application.name}")
    private String appname;

    private LogstashTcpSocketAppender logstashTcpSocketAppender;

    @PostConstruct
    public void init() {
        Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        LoggerContext loggerContext = rootLogger.getLoggerContext();
        logstashTcpSocketAppender = LogstashLogbackConfig.configLogger(rootLogger,loggerContext,logstashAdres,appname);
    }
}
