package com.flipkart.cp.transact.toa.service.api;

import com.flipkart.cp.transact.toa.domain.entities.models.client.RemainingToaRequest;
import com.flipkart.cp.transact.toa.domain.entities.models.client.RemainingToaResponse;
import com.flipkart.cp.transact.toa.domain.entities.models.client.Toa;
import com.flipkart.cp.transact.toa.domain.entities.models.oms.OrderResponse;
import com.flipkart.cp.transact.toa.domain.entities.models.payment.PaymentCallback;
import com.flipkart.cp.transact.toa.domain.exceptions.BadRequestException;
import com.flipkart.cp.transact.toa.domain.exceptions.InvalidMerchantException;
import com.flipkart.cp.transact.toa.domain.exceptions.OMSCallException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * Created by padmanabh.kulkarni on 10/06/15.
 */

public interface ToaService {
    /**
     *
     * @param toa
     * @return
     */
    //TODO throw this instead  DuplicateKeyException
    Integer insertInToa(Toa toa) throws SQLIntegrityConstraintViolationException;

    /**
     *
     * @param toa
     * @return
     */
    Integer updateTOA(Toa toa);

    /**
     *
     * @param id
     * @return
     */
    //todo check why string
    Toa getTOAById(String id);

    /**
     *
     * @param toaReferenceId
     * @return
     */
    Toa getTOAByToaReferenceId(String toaReferenceId);

    /**
     *
     * @param toa
     */
    //TODO check for SQLIntegrityConstraintViolationException
    boolean processTOA(Toa toa) throws SQLIntegrityConstraintViolationException, BadRequestException, OMSCallException, InvalidMerchantException;

    /**
     *
     * @param paymentCallback
     * @return
     */
    Toa processCallback(PaymentCallback paymentCallback, Toa toa);

    /**
     *
     * @param reference
     * @return
     */
    List<Toa> getToaByReference(String reference);

    /**
     *
     * @param toa
     * @param paymentCallback
     * @param destinationResponseStatus
     * @param messageId
     */
    void actionOnPaymentFailure(Toa toa, PaymentCallback paymentCallback, String destinationResponseStatus, String messageId);

    Double findPreviousToaSum(Toa toa);

    List<Toa> getToaByEntity(Toa toa);

    boolean markCompletionOfTOA(Toa toa);

    RemainingToaResponse findRemainingToa(RemainingToaRequest remainingToaRequest, Boolean toaOverride, OrderResponse orderResponse) throws BadRequestException;

    boolean isUniqueCustomerIdAndCustomerRequestId(Toa toa);
}
