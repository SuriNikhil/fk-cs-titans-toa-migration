package com.flipkart.cp.transact.toa.util.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.base.Strings;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by padmanabh.kulkarni on 28/06/15.
 */
public class JsonUtils {

    //private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * defines the objectMapper configs.
     *
     * @return
     */
    private static ObjectMapper objectMapper;

    public static ObjectMapper getObjectMapper() {
        return getObjectMapper(false);
    }

    public static ObjectMapper getObjectMapper(boolean createNewInstance) {

        if (objectMapper != null && !createNewInstance)
            return objectMapper;

        ObjectMapper objectMapperInstance = new ObjectMapper();
        objectMapperInstance.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapperInstance.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapperInstance.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapperInstance.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapperInstance.registerModule(new JodaModule());
        objectMapperInstance.registerModule(new GuavaModule());
        objectMapperInstance.setSubtypeResolver(new StdSubtypeResolver());
        objectMapperInstance.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        objectMapperInstance.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // newInstance request should not override global ObjectMapper instance
        if (!createNewInstance)
            objectMapper = objectMapperInstance;

        return objectMapperInstance;
    }

    public static String JsonifyData(String data) {
        if (Strings.isNullOrEmpty(data)) {
            data = "";
        } else {
            data = UrlEncode(data.trim());
        }
        return data;
    }

    public static String UrlEncode(String data) {
        if (!Strings.isNullOrEmpty(data)) {
            try {
                data = URLEncoder.encode(data.trim(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                //logger.error("Error while encoding data: " + data, e);
                data = "";
            }
        }
        return data;
    }
}

