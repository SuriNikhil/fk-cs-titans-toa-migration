package com.flipkart.cp.transact.toa.dao;

import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
import com.flipkart.cp.transact.toa.domain.entities.models.common.Transaction;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by padmanabh.kulkarni on 28/01/16.
 */
public class TransactionDAO extends AbstractDAO<Transaction> {

    public TransactionDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    //    @Insert("INSERT INTO transaction " +
//            "(" +
//            " id," +
//            " toa_id," +
//            " merchant_id," +
//            " code," +
//            " amount," +
//            " status," +
//            " created_date," +
//            " update_date" +
//            ")" +
//            "VALUES" +
//            "(" +
//            " null," +
//            " #{toaId}," +
//            " #{merchantId}," +
//            " #{code}," +
//            " #{amount}," +
//            " #{status}," +
//            " now()," +
//            " #{updatedDate}" +
//            ")")
//    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
//
    public Integer addTransaction(Transaction transaction) {
        persist(transaction);
        String sql = "Select LAST_INSERT_ID() from transaction";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        return (Integer) sqlQuery.list().get(0);
    }

    //
//    @Select(" SELECT " +
//            " * " +
//            " FROM " +
//            "transaction" +
//            " WHERE " +
//            " id = #{id}" +
//            " OR code = #{code}"
//    )
    public Transaction getTransactionDetails(Transaction transaction) {
        String sql = "Select * from transaction where id=:id or code=:code";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.addEntity(Transaction.class);
        sqlQuery.setParameter("id", transaction.getId());
        return (Transaction) sqlQuery.list().get(0);
    }


    //    @Update("<script>UPDATE " +
//            " transaction" +
//            "<set>" +
//                "<if test=\" status != null \"> status = #{status}, </if>" +
//                "<if test=\" code != null \"> code = #{code}, </if>" +
//                "<if test=\" cardNumber != null \"> card_number = #{cardNumber} </if>" +
//            "</set>" +
//            " WHERE " +
//                " id=#{id}" +
//            "</script>"
//            )
    //TODO query
    public Integer updateTransaction(Transaction transaction) {
        return null;
    }

    //    @Select(" SELECT " +
//            " count(id) " +
//            " FROM transaction" +
//            " WHERE " +
//            " toa_id = #{toaId}" +
//            " AND status < #{status}")
    public Integer getTransactionState(Transaction transaction) {
        String sql = "Select count(id) from transaction where toa_id=:toaId and status< :status";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.addEntity(Transaction.class);
        sqlQuery.setParameter("toaId", transaction.getToaId());
        sqlQuery.setParameter("status", transaction.getStatus());
        return (Integer) sqlQuery.list().get(0);
    }

    //    @Select("<script>SELECT " +
//                " txn.id," +
//                " txn.toa_id," +
//                " txn.code," +
//                " txn.merchant_id," +
//                " txn.amount," +
//                " txn.status," +
//                " txn.card_number," +
//                " txn.created_date," +
//                " txn.update_date"+
//            " FROM " +
//                " transaction txn" +
//                " INNER JOIN toa toa on txn.toa_id = toa.id" +
//            "<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">" +
//                "<if test=\"referenceEntityId != null \"> toa.reference_entity_id = #{referenceEntityId} </if>" +
//                "<if test=\"referenceEntityType != null \"> AND toa.reference_entity_type = #{referenceEntityType} </if>" +
//                "<if test=\"toaReferenceId != null \"> AND toa.toa_reference_id =#{toaReferenceId} </if>" +
//                " AND txn.status != 4" +
//            "</trim>" +
//            " ORDER BY txn.toa_id" +
//            "</script>"
//    )
    //TODO query>?
    public List<Transaction> getTransactionsForToa(Toa toa) {
        return null;
    }
}
