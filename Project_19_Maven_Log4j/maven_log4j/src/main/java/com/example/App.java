package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        process();
    }

    public static void process(){
        logger.trace("This is a trace log message.");
        logger.debug("This is a debug log message.");
        logger.info("This is an info log message.");
        logger.warn("This is warn log message.");
        logger.error("This is error log message.");
        logger.fatal("This is fatal log message.");
    }
}