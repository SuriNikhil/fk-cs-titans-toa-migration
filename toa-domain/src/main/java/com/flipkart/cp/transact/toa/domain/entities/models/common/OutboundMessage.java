package com.flipkart.cp.transact.toa.domain.entities.models.common;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by padmanabh.kulkarni on 26/05/15.
 */
@Entity
@Table(name = "outbound_messages")
public class OutboundMessage {

    private Integer id;
    private String messageId;
    private Integer relayed; //relay status
    private Date relayedAt; //relay datetime
    private String exchangeName;
    private String message;
    private Date createdAt;
    private Date updatedAt;
    private Integer inboundMessageId;
    private String exchangeType;
    private String appId;
    private String correlationId;
    private String groupId;
    private String httpMethod;
    private String httpUri;
    private String replyTo;
    private String replyToHttpMethod;
    private String replyToHttpUri;
    private String txnId;
    private String routingKey;
    private String context;
    private Integer destinationResponseStatus;
    private String relayError;
    private Integer retries;
    private String customHeaders;

  public OutboundMessage(){}

    public OutboundMessage(
            Integer id,
            String messageId,
            Integer relayed,
            Date relayedAt,
            String exchangeName,
            String message,
            Date createdAt,
            Date updatedAt,
            Integer inboundMessageId,
            String exchangeType,
            String appId,
            String correlationId,
            String groupId,
            String httpMethod,
            String httpUri,
            String replyTo,
            String replyToHttpMethod,
            String replyToHttpUri,
            String txnId,
            String routingKey,
            String context,
            Integer destinationResponseStatus,
            String relayError,
            Integer retries,
            String customHeaders
            ){
        this.id = id;
        this.messageId = messageId;
        this.relayed = relayed;
        this.relayedAt = relayedAt;
        this.exchangeName = exchangeName;
        this.message = message;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.inboundMessageId = inboundMessageId;
        this.exchangeType = exchangeType;
        this.appId = appId;
        this.correlationId = correlationId;
        this.groupId = groupId;
        this.httpMethod = httpMethod;
        this.httpUri = httpUri;
        this.replyTo = replyTo;
        this.replyToHttpMethod = replyToHttpMethod;
        this.replyToHttpUri = replyToHttpUri;
        this.txnId = txnId;
        this.routingKey = routingKey;
        this.context = context;
        this.destinationResponseStatus = destinationResponseStatus;
        this.relayError = relayError;
        this.retries = retries;
        this.customHeaders = customHeaders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Integer getRelayed() {
        return relayed;
    }

    public void setRelayed(Integer relayed) {
        this.relayed = relayed;
    }

    public Date getRelayedAt() {
        return relayedAt;
    }

    public void setRelayedAt(Date relayedAt) {
        this.relayedAt = relayedAt;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getInboundMessageId() {
        return inboundMessageId;
    }

    public void setInboundMessageId(Integer inboundMessageId) {
        this.inboundMessageId = inboundMessageId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpUri() {
        return httpUri;
    }

    public void setHttpUri(String httpUri) {
        this.httpUri = httpUri;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getReplyToHttpUri() {
        return replyToHttpUri;
    }

    public void setReplyToHttpUri(String replyToHttpUri) {
        this.replyToHttpUri = replyToHttpUri;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public Integer getDestinationResponseStatus() {
        return destinationResponseStatus;
    }

    public void setDestinationResponseStatus(Integer destinationResponseStatus) {
        this.destinationResponseStatus = destinationResponseStatus;
    }

    public String getRelayError() {
        return relayError;
    }

    public void setRelayError(String relayError) {
        this.relayError = relayError;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public String getCustomHeaders() {
        return customHeaders;
    }

    public void setCustomHeaders(String customHeaders) {
        this.customHeaders = customHeaders;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getReplyToHttpMethod() {
        return replyToHttpMethod;
    }

    public void setReplyToHttpMethod(String replyToHttpMethod) {
        this.replyToHttpMethod = replyToHttpMethod;
    }
}
