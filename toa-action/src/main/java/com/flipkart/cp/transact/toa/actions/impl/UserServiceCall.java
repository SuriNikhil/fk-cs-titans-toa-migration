package com.flipkart.cp.transact.toa.actions.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flipkart.cp.convert.simple.proxy.commands.HttpMethod;
import com.flipkart.cp.convert.simple.proxy.request.HttpRequestWrapper;
import com.flipkart.cp.transact.toa.actions.models.request.UserServiceRequest;
import com.flipkart.cp.transact.toa.actions.models.response.UserServiceResponse;
import com.flipkart.cp.transact.toa.util.common.*;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by padmanabh.kulkarni on 28/06/15.
 */
public class UserServiceCall {
//    private static final Logger log = LoggerFactory.getLogger(UserServiceCall.class);
//    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");

    private static String USER_SERVICE_URL;
    private static String POOL_NAME = "toaUserService";
    private static String COMMAND_KEY = "toaUserService";

    //TODO:add authN header

    static {
        try {
            File file = new File(Constants.TOA_CONFIG_FILE);
            FileInputStream fileInputStream = new FileInputStream(file);

            Properties toaProperties = new Properties();
            toaProperties.load(fileInputStream);

            if (toaProperties != null) {
                USER_SERVICE_URL = toaProperties.getProperty("USER_SERVICE_URL");
//                log.info("Static block - setTOAProperties - USER_SERVICE_URL ", USER_SERVICE_URL);
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

    public static UserServiceResponse getUserServiceResponse(UserServiceRequest userServiceRequest, Map<String , String> headers) throws Exception {
        SimpleProxyUtil.startServiceProxy();

        List<UserServiceResponse> userServiceResponses = Lists.newArrayList();
        HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper(USER_SERVICE_URL + userServiceRequest.getCustomerAccountId() + Constants.USER_SERVICE_URL_PARAMS).withHttpMethod(HttpMethod.GET).withHeaders(headers);
        try {
            String responseAsString = SimpleProxyHandler.getInstance().execute(httpRequestWrapper, POOL_NAME, COMMAND_KEY);
            if(responseAsString != null) {
                userServiceResponses = JsonUtils.getObjectMapper().readValue(responseAsString, new TypeReference<List<UserServiceResponse>>(){});
            }
        } catch (Exception e) {
//            log.error("Exception {} {}", e, e.getMessage());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("user.service.simple.proxy.call.failure", 1));
        }

        if(userServiceResponses == null || userServiceResponses.isEmpty()) {
//            log.error("No response from userService. Throwing exception");
//            syslog.info(CosmosUtil.cosmosCustomMetricString("user.service.simple.proxy.call.failure", 1));
            throw new Exception("No response");
        } else {
            return userServiceResponses.get(0);
        }
    }
}
