package com.flipkart.cp.transact.toa.dao;

import com.flipkart.cp.transact.toa.domain.entities.models.common.OutboundMessage;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;


import java.util.List;

/**
 * Created by padmanabh.kulkarni on 29/05/15.
 */
public class OutboundMessageDAO extends AbstractDAO<OutboundMessage> {


    public OutboundMessageDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * @param outboundMessage
     * @return
     */
//    @Insert(" INSERT INTO outbound_messages " +
//            " (" +
//                " id," +
//                " message_id," +
//                " relayed," +
//                " relayed_at," +
//                " exchange_name," +
//                " message," +
//                " created_at," +
//                " updated_at," +
//                " inbound_message_id," +
//                " exchange_type," +
//                " app_id," +
//                " correlation_id," +
//                " group_id," +
//                " http_method," +
//                " http_uri," +
//                " reply_to," +
//                " reply_to_http_method," +
//                " reply_to_http_uri," +
//                " txn_id," +
//                " routing_key," +
//                " context," +
//                " destination_response_status," +
//                " relay_error," +
//                " retries," +
//                " custom_headers" +
//            " )" +
//            " VALUES" +
//            " (" +
//                " #{id}," +
//                " #{messageId}," +
//                " #{relayed}," +
//                " #{relayedAt}," +
//                " #{exchangeName}," +
//                " #{message}," +
//                " now()," +
//                " #{updatedAt}," +
//                " #{inboundMessageId}," +
//                " #{exchangeType}," +
//                " #{appId}," +
//                " #{correlationId}," +
//                " #{groupId}," +
//                " #{httpMethod}," +
//                " #{httpUri}," +
//                " #{replyTo}," +
//                " #{replyToHttpMethod}," +
//                " #{replyToHttpUri}," +
//                " #{txnId}," +
//                " #{routingKey}," +
//                " #{context}," +
//                " #{destinationResponseStatus}," +
//                " #{relayError}," +
//                " #{retries}," +
//                " #{customHeaders}" +
//            " )"
//    )
//    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    public Integer insertInOutboundMessage(OutboundMessage outboundMessage) {
        persist(outboundMessage);
        String sql = "Select LAST_INSERT_ID() from transaction";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        return (Integer) sqlQuery.list().get(0);
    }

    //    /**
//     *
//     * @param outboundMessage
//     * @return
//     */
//    @Select(" SELECT * FROM outbound_messages" +
//            " WHERE relayed =#{relayed}"
//            )
    public List<OutboundMessage> getOutboundMessages(OutboundMessage outboundMessage) {
        String sql = "select * from outbound_messages where relayed=:relayed";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.addEntity(OutboundMessage.class);
        sqlQuery.setParameter("relayed", outboundMessage.getRelayed());
        return (List<OutboundMessage>) sqlQuery.list();

    }

    //    /**
//     *
//     * @param outboundMessage
//     * @return
//     */
//    @Update("<script>UPDATE outbound_messages " +
//            "<set>" +
//                "<if test=\"1 != 2\"> relayed = #{relayed},</if>" +
//                "<if test=\" destinationResponseStatus != null \"> destination_response_status = #{destinationResponseStatus}, </if>" +
//                "<if test=\" relayError != null \"> relay_error = #{relayError},</if>" +
//                "<if test=\" updatedAt != null \"> updated_at = #{updatedAt}</if>" +
//            "</set>" +
//            " WHERE id = #{id} " +
//            " OR message_id = #{messageId}" +
//            "</script>")
    //TODO update query
    public Integer updateOutboundMessage(OutboundMessage outboundMessage) {
        return null;
    }

    //
//    /**
//     *
//     * @param messageId
//     * @return
//     */
//    @Select("SELECT * FROM outbound_messages WHERE message_id = #{messageId}")
    public OutboundMessage getOutboundMessageByMessageId(String messageId) {
        String sql = "select * from outbound_messages where message_id=:messageId";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.addEntity(OutboundMessage.class);
        sqlQuery.setParameter("messageId", messageId);
        return (OutboundMessage) sqlQuery.list();
    }
}
