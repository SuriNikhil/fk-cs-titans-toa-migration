package com.flipkart.cp.transact.toa.domain.entities.models.bigfoot;

/**
 * Created by padmanabh.kulkarni on 21/06/15.
 */
public class BigfootEventRequest {

    private String eventId;
    private String eventTime;
    private String schemaVersion;
    private Object data;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

