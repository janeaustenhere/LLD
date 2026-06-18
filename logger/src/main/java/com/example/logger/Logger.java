package com.example.logger;

import com.example.logger.appender.ConsoleLogAppender;
import com.example.logger.appender.FileLogAppender;
import com.example.logger.configuration.LogConfiguration;
import com.example.logger.enums.LogLevel;
import com.example.logger.formatter.JSONLogFormatter;
import com.example.logger.formatter.PlainTextLogFormatter;
import com.example.logger.handler.LogHandler;
import com.example.logger.models.LogMessage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;


public class Logger {

   public static final Logger INSTANCE = new Logger();

   private final LogHandler handlechain;

   private Logger(){

       handlechain =  LogConfiguration.build();
       LogConfiguration.subscribeAppender(LogLevel.INFO,new ConsoleLogAppender(new PlainTextLogFormatter()));
       LogConfiguration.subscribeAppender(LogLevel.ERROR,new FileLogAppender(new JSONLogFormatter()));
   }

   public static Logger getInstance(){
       return INSTANCE;
   }

    private void log (LogLevel logLevel, String message)  {
        LogMessage logMessage = new LogMessage(message,logLevel,System.currentTimeMillis());
        handlechain.handle(logMessage);
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO,message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void fatal(String message){
        log(LogLevel.FATAL, message);
    }

    public void error(String message){
        log(LogLevel.ERROR, message);
    }
}
