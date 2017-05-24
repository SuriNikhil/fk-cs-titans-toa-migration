package com.flipkart.cp.transact.toa.service.api;

import com.flipkart.cp.transact.toa.domain.entities.models.common.OutboundMessage;

import java.util.List;

/**
 * Created by padmanabh.kulkarni on 11/06/15.
 */
public interface OutboundMessageService {

    /**
     *
     * @param outboundMessage
     * @return
     */
    Integer insertInOutboundMessage(OutboundMessage outboundMessage);

    Integer createAndInsertInOutboundMessage(Object entity, String httpUri, String httpMethod, String replyToHttpMethod, String callbackURI, String customHeaders, String groupId, String messageId, String transactionId, String correlationId);

    /**
     *
     * @param outboundMessage
     * @return
     */
    Integer updateOutboundMessage(OutboundMessage outboundMessage);

    /**
     *
     * @param outboundMessage
     * @return
     */
    List<OutboundMessage> getOutboundMessages(OutboundMessage outboundMessage);

    /**
     *
     * @param messageId
     * @return
     */
    OutboundMessage getOutboundMessageByMessageId(String messageId);
}
