package com.example.logger.configuration;


import com.example.logger.appender.LogAppender;
import com.example.logger.enums.LogLevel;

import com.example.logger.handler.*;

public class LogConfiguration {

    private final static LogHandler debugHandler = new DebugHandler();
    private final static LogHandler infoHandler = new InfoHandler();
    private final static LogHandler errorHandler = new ErrorHandler();
    private final static LogHandler warnHandler = new WarnHandler();
    private final static LogHandler fatalHandler = new FatalHandler();


    public static LogHandler build(){

        debugHandler.setNext(infoHandler);
        infoHandler.setNext(warnHandler);
        warnHandler.setNext(errorHandler);
        errorHandler.setNext(fatalHandler);

        return debugHandler;
    }

    public static void subscribeAppender (LogLevel logLevel, LogAppender logAppender){
        switch (logLevel) {
            case LogLevel.DEBUG:
                debugHandler.addSubsribers(logAppender);
                break;
            case LogLevel.ERROR:
                errorHandler.addSubsribers(logAppender);
                break;
            case LogLevel.FATAL:
                fatalHandler.addSubsribers(logAppender);
                break;
            case LogLevel.INFO:
                infoHandler.addSubsribers(logAppender);
                break;
            case LogLevel.WARN:
                warnHandler.addSubsribers(logAppender);
        }

    }


}
