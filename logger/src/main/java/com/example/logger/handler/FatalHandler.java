package com.example.logger.handler;

import com.example.logger.enums.LogLevel;
import com.example.logger.models.LogMessage;
import org.springframework.stereotype.Component;

@Component
public class FatalHandler extends LogHandler{
    @Override
    protected boolean canHandle(LogMessage logMessage) {

        return logMessage.getLogLevel() == LogLevel.FATAL;
    }
}
