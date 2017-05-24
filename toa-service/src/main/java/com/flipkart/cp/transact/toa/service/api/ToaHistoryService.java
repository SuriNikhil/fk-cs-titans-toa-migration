package com.flipkart.cp.transact.toa.service.api;

import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
import com.flipkart.cp.transact.toa.domain.entities.models.toa.TOAHistory;
import com.flipkart.cp.transact.toa.domain.enums.ChangeReason;
import com.flipkart.cp.transact.toa.domain.enums.ChangedAttribute;
import com.flipkart.cp.transact.toa.domain.enums.Event;
import com.flipkart.cp.transact.toa.domain.enums.Status;

/**
 * Created by padmanabh.kulkarni on 11/06/15.
 */
public interface ToaHistoryService {
    /**
     *
     * @param toaHistory
     * @return
     */
    Integer insertInTOAHistory(TOAHistory toaHistory);

    /**
     *
     * @param toaId
     * @return
     */
    TOAHistory getTOAStatus(Integer toaId);

    /**
     *
     * @param toa
     * @param changedAttribute
     * @param event
     * @param status
     * @param userLogin
     * @param changeReason
     * @param changeSubReason
     */
    void updateToaStatusAndAddToTOAHistory(Toa toa, ChangedAttribute changedAttribute, Event event, Status status, String userLogin, ChangeReason changeReason, String changeSubReason);

    Integer addToToaHistory(Toa toa, ChangedAttribute changedAttribute, Event event, Status status, String userLogin, ChangeReason changeReason, String changeSubReason);

}
