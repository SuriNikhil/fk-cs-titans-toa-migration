package com.flipkart.cp.transact.toa.actions.models.request;

import com.flipkart.cp.transact.toa.domain.entities.models.common.OutboundMessage;

import java.util.Map;

/**
 * Created by padmanabh.kulkarni on 28/06/15.
 */
public class MessageQueueRequest {

    private OutboundMessage outboundMessage;

    private Map<String, String> headers;

    public MessageQueueRequest(){}

    public MessageQueueRequest(OutboundMessage outboundMessage, Map<String, String> headers){
        this.outboundMessage = outboundMessage;
        this.headers = headers;
    }

    public OutboundMessage getOutboundMessage() {
        return outboundMessage;
    }

    public void setOutboundMessage(OutboundMessage outboundMessage) {
        this.outboundMessage = outboundMessage;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
