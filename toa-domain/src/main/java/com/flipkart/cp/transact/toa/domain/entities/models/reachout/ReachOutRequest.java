package com.flipkart.cp.transact.toa.domain.entities.models.reachout;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Created by padmanabh.kulkarni on 17/02/16.
 */
public class ReachOutRequest {
    @NotNull
    @JsonProperty("request_id")
    String requestId;

    @NotNull
    @JsonProperty("event_name")
    String eventName;

    @NotNull
    @JsonProperty("event_time")
    Long eventTime;

    @JsonProperty("context")
    Map<String, Object> context;

    @JsonProperty("event_count")
    Integer eventCount;

    @JsonProperty("event_data")
    List<String> eventData;

    public ReachOutRequest() {
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getEventTime() {
        return this.eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

    public Map<String, Object> getContext() {
        return this.context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public Integer getEventCount() {
        return this.eventCount;
    }

    public void setEventCount(Integer eventCount) {
        this.eventCount = eventCount;
    }

    public List<String> getEventData() {
        return this.eventData;
    }

    public void setEventData(List<String> eventData) {
        this.eventData = eventData;
    }


}
