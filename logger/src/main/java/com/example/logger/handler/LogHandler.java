package com.example.logger.handler;

import com.example.logger.appender.LogAppender;
import com.example.logger.models.LogMessage;
import lombok.Setter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class LogHandler {

    @Setter
    private LogHandler next;
    public final List<LogAppender> subsribers= new CopyOnWriteArrayList<>();

    public void notifySubsribers(LogMessage logMessage) {

        for (LogAppender subsriber :subsribers){
            subsriber.appendMessage(logMessage);
        }

    }

    public void addSubsribers(LogAppender logAppender){
        subsribers.add(logAppender);
    }

    public void handle(LogMessage logMessage) {

        if(canHandle(logMessage)){
            notifySubsribers(logMessage);
        }

        if(next != null){
            next.handle(logMessage);
        }
    }

    protected abstract boolean canHandle(LogMessage logMessage);


}
