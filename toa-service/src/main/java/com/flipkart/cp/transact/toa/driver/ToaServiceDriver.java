package com.flipkart.cp.transact.toa.driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.flipkart.cp.transact.toa.domain.entities.models.client.HealthCheckResponse;
import com.flipkart.cp.transact.toa.domain.entities.models.common.Merchant;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.joda.time.DateTime;

import javax.validation.Validator;

/**
 * Created by nikhil.suri on 19/05/17.
 */
public class ToaServiceDriver extends Application<ToaConfiguration> {


    private static ToaServiceDriver driver;
    private Injector injector;
    private HibernateBundle<ToaConfiguration> hibernate;
    private GuiceBundle<ToaConfiguration> guiceBundle;


    @Override
    public void initialize(Bootstrap<ToaConfiguration> bootstrap) {
        //initiallise hibernate bundle
        hibernate =
                new HibernateBundle<ToaConfiguration>(Merchant.class) {
                    public DataSourceFactory getDataSourceFactory(ToaConfiguration configuration) {
                        return configuration.getDatabaseAppDataSourceFactory();
                    }
                };
        //initiallise guice
        guiceBundle = GuiceBundle.<ToaConfiguration>newBuilder()
                .addModule(new ToaApiModule(hibernate))
                .setConfigClass(ToaConfiguration.class)
                .enableAutoConfig("com.flipkart.cp.transact.toa.resources")
                .build();
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(guiceBundle);


    }

    @Override
    public String getName() {
        return "dropwizard-toa";
    }

    public static void main(String[] args) throws Exception {
        (new ToaServiceDriver()).run(args);
    }

    @Override
    public void run(ToaConfiguration toaConfiguration, Environment environment) throws Exception {
        //add resources to jersey not required for auto enable
       // environment.jersey().register(HealthCheckResource.class);

    }
}
