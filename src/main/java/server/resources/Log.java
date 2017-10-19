package server.resources;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {


    public static void  writeLog(String className, Object eventObject, String eventDescription, Integer logLevel) {
        Logger log = LoggerFactory.getLogger(className);

        switch (logLevel) {
            case 2:
                log.debug(eventDescription, eventObject);
                break;
            case 1:
                log.error(eventDescription, eventObject);
                break;
            case 0:
                log.info(eventDescription, eventObject);
                break;
            default:
                log.info(eventDescription, eventObject);
                break;

        }

    }
}


