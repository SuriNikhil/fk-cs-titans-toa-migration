package com.flipkart.cp.transact.toa.service.api;

import com.flipkart.cp.transact.toa.domain.entities.models.client.StatusResponse;
import com.flipkart.cp.transact.toa.domain.entities.models.common.Merchant;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Created by padmanabh.kulkarni on 01/02/16.
 */
public interface MerchantService {
    //TODO check for exception
    StatusResponse addMerchant(Merchant merchant) throws SQLIntegrityConstraintViolationException;

    Merchant getMerchantDetails(Merchant merchant);
}
