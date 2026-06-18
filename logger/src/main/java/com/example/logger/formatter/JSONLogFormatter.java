package com.example.logger.formatter;

import com.example.logger.models.LogMessage;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component("jSONLogFormatter")
public class JSONLogFormatter implements LogFormatter{
    @Override
    public String formatMessage(LogMessage logMessage) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(logMessage.getTimeStamp()),
                ZoneId.systemDefault()).format(dateTimeFormatter);


        return String.format("{ TimeStamp : %s ,LogLevel: [%s],  Message %s }",
                formattedTime,logMessage.getLogLevel(),logMessage.getMessage());
    }
}
