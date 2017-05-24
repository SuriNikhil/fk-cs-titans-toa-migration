package com.flipkart.cp.transact.toa.actions.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flipkart.cp.convert.simple.proxy.commands.HttpMethod;
import com.flipkart.cp.convert.simple.proxy.request.HttpRequestWrapper;
import com.flipkart.cp.transact.toa.actions.models.request.CASRequest;
import com.flipkart.cp.transact.toa.util.common.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * Created by padmanabh.kulkarni on 28/07/15.
 */
public class CASCall {
//    private static final Logger log = LoggerFactory.getLogger(UserServiceCall.class);
//    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");

    private static String CAS_GET_USER_ROLE_URL;
    private static String POOL_NAME = "toaCASService";
    private static String COMMAND_KEY = "toaCASService";

    static {
        try {
            File file = new File(Constants.TOA_CONFIG_FILE);
            FileInputStream fileInputStream = new FileInputStream(file);

            Properties toaProperties = new Properties();
            toaProperties.load(fileInputStream);

            if (toaProperties != null) {
                CAS_GET_USER_ROLE_URL = toaProperties.getProperty("CAS_GET_USER_ROLE_URL");
  //              log.info("Static block - setTOAProperties - CAS_GET_USER_ROLE_URL ", CAS_GET_USER_ROLE_URL);
            }
            else {
    //            log.error("Static block - setTOAProperties - error in setting TOA properties");
      //          syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
            }

        } catch (IOException e) {
        //    log.error("Static block - Error in setting TOA properties {} {} {} ", e, e.getMessage(), e.getStackTrace());
          //  syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
        }
    }

    public static Set<String> getCASResponse(CASRequest casRequest) throws Exception {
        SimpleProxyUtil.startServiceProxy();

        Set<String> userRoles = null;
        HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper(CAS_GET_USER_ROLE_URL + casRequest.getUserLogin()).withHttpMethod(HttpMethod.GET);
        try {
            String responseAsString = SimpleProxyHandler.getInstance().execute(httpRequestWrapper, POOL_NAME, COMMAND_KEY);
            userRoles = JsonUtils.getObjectMapper().readValue(responseAsString, new TypeReference<Set<String>>() {
            });
        } catch (Exception e) {
         //   log.error("Exception {} {} {}", e, e.getMessage(), e.getStackTrace());
           // syslog.info(CosmosUtil.cosmosCustomMetricString("cas.simple.proxy.call.failure", 1));
        }

        if(userRoles == null || userRoles.isEmpty()) {
         //   log.error("No response from CASService. Throwing exception");
           // syslog.info(CosmosUtil.cosmosCustomMetricString("cas.simple.proxy.call.failure", 1));
            throw new Exception("No response");
        } else {
            return userRoles;
        }
    }

}
