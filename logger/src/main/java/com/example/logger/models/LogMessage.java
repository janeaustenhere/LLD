package com.example.logger.models;

import com.example.logger.enums.LogLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogMessage {

    String message;
    LogLevel logLevel;
    Long timeStamp;

}
