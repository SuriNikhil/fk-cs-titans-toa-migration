package com.flipkart.cp.transact.toa.dao;

import com.flipkart.cp.transact.toa.domain.entities.models.toa.TOAHistory;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

/**
 * Created by padmanabh.kulkarni on 29/05/15.
 */
public class TOAHistoryDAO extends AbstractDAO<TOAHistory> {

    public TOAHistoryDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    //
//    @Insert(" INSERT INTO toa_history " +
//            " (" +
//            " id," +
//            " toa_id," +
//            " changed_attribute," +
//            " event," +
//            " new_status," +
//            " user_login," +
//            " change_reason," +
//            " change_sub_reason," +
//            " change_time," +
//            " change_data," +
//            " is_bigfoot_processed" +
//            " )" +
//            "VALUES" +
//            "(" +
//            " #{id}," +
//            " #{toaId}," +
//            " #{changedAttribute}," +
//            " #{event}," +
//            " #{newStatus}," +
//            " #{userLogin}," +
//            " #{changeReason}," +
//            " #{changeSubReason}," +
//            " now()," +
//            " #{changeData}," +
//            " #{isBigfootProcessed}" +
//            ")"
//
//    )
//    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    public Integer insertInTOAHistory(TOAHistory toaHistory) {
        persist(toaHistory);
        String sql = "Select LAST_INSERT_ID() from transaction";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        return (Integer) sqlQuery.list().get(0);
    }

    //    @Select(" SELECT " +
//            " MAX(id)," +
//            " toa_id," +
//            " changed_attribute," +
//            " event," +
//            " new_status," +
//            " user_login," +
//            " change_reason," +
//            " change_sub_reason," +
//            " change_time," +
//            " change_data," +
//            " is_bigfoot_processed" +
//            " FROM" +
//            " toa_history" +
//            " WHERE" +
//            " toa_id = #{toaId} ")
    public TOAHistory getToaStatus(Integer toaId) {
        String sql = "select max(id),toa_id,changed_attribute,event,"
                + "new_status,user_login,change_reason,change_sub_reason"
                + "change_time,change_data,is_bigfoot_processed "
                + "from toa_history where toa_id=: toaId";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.addEntity(TOAHistory.class);
        sqlQuery.setParameter("id", toaId);
        return (TOAHistory) sqlQuery.list().get(0);
    }

}
