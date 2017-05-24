package com.flipkart.cp.transact.toa.dao;

import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
import com.flipkart.cp.transact.toa.domain.entities.models.common.Merchant;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by padmanabh.kulkarni on 26/05/15.
 */
public class ToaDAO extends AbstractDAO<Toa> {

    public ToaDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    //    @Insert(" INSERT INTO toa " +
//            "(" +
//                " toa_reference_id," +
//                " reference_entity_type," +
//                " reference_entity_id," +
//                " sub_entity_reference_type," +
//                " sub_entity_reference_id," +
//                " toa_reason," +
//                " toa_amount," +
//                " toa_mode," +
//                " toa_type," +
//                " comment," +
//                " user_login," +
//                " quantity," +
//                " status," +
//                " created_at," +
//                " init_date," +
//                " customer_account_id," +
//                " customer_request_id," +
//                " payment_reference_number," +
//                " external_payment_id," +
//                " promise_date," +
//                " updated_at," +
//                " seller_id," +
//                " tenant_id," +
//                " external_reference_id," +
//                " merchant_id," +
//                " channel" +
//            ") " +
//            "VALUES (" +
//                " #{toaReferenceId}," +
//                " #{referenceEntityType}," +
//                " #{referenceEntityId}," +
//                " #{subEntityReferenceType}," +
//                " #{subEntityReferenceId}," +
//                " #{toaReason}," +
//                " #{toaAmount}," +
//                " #{toaMode}," +
//                " #{toaType}," +
//                " #{comment}," +
//                " #{userLogin}," +
//                " #{quantity}," +
//                " #{status}," +
//                " now()," +
//                " #{initDate}," +
//                " #{customerAccountId}," +
//                " #{customerRequestId}," +
//                " #{paymentReferenceNumber}," +
//                " #{externalPaymentId}," +
//                " #{promiseDate}," +
//                " #{updatedAt}," +
//                " #{sellerId}," +
//                " #{tenantId}," +
//                " #{externalReferenceId}," +
//                " #{merchantId}," +
//                " #{channel}" +
//            ")")
//    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    public Integer insertInToa(Toa toa) {
        persist(toa);
        //TODO issue in muliptle hits if not transactional
        String sql = "Select LAST_INSERT_ID() from toa";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        return (Integer) sqlQuery.list().get(0);
    }

    //    @Select(" SELECT " +
//            " * " +
//            " FROM toa toa " +
//            " WHERE toa.id = #{id}")
    public Toa getToaById(String id) {

        String sql = "select * from toa where id=:id";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.addEntity(Merchant.class);
        sqlQuery.setParameter("id", id);
        //TODO check if it does nit exists or is it the right way to get single output
        return (Toa) sqlQuery.list().get(0);
    }

    //    @Update("<script>UPDATE toa " +
//            " <set>" +
//            "<if test=\"initDate != null \"> init_date = #{initDate},</if>" +
//            "<if test=\"updatedAt != null \"> updated_at = #{updatedAt},</if>" +
//            "<if test=\"status != null \"> status = #{status}, </if>" +
//            "<if test=\"externalPaymentId != null \"> external_payment_id = #{externalPaymentId}, </if>" +
//            "<if test=\"paymentReferenceNumber != null \"> payment_reference_number = #{paymentReferenceNumber} </if>" +
//            " </set>" +
//            " WHERE" +
//            " id = #{id}</script>")
    //TODO
    public Integer updateTOA(Toa toa) {
        return null;
    }

    //    @Select(" SELECT * FROM toa WHERE toa_reference_id =#{toa_reference_id}")
    public Toa getToaByToaReferenceId(String toaReferenceId) {
        String query = "select * from toa where toa_reference_id= : toaReferenceId";
        SQLQuery sqlQuery = currentSession().createSQLQuery(query);
        sqlQuery.addEntity(Toa.class);
        sqlQuery.setParameter("toaReferenceId", toaReferenceId);
        return (Toa) sqlQuery.list().get(0);
    }

    //    @Select(" SELECT * " +
//            " FROM toa toa " +
//            " WHERE toa.id IN" +
//            " (" +
//            " SELECT toa.id" +
//            " FROM toa toa " +
//            " WHERE toa.reference_entity_id =#{reference}" +
//            " OR toa.toa_reference_id =#{reference}" +
//            " OR toa.external_reference_id =#{reference}" +
//            " OR toa.sub_entity_reference_id =#{reference}" +
//            " )")
    public List<Toa> getToaByReference(String reference) {
        String query = "select * from toa toa where toa.id in (select toa.id from toa toa where" +
                " toa.reference_entity_id=: reference" +
                "or toa.reference.id=:reference" +
                "or external_reference_id=: reference" +
                "or sub_entity_reference=:reference);";
        SQLQuery sqlQuery = currentSession().createSQLQuery(query);
        sqlQuery.addEntity(Toa.class);
        sqlQuery.setParameter("reference", reference);
        return (List<Toa>) sqlQuery.list();
    }

    //    @Select("<script>SELECT " +
//            " CASE WHEN #{subEntityReferenceType} = 'ORDER_ITEM_UNIT' THEN SUM(toa_amount/quantity) ELSE SUM(toa_amount) END AS toaSum" +
//            " FROM toa" +
//            " <trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \"> " +
//            " (toa.status != 'FAILED' AND toa.status != 'CANCELLED')" +
//            " <if test =\"referenceEntityId != null\">AND toa.reference_entity_id = #{referenceEntityId}</if>" +
//            " <if test =\"referenceEntityType != null\"> AND toa.reference_entity_type = #{referenceEntityType}</if>" +
//            " <if test =\"subEntityReferenceId != null\"> AND toa.sub_entity_reference_id IN (${subEntityReferenceId})</if>" +
//            "</trim>" +
//            "</script>")
    //TODO query?
    public Double findPreviousToaSum(Toa toa) {
        return null;
    }

    //    @Select(" SELECT" +
//            " * " +
//            " FROM " +
//            " toa " +
//            " WHERE " +
//            " reference_entity_id = #{referenceEntityId} " +
//            " AND reference_entity_type = #{referenceEntityType}" +
//            " ORDER BY id"
//    )
    public List<Toa> getToaByEntity(Toa toa) {
        String sql = "select * from toa where reference_entity_id=:referenceId" +
                "and reference_entity_type=:referenceEntityType" +
                "order by id";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.addEntity(Toa.class);
        sqlQuery.setParameter("referenceEntityType", toa.getReferenceEntityType());
        sqlQuery.setParameter("reference_entity_id", toa.getReferenceEntityId());
        return (List<Toa>) sqlQuery.list();
    }

    //    @Select(" SELECT " +
//            " *" +
//            " FROM " +
//            " toa " +
//            " WHERE " +
//            " customer_account_id = #{customerAccountId}" +
//            " AND customer_request_id = #{customerRequestId}" +
//            " AND customer_request_id is not null")
    public List<Toa> getToaByCustomerAccountIdAndCustomerRequestId(Toa toa) {
        String sql = "select * from toa where cutomer_account_id=:customerAccountId" +
                "And customer_request_id=:customerRequestId" +
                "and customer_request_id is not null";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.addEntity(Toa.class);
        sqlQuery.setParameter("customerAccountId", toa.getCustomerAccountId());
        sqlQuery.setParameter("customerRequestId", toa.getCustomerRequestId());
        return (List<Toa>) sqlQuery.list();
    }
}