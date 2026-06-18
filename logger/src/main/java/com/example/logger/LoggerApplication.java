package com.example.logger;

import com.example.logger.appender.ConsoleLogAppender;
import com.example.logger.appender.FileLogAppender;
import com.example.logger.configuration.LogConfiguration;
import com.example.logger.enums.LogLevel;
import com.example.logger.formatter.JSONLogFormatter;
import com.example.logger.formatter.PlainTextLogFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class LoggerApplication {

	public static final Logger logger = Logger.getInstance();



	public static void main(String[] args) throws IOException {

		logger.info("Manasi Suthar");
		logger.error("Exception/ error");

		SpringApplication.run(LoggerApplication.class, args);






	}

}
