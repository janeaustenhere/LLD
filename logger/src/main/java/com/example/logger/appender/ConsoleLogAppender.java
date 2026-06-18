package com.example.logger.appender;


import com.example.logger.formatter.LogFormatter;
import com.example.logger.models.LogMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ConsoleLogAppender implements LogAppender {

    private final LogFormatter logFormatter;

    public ConsoleLogAppender(@Qualifier("plainTextLogFormatter") LogFormatter logFormatter) {
        this.logFormatter = logFormatter;
    }

    @Override
    public synchronized void appendMessage(LogMessage logMessage) {

       String message = logFormatter.formatMessage(logMessage);
       System.out.println(message);
    }
}
