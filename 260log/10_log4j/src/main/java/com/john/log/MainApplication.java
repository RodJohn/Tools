package com.john.log;

import com.john.log.service.LogService;
import org.apache.log4j.Logger;

public class MainApplication {

    private static Logger logger = Logger.getLogger(MainApplication.class);

    public static void main(String[] args) {
        logger.trace("trace level message ");
        logger.debug("debug level message ");
        logger.info("info level message ");
        logger.warn("warn level message ");
        logger.error("error level message ");
        logger.error("error level message ");

        new LogService().hello();
    }
}
