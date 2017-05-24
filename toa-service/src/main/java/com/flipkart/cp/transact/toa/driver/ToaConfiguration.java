package com.flipkart.cp.transact.toa.driver;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by nikhil.suri on 22/05/17.
 */
public class ToaConfiguration extends Configuration {




    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database;


    public DataSourceFactory getDatabaseAppDataSourceFactory() {
        return database;
    }

    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }



}
