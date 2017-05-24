package com.flipkart.cp.transact.toa.actions.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by padmanabh.kulkarni on 28/06/15.
 */
public class MessageQueueResponse {

    @JsonProperty("X_RESTBUS_MESSAGE_ID")
    private String messageId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
