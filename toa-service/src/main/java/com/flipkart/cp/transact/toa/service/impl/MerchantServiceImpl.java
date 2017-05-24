package com.flipkart.cp.transact.toa.service.impl;

import com.flipkart.cp.transact.toa.dao.MerchantDAO;
import com.flipkart.cp.transact.toa.domain.entities.models.client.StatusResponse;
import com.flipkart.cp.transact.toa.domain.entities.models.common.Merchant;
import com.flipkart.cp.transact.toa.service.api.MerchantService;
import com.flipkart.cp.transact.toa.util.common.Constants;
import com.flipkart.cp.transact.toa.util.common.DataSecurityUtil;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Named;
import java.util.Date;

/**
 * Created by padmanabh.kulkarni on 01/02/16.
 */
@Named
@Slf4j
public class MerchantServiceImpl implements MerchantService {
   // private static final Logger log = LoggerFactory.getLogger(MerchantServiceImpl.class);

    private MerchantDAO merchantDAO;


    private DataSecurityUtil dataSecurityUtil;

    @Inject
    public MerchantServiceImpl(MerchantDAO merchantDAO, DataSecurityUtil dataSecurityUtil) {
        this.dataSecurityUtil = dataSecurityUtil;
        this.merchantDAO = merchantDAO;
    }


    @Override
    //TODO do we need this exception or unit of works handles it
    public StatusResponse addMerchant(Merchant merchant) /*throws DataIntegrityViolationException */ {
        Merchant existingMerchant = merchantDAO.getMerchant(merchant);

        if (existingMerchant != null) {
            log.info("Merchant with same name already exists with id {}", merchant.getId());
            return new StatusResponse(HttpStatus.SC_BAD_REQUEST, Constants.DUPLICATE_MERCHANT_DESCRIPTION);
        }

        log.info("Not a duplicate merchant");
        log.info("encrypting username");
        String encryptedString = dataSecurityUtil.doEncrypt(merchant.getUsername());
        merchant.setUsername(encryptedString);

        log.info("encrypting password");
        encryptedString = dataSecurityUtil.doEncrypt(merchant.getPassword());
        merchant.setPassword(encryptedString);
        merchant.setCreatedDate(new Date());
        Integer merchantId = merchantDAO.addMerchant(merchant);

        log.debug("merchant created with id {}", merchantId);

        return new StatusResponse(HttpStatus.SC_CREATED, Constants.MERCHANT_CREATED_DESCRIPTION);
    }

    @Override
    @UnitOfWork
    public Merchant getMerchantDetails(Merchant merchant) {
        return merchantDAO.getMerchant(merchant);
    }
}
