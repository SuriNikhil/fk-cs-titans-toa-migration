//package com.flipkart.cp.transact.toa.api.config;
//
//import com.flipkart.cp.transact.toa.util.common.Constants;
//import com.flipkart.kloud.authn.filter.AuthnFilterBuilder;
//import com.flipkart.kloud.authn.filter.PreAuthAuthenticationProvider;
//import com.flipkart.kloud.authn.filter.PreAuthProcessingFilter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
//
//import javax.servlet.Filter;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.Properties;
//
///**
// * Created by padmanabh.kulkarni on 06/08/15.
// */
//@Configuration
//@EnableWebSecurity
//@EnableWebMvcSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class AuthConfig extends WebSecurityConfigurerAdapter {
//    private static final Logger log = LoggerFactory.getLogger(AuthConfig.class);
//
//    private static String AUTH_URL;
//    private static String CLIENT_ID;
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
//                AUTH_URL = toaProperties.getProperty("AUTH_URL");
//                CLIENT_ID = toaProperties.getProperty("CLIENT_ID");
//                log.info("Static block - setTOAProperties -  AUTH_URL {}, CLIENT_ID {}", AUTH_URL, CLIENT_ID);
//            } else {
//                log.error("Static block - setTOAProperties - error in setting TOA properties");
//            }
//
//        } catch (Exception e) {
//            log.error("Static block - Error in setting TOA properties {} {} {}", e, e.getMessage(), e.getStackTrace());
//        }
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(new PreAuthAuthenticationProvider());
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
////        System.out.println("getting called");
//        //todo: enable auth
//        http.authorizeRequests()
//                .anyRequest().permitAll();
//        http.csrf().disable();
//
//
//    }
//
//    private Filter preAuthFilter() throws Exception {
//        PreAuthProcessingFilter filter = new PreAuthProcessingFilter();
//        filter.setAuthenticationManager(authenticationManager());
//
//        return filter;
//    }
//
//    private Filter authnFilter() {
//        String [] authURLs = {AUTH_URL};
//
//        try {
//            return new AuthnFilterBuilder()
//                    .setAuthnUrls(authURLs)
//                    .setClientId(CLIENT_ID)
//                    .setPrivateKeyPath(Constants.PRIVATE_KEY_PATH)
//                    .enableSystemAuth()
//                    .build();
//        } catch (Exception e) {
//            log.error("Exception e {} {}");
//        }
//        return null;
//    }
//
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//}