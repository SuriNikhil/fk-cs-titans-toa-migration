package com.flipkart.cp.transact.toa.actions.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flipkart.cp.convert.simple.proxy.commands.HttpMethod;
import com.flipkart.cp.convert.simple.proxy.request.HttpRequestWrapper;
import com.flipkart.cp.transact.toa.actions.models.response.AuthTokenResponse;
import com.flipkart.cp.transact.toa.util.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by padmanabh.kulkarni on 15/06/16.
 */
public class UserServiceAuthTokenCall {
//    private static final Logger log = LoggerFactory.getLogger(UserServiceCall.class);
//    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");

    private static String USER_SERVICE_AUTH_URI;
    private static String USER_SERVICE_AUTH_CLIENT_ID;
    private static String USER_SERVICE_AUTH_SECRET_KEY;
    private static String POOL_NAME = "toaUserServiceAuth";
    private static String COMMAND_KEY = "toaUserServiceAuth";
    private static final String AUTH_TOKEN_TYPE_PREFIX = "Bearer";
    private static final String AUTH_REQUEST_HEADER_PREFIX = "Basic ";

    private static Long tokenValidityTime = 0L;
    private static String token;

    static {
        try {
            File file = new File(Constants.TOA_CONFIG_FILE);
            FileInputStream fileInputStream = new FileInputStream(file);

            Properties toaProperties = new Properties();
            toaProperties.load(fileInputStream);

            if (toaProperties != null) {
                USER_SERVICE_AUTH_URI = toaProperties.getProperty("USER_SERVICE_AUTH_URI");
                USER_SERVICE_AUTH_CLIENT_ID = toaProperties.getProperty("USER_SERVICE_AUTH_CLIENT_ID");
                USER_SERVICE_AUTH_SECRET_KEY = toaProperties.getProperty("USER_SERVICE_AUTH_SECRET_KEY");

//                log.info("Static block - setTOAProperties - USER_SERVICE_URL ", USER_SERVICE_AUTH_URI);
            }
            else {
//                log.error("Static block - setTOAProperties - error in setting TOA properties");
//                syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
            }

        } catch (IOException e) {
//            log.error("Static block - Error in setting TOA properties {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
        }
    }

    public static String getUserServiceAuthToken() throws Exception {

        Long currentTime = new Date().getTime();
        if(token != null && tokenValidityTime >= currentTime + 9000){
//            log.info("Token is valid till "+ tokenValidityTime + " and current time: "+ currentTime + " . Returning existing token: "+ token);
            return token;
        }

        if(USER_SERVICE_AUTH_CLIENT_ID == null){
//            log.error("USER_SERVICE_AUTH_CLIENT_ID is null");
            throw new Exception();
        }

        if(USER_SERVICE_AUTH_SECRET_KEY == null){
//            log.error("USER_SERVICE_AUTH_CLIENT_ID is null");
            throw new Exception();
        }

        if(USER_SERVICE_AUTH_URI == null){
//            log.error("USER_SERVICE_AUTH_URI is null");
            throw new Exception();
        }

        SimpleProxyUtil.startServiceProxy();

        String clientIdAndKey = USER_SERVICE_AUTH_CLIENT_ID + ":" + USER_SERVICE_AUTH_SECRET_KEY;

//        log.debug("clientIdAndKey: "+ clientIdAndKey);
        String encodedSecretKey = Base64.getEncoder().encodeToString(clientIdAndKey.getBytes());

        Map<String , String > headers = new HashMap<>();
        headers.put("Authorization", AUTH_REQUEST_HEADER_PREFIX + encodedSecretKey);

        AuthTokenResponse authTokenResponse = null;
        HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper(USER_SERVICE_AUTH_URI).withHttpMethod(HttpMethod.POST).withHeaders(headers);
        try {
            String responseAsString = SimpleProxyHandler.getInstance().execute(httpRequestWrapper, POOL_NAME, COMMAND_KEY);
            if(responseAsString != null) {
                authTokenResponse = JsonUtils.getObjectMapper().readValue(responseAsString, new TypeReference<AuthTokenResponse>(){});
            }
        } catch (Exception e) {
//            log.error("Exception {} {}", e, e.getMessage());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("user.service.simple.proxy.call.failure", 1));
        }

        if(authTokenResponse == null) {
//            log.error("No response from userService. Throwing exception");
//            syslog.info(CosmosUtil.cosmosCustomMetricString("user.service.simple.proxy.call.failure", 1));
            tokenValidityTime = 0L;
            token = null;
            throw new Exception("No response");
        } else if(authTokenResponse.getError() == null && authTokenResponse.getAccessToken() != null){
            token = AUTH_TOKEN_TYPE_PREFIX + authTokenResponse.getAccessToken();

//            log.info("Token: "+ token + " expires in: "+ authTokenResponse.getExpiresIn());
            tokenValidityTime = new Date().getTime() + authTokenResponse.getExpiresIn() * 1000;

            return token;
        }

        throw new Exception();
    }

}
