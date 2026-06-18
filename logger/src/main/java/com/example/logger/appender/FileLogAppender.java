package com.example.logger.appender;

import com.example.logger.formatter.LogFormatter;
import com.example.logger.models.LogMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;

@Component
public class FileLogAppender implements LogAppender{

    private final LogFormatter logFormatter;
    private final BufferedWriter bufferedWriter;

    public FileLogAppender(@Qualifier("jSONLogFormatter") LogFormatter logFormatter) {
        this.logFormatter = logFormatter;
        try {
            this.bufferedWriter =  new BufferedWriter(new FileWriter("/Users/manasisuthar/IdeaProjects/logger/log.txt",true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public synchronized void appendMessage(LogMessage logMessage) {
        try {
            bufferedWriter.write(logFormatter.formatMessage(logMessage));
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
