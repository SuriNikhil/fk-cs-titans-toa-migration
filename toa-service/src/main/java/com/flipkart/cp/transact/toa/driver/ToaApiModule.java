package com.flipkart.cp.transact.toa.driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.cp.transact.toa.service.api.MerchantService;
import com.flipkart.cp.transact.toa.service.impl.MerchantServiceImpl;
import com.flipkart.cp.transact.toa.util.common.DataSecurityUtil;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

import javax.validation.Validator;

/**
 * Created by nikhil.suri on 23/05/17.
 */
public class ToaApiModule extends AbstractModule {

    private HibernateBundle<ToaConfiguration> hibernateBundle;

    public ToaApiModule(HibernateBundle<ToaConfiguration> hibernateBundle) {
        this.hibernateBundle = hibernateBundle;
    }

    @Override
    protected void configure() {
        bind(MerchantService.class).to(MerchantServiceImpl.class);
    }

    @Provides
    public SessionFactory sessionFactoryProvider() {
        return hibernateBundle.getSessionFactory();
    }

    @Provides
    public DataSecurityUtil dataSecurityUtilProvider() {
        //TODO where to put the file ?
        return new DataSecurityUtil("/etc/toa-config/datasecure");
    }
}
