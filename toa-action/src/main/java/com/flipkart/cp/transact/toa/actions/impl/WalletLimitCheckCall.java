package com.flipkart.cp.transact.toa.actions.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flipkart.caishen.api.wallet.requests.LimitRequest;
import com.flipkart.caishen.api.wallet.responses.LimitResponse;
import com.flipkart.cp.convert.simple.proxy.commands.HttpMethod;
import com.flipkart.cp.convert.simple.proxy.request.HttpRequestWrapper;
import com.flipkart.cp.transact.toa.util.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by padmanabh.kulkarni on 01/12/15.
 */
public class WalletLimitCheckCall {
//    private static final Logger log = LoggerFactory.getLogger(WalletLimitCheckCall.class);
//    private static final Logger syslog = LoggerFactory.getLogger("sysLogger");

    private static String WALLET_LIMIT_URL;
    private static String POOL_NAME = "toaWalletService";
    private static String COMMAND_KEY = "toaWalletService";

    static {
        // Reads the properties file based on environment and sets the TOA properties
        try {
            File file = new File(Constants.TOA_CONFIG_FILE);
            FileInputStream fileInputStream = new FileInputStream(file);

            Properties toaProperties = new Properties();
            toaProperties.load(fileInputStream);

            if (toaProperties != null) {
                WALLET_LIMIT_URL = toaProperties.getProperty("WALLET_LIMIT_URL");

//                log.info("Static block - setTOAProperties - WALLET_LIMIT_URL ", WALLET_LIMIT_URL);
            } else {
//                log.error("Static block - setTOAProperties - error in setting TOA properties");
//                syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
            }

        } catch (Exception e) {
//            log.error("Static block - Error in setting TOA properties {} {} {}", e, e.getMessage(), e.getStackTrace());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
        }
    }

    public static LimitResponse getWalletLimit(LimitRequest limitRequest, Map<String , String > headers) throws Exception {
        String limitRequestString = null;
        try {
            limitRequestString = JsonUtils.getObjectMapper().writeValueAsString(limitRequest);
        } catch (IOException e) {
//            log.error("Exception {} {}", e, e.getMessage());
        }

        SimpleProxyUtil.startServiceProxy();

        LimitResponse limitResponse = new LimitResponse();
        HttpRequestWrapper httpRequestWrapper = new HttpRequestWrapper(WALLET_LIMIT_URL).withHttpMethod(HttpMethod.POST).withPayload(limitRequestString.getBytes()).withHeaders(headers);

        try {
            String responseAsString = SimpleProxyHandler.getInstance().execute(httpRequestWrapper, POOL_NAME, COMMAND_KEY);
            if(responseAsString != null) {
//                log.debug("response {}", responseAsString);
                limitResponse = JsonUtils.getObjectMapper().readValue(responseAsString, new TypeReference<LimitResponse>(){});
            }
        } catch (Exception e) {
//            log.error("Exception {} {}", e, e.getMessage());
//            syslog.info(CosmosUtil.cosmosCustomMetricString("wallet.simple.proxy.call.failure", 1));
        }
        if(limitResponse == null || limitResponse.getTransactionLimit() == null) {
//            log.error("Wallet limit check failed. Throwing exception");
//            syslog.info(CosmosUtil.cosmosCustomMetricString("wallet.simple.proxy.call.failure", 1));
            throw new Exception("Wallet limit check failed");
        } else {
            return limitResponse;
        }

    }
}

