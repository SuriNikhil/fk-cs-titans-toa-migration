package com.flipkart.cp.transact.toa.domain.entities.models.client;

/**
 * Created by padmanabh.kulkarni on 21/09/15.
 */
public class HealthCheckResponse {

    private long uptime;
    private Integer requests;
    private Integer capacity;

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public Integer getRequests() {
        return requests;
    }

    public void setRequests(Integer requests) {
        this.requests = requests;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
