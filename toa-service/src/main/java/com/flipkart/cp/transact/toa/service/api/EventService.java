package com.flipkart.cp.transact.toa.service.api;

import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
import com.flipkart.cp.transact.toa.domain.entities.models.reachout.ReachOutResponse;

/**
 * Created by padmanabh.kulkarni on 17/12/15.
 */
public interface EventService {
    void pushEventToReachOut(Toa toa);

    Integer retryReachOutEvent(String messageId, String responseStatus, ReachOutResponse reachOutResponse);

    Integer reachOutEventSuccess(String messageId, ReachOutResponse reachOutResponse);
}
