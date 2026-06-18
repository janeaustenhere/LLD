package com.example.logger.appender;

import com.example.logger.models.LogMessage;

import java.io.IOException;

public interface LogAppender {

    void appendMessage(LogMessage logMessage);
}
