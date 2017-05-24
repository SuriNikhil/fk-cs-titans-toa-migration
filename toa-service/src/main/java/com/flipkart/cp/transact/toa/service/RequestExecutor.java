//package com.flipkart.cp.transact.toa.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.flipkart.casclient.client.HttpAuthClient;
//import com.flipkart.casclient.entity.Request;
//import com.flipkart.casclient.util.InMemoryCache;
//import com.flipkart.cp.transact.toa.util.common.Constants;
//import com.flipkart.cp.transact.toa.util.common.CosmosUtil;
//import com.ning.http.client.Response;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.Properties;
//
//public class RequestExecutor {
//
//    //private static final Logger log = LoggerFactory.getLogger(RequestExecutor.class);
//    //private static final Logger syslog = LoggerFactory.getLogger("sysLogger");
//
//    private static String CAS_HOST;
//    private static String CAS_USERNAME;
//    private static String CAS_PASSWORD;
//
//
//    static {
//        // Reads the properties file based on environment and sets the TOA properties
//        try {
//            File file = new File(Constants.TOA_CONFIG_FILE);
//            FileInputStream fileInputStream = new FileInputStream(file);
//
//            Properties toaProperties = new Properties();
//            toaProperties.load(fileInputStream);
//
//            if (toaProperties != null) {
//                CAS_HOST = toaProperties.getProperty("CAS_HOST");
//                CAS_USERNAME = toaProperties.getProperty("CAS_USERNAME");
//                CAS_PASSWORD = toaProperties.getProperty("CAS_PASSWORD");
//
//
////                log.info("Static block - setTOAProperties -    CAS_HOST {} CAS_USERNAME {} CAS_PASSWORD {}",
////                        CAS_HOST, CAS_USERNAME, CAS_PASSWORD);
//            } else {
//                //log.error("Static block - setTOAProperties - error in setting TOA properties");
////                syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//            }
//
//        } catch (Exception e) {
////            log.error("Static block - Error in setting TOA properties {} {} {}", e, e.getMessage(), e.getStackTrace());
////            syslog.info(CosmosUtil.cosmosCustomMetricString("property.loading.failure", 1));
//        }
//    }
//
//    public  <T> T executePutRequest(Request request, Class<T> returnClass) throws Exception {
//    	long startTime = System.currentTimeMillis();
//    	Response resp = null;
//        HttpAuthClient httpAuthClient = new HttpAuthClient(CAS_HOST, CAS_USERNAME, CAS_PASSWORD, true, new InMemoryCache());
//        try {
//            resp = httpAuthClient.executePut(request);
//
//        } catch (Exception e) {
////            log.error("Unable to contact service for request." + request.getUrl(), e);
//            throw new Exception(request.getUrl()+" failed with "+ e.getMessage());
//        }
//        T response = extractResponse(returnClass, resp, request.getUrl());
//        long endTime = System.currentTimeMillis();
////        log.info(request.getUrl() + " took " +  (endTime - startTime) + " milliSec");
//        return response;
//    }
//
//    public <T> T executePostRequest(Request request, Class<T> returnClass) throws Exception {
//    	long startTime = System.currentTimeMillis();
//        HttpAuthClient httpAuthClient = new HttpAuthClient(CAS_HOST, CAS_USERNAME, CAS_PASSWORD, true, new InMemoryCache());
//
//        Response resp = null;
//        try {
//            resp = httpAuthClient.executePost(request);
//
//        } catch (Exception e) {
////            log.error("Unable to contact service for request." + request.getUrl(), e);
//            throw new Exception(request.getUrl()+" failed with "+ e.getMessage());
//        }
//
//        T response =  extractResponse(returnClass, resp, request.getUrl());
//        long endTime = System.currentTimeMillis();
////        log.info(request.getUrl() + " took " +  (endTime - startTime) + " milliSec");
//        return response;
//    }
//
//    public  <T> T executeGetRequest(Request request, Class<T> returnClass) throws Exception {
//    	long startTime = System.currentTimeMillis();
//        HttpAuthClient httpAuthClient = new HttpAuthClient(CAS_HOST, CAS_USERNAME, CAS_PASSWORD, true, new InMemoryCache());
//
//        Response resp = null;
//        try {
//            resp = httpAuthClient.executeGet(request);
//
//        } catch (Exception e) {
//          //  log.error("Unable to contact service for request." + request.getUrl(), e);
//            throw new Exception(request.getUrl()+" failed with "+ e.getMessage());
//        }
//        T response =  extractResponse(returnClass, resp, request.getUrl());
//        long endTime = System.currentTimeMillis();
//      //  log.info(request.getUrl() + " took " +  (endTime - startTime) + " milliSec");
//        return response;
//    }
//
//    public  <T> T extractResponse(Class<T> returnClass, Response resp, String requestUrl) throws Exception {
//
//    	if ( resp != null && !(resp.getStatusCode() >= 200 && resp.getStatusCode() < 300) ) {
//    		String responseBody = "";
//    		try {
//				responseBody = resp.getResponseBody();
//			} catch (Exception e) {
//				//log.error("Exception while fetching response body " + e.getMessage());
//			}
//    		//log.warn(requestUrl + " failed with response code "+ resp.getStatusCode() + " response body " + responseBody);
//            throw  new Exception(requestUrl+" failed. " + responseBody);
//        }
//
//    	try {
//
//            if (resp.getResponseBody()==null || resp.getResponseBody().isEmpty()) {
//            //	log.info("No response received");
//                return null;
//            }
//
//          //  log.info("Response from " + requestUrl + " : " + resp.getResponseBody());
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
//            if (returnClass.getName().equals(ObjectNode.class.getName())) {
//                return returnClass.cast(mapper.readTree(resp.getResponseBody()));
//            } else {
//                return mapper.readValue(resp.getResponseBody(), returnClass);
//            }
//
//        } catch (Exception e) {
//          //  log.error("Exception ", e);
//            throw new Exception("Failed to read the response from " + requestUrl +". "+ e.getMessage());
//        }
//    }
//
//}