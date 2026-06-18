package com.example.logger.formatter;

import com.example.logger.models.LogMessage;

public interface LogFormatter {

    String formatMessage(LogMessage message);
}
