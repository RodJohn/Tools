package com.john.log.service;

import org.apache.log4j.Logger;

public class LogService {

    private static Logger logger = Logger.getLogger(LogService.class);

    public void hello() {
        logger.trace("trace level message ");
        logger.debug("debug level message ");
        logger.info("info level message ");
        logger.warn("warn level message ");
        logger.error("error level message ");
        logger.error("error level message ");
    }

}
