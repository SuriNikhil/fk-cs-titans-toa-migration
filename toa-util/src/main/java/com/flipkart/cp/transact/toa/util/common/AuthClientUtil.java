package com.flipkart.cp.transact.toa.util.common;

import com.flipkart.casclient.client.HttpAuthClient;
import com.flipkart.casclient.util.InMemoryCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by padmanabh.kulkarni on 10/02/16.
 */
public class AuthClientUtil {
    private static String CAS_URI;
    private static String CAS_USERNAME;
    private static String CAS_PASSWORD;

    private static final Logger log = LoggerFactory.getLogger(AuthClientUtil.class);
    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");


    static {
        // Reads the properties file based on environment and sets the TOA properties
        try {
            File file = new File(Constants.TOA_CONFIG_FILE);
            FileInputStream fileInputStream = new FileInputStream(file);

            Properties toaProperties = new Properties();
            toaProperties.load(fileInputStream);

            if (toaProperties != null) {
                CAS_URI = toaProperties.getProperty("CAS_URI");
                CAS_USERNAME = toaProperties.getProperty("CAS_USERNAME");
                CAS_PASSWORD = toaProperties.getProperty("CAS_PASSWORD");

                log.info("Static block - setTOAProperties - CAS_URI {}, CAS_USERNAME {}, CAS_PASSWORD {}",
                         CAS_URI, CAS_USERNAME, CAS_PASSWORD);
            } else {
                log.error("Static block - setTOAProperties - error in setting TOA properties");
                syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
            }

        } catch (Exception e) {
            log.error("Static block - Error in setting TOA properties {} {} {}", e, e.getMessage(), e.getStackTrace());
            syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
        }
    }

    private static HttpAuthClient httpAuthClient;

    public static HttpAuthClient getHttpAuthClient() {
        return getHttpAuthClient(false);
    }


    public static HttpAuthClient getHttpAuthClient (boolean createNewInstance) {

        if (httpAuthClient != null && !createNewInstance)
            return httpAuthClient;

        HttpAuthClient httpAuthClientInstance = new HttpAuthClient(CAS_URI, CAS_USERNAME, CAS_PASSWORD, true, new InMemoryCache());


        // newInstance request should not override global ObjectMapper instance
        if (!createNewInstance)
            httpAuthClient = httpAuthClientInstance;

        return httpAuthClientInstance;
    }
}
