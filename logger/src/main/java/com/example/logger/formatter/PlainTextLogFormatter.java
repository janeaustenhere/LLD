package com.example.logger.formatter;

import com.example.logger.models.LogMessage;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component("plainTextLogFormatter")
public class PlainTextLogFormatter implements LogFormatter{
    @Override
    public String formatMessage(LogMessage logMessage) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy:MM-dd HH:mm:ss");

        String formattedTime = LocalDateTime.
                ofInstant(Instant.ofEpochMilli(logMessage.getTimeStamp()),
                        ZoneId.systemDefault()).format(dateTimeFormatter);


        return String.format("%s  [%s] - %s", formattedTime, logMessage.getLogLevel(), logMessage.getMessage());
    }
}
