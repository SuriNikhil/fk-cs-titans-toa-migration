package com.flipkart.cp.transact.toa.util.common;

import com.flipkart.kloud.authn.AuthTokenService;
import com.flipkart.kloud.authn.PrivateKeyCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Properties;

/**
 * Created by padmanabh.kulkarni on 02/12/15.
 */
public class AuthUtil {
    private static final Logger log = LoggerFactory.getLogger(AuthUtil.class);
    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");

    private static String AUTHN_CLIENT_ID;
    private static String AUTH_URL;

    static {
        // Reads the properties file based on environment and sets the TOA properties
        try {
            File file = new File(Constants.TOA_CONFIG_FILE);
            FileInputStream fileInputStream = new FileInputStream(file);

            Properties toaProperties = new Properties();
            toaProperties.load(fileInputStream);

            if (toaProperties != null) {
                AUTHN_CLIENT_ID = toaProperties.getProperty("AUTHN_CLIENT_ID");
                AUTH_URL = toaProperties.getProperty("AUTH_URL");

                log.info("Static block - setTOAProperties - AUTHN_CLIENT_ID {}, AUTH_URL {}", AUTHN_CLIENT_ID, AUTH_URL);

            } else {
                log.error("Static block - setTOAProperties - error in setting TOA properties");
                syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
            }

        } catch (Exception e) {
            log.error("Static block - Error in setting TOA properties {} {} {}", e, e.getMessage(), e.getStackTrace());
            syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
        }
    }


    public static String  getAuthorizationHeader(String targetClientId) {
        PrivateKeyCredential privateKeyCredential = null;
        try {
            privateKeyCredential = PrivateKeyCredential.fromPemFile(AUTHN_CLIENT_ID, Constants.PRIVATE_KEY_PATH);
            AuthTokenService tokenService = new AuthTokenService(AUTH_URL, privateKeyCredential);
            String header = tokenService.fetchToken(targetClientId).toAuthorizationHeader();
            return header;
        } catch (IOException e) {
            log.error("Exception {} {}", e, e.getMessage());
            syslog.info(CosmosUtil.cosmosCustomMetricString("get.auth.header.call.failure", 1));
        } catch (NoSuchAlgorithmException e) {
            log.error("Exception {} {}", e, e.getMessage());
            syslog.info(CosmosUtil.cosmosCustomMetricString("get.auth.header.call.failure", 1));
        } catch (InvalidKeySpecException e) {
            log.error("Exception {} {}", e, e.getMessage());
            syslog.info(CosmosUtil.cosmosCustomMetricString("get.auth.header.call.failure", 1));
        }
        return null;
    }

}
