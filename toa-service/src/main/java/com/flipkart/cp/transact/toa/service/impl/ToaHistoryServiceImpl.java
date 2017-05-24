//package com.flipkart.cp.transact.toa.service.impl;
//
//import com.flipkart.cp.transact.toa.dao.TOAHistoryDAO;
//import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
//import com.flipkart.cp.transact.toa.domain.entities.models.toa.TOAHistory;
//import com.flipkart.cp.transact.toa.domain.enums.ChangeReason;
//import com.flipkart.cp.transact.toa.domain.enums.ChangedAttribute;
//import com.flipkart.cp.transact.toa.domain.enums.Event;
//import com.flipkart.cp.transact.toa.domain.enums.Status;
//import com.flipkart.cp.transact.toa.service.api.BigfootService;
//import com.flipkart.cp.transact.toa.service.api.ToaHistoryService;
//import com.flipkart.cp.transact.toa.service.api.ToaService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.util.Date;
//
///**
// * Created by padmanabh.kulkarni on 11/06/15.
// */
//@Named
//public class ToaHistoryServiceImpl implements ToaHistoryService {
//   // private static final Logger log = LoggerFactory.getLogger(ToaHistoryServiceImpl.class);
//
//
//    @Inject
//    private TOAHistoryDAO toaHistoryDAO;
//
//    @Inject
//    private BigfootService bigfootService;
//
//    @Inject
//    private ToaService toaService;
//
//    @Override
//    public Integer insertInTOAHistory(TOAHistory toaHistory) {
//        return toaHistoryDAO.insertInTOAHistory(toaHistory);
//    }
//
//    @Override
//    public TOAHistory getTOAStatus(Integer toaId) {
//        return toaHistoryDAO.getToaStatus(toaId);
//    }
//
//    @Override
//    public void updateToaStatusAndAddToTOAHistory(Toa toa, ChangedAttribute changedAttribute, Event event, Status status, String userLogin, ChangeReason changeReason, String changeSubReason) {
//
//        //update toa status
//        toa.setStatus(status);
//        toa.setUpdatedAt(new Date());
//        toaService.updateTOA(toa);
//
//        addToToaHistory(toa, changedAttribute, event, status, userLogin, changeReason, changeSubReason);
//
//    }
//
//    @Override
//    public Integer addToToaHistory(Toa toa, ChangedAttribute changedAttribute, Event event, Status status, String userLogin, ChangeReason changeReason, String changeSubReason) {
//        TOAHistory toaHistory = new TOAHistory(null, toa.getId(), changedAttribute, event, status, userLogin, changeReason, changeSubReason, new Date(), null, 0);
//
//        insertInTOAHistory(toaHistory);
//       // log.info("Inserted into toa_history with id {}", toaHistory.getId());
//
//        return bigfootService.addToOutboundMessage(toa, toaHistory);
//    }
//}
