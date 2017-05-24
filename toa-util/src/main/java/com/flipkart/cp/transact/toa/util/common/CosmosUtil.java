package com.flipkart.cp.transact.toa.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;

/**
 * Created by padmanabh.kulkarni on 08/12/15.
 */
public class CosmosUtil {
    private static final Logger log = LoggerFactory.getLogger(CosmosUtil.class);
    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");

    public static String host = "local";
    public static String activeEnvironment = "dev";

    static {
        // Reads the properties file based on environment and sets the thread properties
        Properties buildProps = new Properties();

        try {
            File file = new File(Constants.TOA_CONFIG_FILE);
            FileInputStream fis = new FileInputStream(file);
            buildProps.load(fis);

            if ( buildProps.getProperty("build.environment") != null) {
                activeEnvironment = buildProps.getProperty("build.environment");
            }

            host = InetAddress.getLocalHost().getHostName();
        } catch (IOException e) {
            log.error("Error in reading build.properties file {} {} {}", e, e.getMessage(), e.getStackTrace());
        }

        log.info("host {} activeEnvironment {}", host, activeEnvironment);
    }

    public static String cosmosCustomMetricString(String metric, long metricValue) {
//        return host + " stream.cosmos: " + String.valueOf(System.currentTimeMillis() / 1000) + " toa.service."
//                + activeEnvironment + "." + metric + " " + metricValue + " host=" + host;

        /*returns "CSMSSPRTRtimestamp metric valueCSMSSPRTR"*/
        return "CSMSSPRTR" + String.valueOf(System.currentTimeMillis()) + " toa.service."
                + activeEnvironment + "." + metric + " " + metricValue + "CSMSSPRTR";
    }

}
