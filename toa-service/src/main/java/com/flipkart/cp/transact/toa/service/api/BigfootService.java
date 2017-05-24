package com.flipkart.cp.transact.toa.service.api;

import com.flipkart.cp.transact.toa.domain.entities.models.bigfoot.BigfootResponse;
import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
import com.flipkart.cp.transact.toa.domain.entities.models.toa.TOAHistory;

/**
 * Created by padmanabh.kulkarni on 21/06/15.
 */
public interface BigfootService {
    Integer addToOutboundMessage(Toa toa, TOAHistory toaHistory);

    void bigfootActionOnSuccess(String messageId);

    void bigfootActionOnFailure(BigfootResponse bigfootResponse, String messageId, Integer destinationResponseStatus);
}
