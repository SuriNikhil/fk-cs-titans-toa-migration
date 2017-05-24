package com.flipkart.cp.transact.toa.util.common;

import com.flipkart.cp.convert.simple.proxy.SimpleProxy;
import com.flipkart.cp.convert.simple.proxy.conf.ProxyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by padmanabh.kulkarni on 01/07/15.
 */
public class SimpleProxyUtil {
    private static final Logger log = LoggerFactory.getLogger(SimpleProxyUtil.class);

    public static void startServiceProxy() throws Exception{
        //picking proxyConfig based on build environment
//        log.info("reading proxyConfJson from file");
//        InputStream inputStream = SimpleProxyUtil.class.getClassLoader().getResourceAsStream(Constants.PROXY_CONFIG_FILE_LOCATION);
//
//        File proxyConf = new File("proxyConf.json");
//        OutputStream outputStream = new FileOutputStream(proxyConf);
//
//        IOUtils.copy(inputStream, outputStream);
//        outputStream.close();

        File file = new File(Constants.PROXY_CONFIG_FILE_LOCATION);
        FileInputStream fis = new FileInputStream(file);
        log.info("mapping json to proxyConfig");

        ProxyConfig proxyConfig = JsonUtils.getObjectMapper().readValue(fis, ProxyConfig.class);

        log.debug("proxyConfig {}", JsonUtils.getObjectMapper().writeValueAsString(proxyConfig.toString()));

        log.info("trying to start simple proxy");
        try {
            SimpleProxy.PROXY.start(proxyConfig);
        } catch (Exception e) {
            log.warn("Probable double initialization of simple proxy. Ignoring the exception");
        }

//        proxyConf.delete();
    }

}
