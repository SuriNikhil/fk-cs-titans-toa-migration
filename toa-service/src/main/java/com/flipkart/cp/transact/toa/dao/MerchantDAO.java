package com.flipkart.cp.transact.toa.dao;

import com.flipkart.cp.transact.toa.domain.entities.models.common.Merchant;
import com.google.inject.Inject;
import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by padmanabh.kulkarni on 28/01/16.
 */
public class MerchantDAO extends AbstractDAO<Merchant> {


    @Inject
    public MerchantDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    //    @Insert("INSERT INTO merchant " +
//            "(" +
//                " id," +
//                " name," +
//                " username," +
//                " password," +
//                " status," +
//                " description," +
//                " created_date," +
//                " updated_date" +
//            ")" +
//            " VALUES" +
//            "(" +
//                " null," +
//                " #{name}," +
//                " #{username}," +
//                " #{password}," +
//                " #{status}," +
//                " #{description}," +
//                " now()," +
//                " #{updatedDate}" +
//            ")")
//    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)

    public Integer addMerchant(Merchant merchant) {

        persist(merchant);
        //TODO issue in muliptle hits if not transactional
        String sql = "Select LAST_INSERT_ID() from merchant";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        return ((BigInteger) sqlQuery.list().get(0)).intValue();

    }

    //
//    @Select("SELECT " +
//                " * " +
//            "FROM" +
//                " merchant " +
//            "WHERE " +
//                " name = #{name}" +
//                " OR id = #{id} ")


    public Merchant getMerchant(Merchant merchant) {
        String sql = "select * from merchant where name=:name or id=:id";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.addEntity(Merchant.class);
        sqlQuery.setParameter("name", merchant.getName());
        sqlQuery.setParameter("id", merchant.getId());
        //TODO check if it does nit exists or is it the right way to get single output
        List response = sqlQuery.list();
        return  response.size()==0?null:(Merchant)response.get(0);
    }
}
