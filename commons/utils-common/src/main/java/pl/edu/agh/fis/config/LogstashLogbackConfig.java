package pl.edu.agh.fis.config;
import net.logstash.logback.encoder.LogstashEncoder;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import net.logstash.logback.appender.LogstashTcpSocketAppender;

/**
 * Created by wemstar on 2016-03-23.
 */
public class LogstashLogbackConfig {

    public static LogstashTcpSocketAppender configLogger(Logger rootLogger, LoggerContext loggerContext,String logstashAdres,String appname) {
        LogstashTcpSocketAppender logstashTcpSocketAppender = new LogstashTcpSocketAppender();
        logstashTcpSocketAppender.setName("TCP");
        logstashTcpSocketAppender.setContext(loggerContext);
        logstashTcpSocketAppender.addDestination(logstashAdres+":5044");

        LogstashEncoder encoder = new LogstashEncoder();
        encoder.setContext(loggerContext);
        encoder.setIncludeCallerData(true);
        encoder.setCustomFields("{\"appname\":\""+appname+"\"}");
        encoder.start();

        logstashTcpSocketAppender.setEncoder(encoder);
        logstashTcpSocketAppender.start();

        rootLogger.addAppender(logstashTcpSocketAppender);
        /*rootLogger.setLevel(Level.DEBUG);
        rootLogger.warn(logstashAdres);*/
        return logstashTcpSocketAppender;
    }
}
