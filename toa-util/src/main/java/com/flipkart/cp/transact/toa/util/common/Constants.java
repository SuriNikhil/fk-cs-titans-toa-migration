package com.flipkart.cp.transact.toa.util.common;

/**
 * Created by padmanabh.kulkarni on 03/06/15.
 */
public class Constants {

    public static final String TOA_HISTORY_CHANGED_ATTRIBUTE_STATUS = "STATUS";

    public static final String USER_LOGIN_SYSTEM = "SYSTEM";

    //RELAY
    public static final Integer RELAYED = 1;
    public static final Integer NOT_RELAYED = 0;

    //Outbound message group ids
    public static final String BIGFOOT_MESSAGE_GROUP_ID = "BIGFOOT_MESSAGE_GROUP";
    public static final String CREDIT_MESSAGE_GROUP_ID = "CREDIT_MESSAGE_GROUP";
    public static final String PAYMENT_MESSAGE_GROUP_ID = "PAYMENT_MESSAGE_GROUP";
    public static final String WALLET_MESSAGE_GROUP_ID = "WALLET_MESSAGE_GROUP";
    public static final String REACH_OUT_EVENT_GROUP_ID = "REACH_OUT_MESSAGE_GROUP";
    public static final String GCMS_CREATE_TRANSACTION_GROUP_ID = "GCMS_CREATE_TRANSACTION_GROUP";
    public static final String GCMS_CREATE_EGV_GROUP_ID = "GCMS_CREATE_EGV_GROUP";
    public static final String EGV_ADD_TO_WALLET_GROUP_ID = "EGV_ADD_TO_WALLET_GROUP";

    //Bigfoot response messages
    public static final String BIGFOOT_RESPONSE_SUCCESS = "SUCCESS";
    public static final String BIGFOOT_RESPONSE_FAILURE = "FAILURE";

    //Wallet response messages
    public static final String WALLET_RESPONSE_TXN_STATUS_SUCCESS = "SUCCESS";
    public static final String WALLET_RESPONSE_TXN_STATUS_FAILED = "FAILED";

    //Wallet request params
    public static final String WALLET_REQUEST_CLIENT_ID = "w_flipkart";
    public static final String WALLET_REQUEST_CREDIT_TYPE = "promotional_balance";
    public static final String WALLET_REQUEST_TYPE_CREDIT = "credit";
    public static final String WALLET_VERSION = "1.0";
    public static final String HASH_KEY = "f30aa7a662c728b7";
    public static final String WALLET_CREDIT_PATH = "/wallet/fkwallet";

    public static final String TOA_PREFIX = "TOA";

    public static final String BIGFOOT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        public static final String REFUND_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss+'05:30'";

    public static final String USER_SERVICE_URL_PARAMS = "?require=%7B%22preferences%22%3Atrue%2C%22store%22%3Atrue%7D&requestId=";

    //Status messages
    public static final String INVALID_TOA_DESCRIPTION = "INVALID_TOA";
    public static final String CAN_NOT_COMPLETE_TOA_DESCRIPTION = "Sorry! Can not complete TOA with status ";
    public static final String MISSING_HEADERS_DESCRIPTION = "Missing one of the required headers.";
    public static final String DUPLICATE_TOA_REFERENCE_ID_DESCRIPTION = "Duplicate TOA reference id.";
    public static final String TOA_CREATED_DESCRIPTION = "TOA created successfully";
    public static final String MISSING_PARAMS_DESCRIPTION = "Missing Parameters";
    public static final String NO_DATA_FOUND_DESCRIPTION = "No data found for given request";
    public static final String SUCCESS_DESCRIPTION = "SUCCESS";
    public static final String TOA_LIMIT_MESSAGE = "Please enter a value less than or equal to ";
    public static final String TOA_LIMIT_REACHED = "TOA limit is reached";
    public static final String MERCHANT_CREATED_DESCRIPTION = "Merchant created successfully";
    public static final String DUPLICATE_MERCHANT_DESCRIPTION = "Merchant with same name already exists";
    public static final String INVALID_MERCHANT_DESCRIPTION = "Invalid/Inactive Merchant";
    public static final String TOA_MODE_INACTIVE_DESCRIPTION ="TOA mode is not active";

    //OMS constants
    public static final String OMS_GET_ORDERS_URL = "/orders/";
    public static final String OMS_GET_ORDER_ITEM_URL = "/orderItem/";
    public static final String OMS_GET_ORDER_ITEM_UNIT_URL = "/orderItemUnit/";

    public static final String OMS_BASE_URL = "/oms/1.0";
    public static final String SUB_ENTITY_TYPE_ORDER_ITEM = "order_item";
    public static final String SUB_ENTITY_TYPE_ORDER_ITEM_UNIT = "order_item_unit";
    public static final String ORDER_ITEM_UNIT_SUFFIX = "000";

    public static final String PRIVATE_KEY_PATH = "/var/lib/keystore/fk-toa-svc.pem";
    public static final String INVALID_QUANTITY = "Invalid quantity";
    public static final String INVALID_AMOUNT = "Invalid amount";
    public static final String INVALID_INPUT = "Invalid input";
    public static final String INVALID_EXTERNAL_REFERENCE_ID = "Invalid external reference Id";
    public static final String ENTITY_SUB_ENTITY_MISMATCH = "Reference entity and sub entity do not Match";
    public static final String ENTITY_CUSTOMER_MISMATCH = "Entity-Customer mismatch";
    public static final String ENTITY_SELLER_MISMATCH = "Entity-Seller mismatch";

    public static final String TOA_CONFIG_FILE = "/etc/toa-config/config.xml";
    public static final String TOA_REACH_OUT_EVENT_NAME = "TOA_ISSUED";
    public static final boolean BYPASS_SC_PAYMENTS = false;
    public static final String PROXY_CONFIG_FILE_LOCATION = "/etc/toa-config/proxyConf.json";
    public static final String TOA_SERVICE = "TOA-SERVICE";
    public static final String TOA_TOKEN = "TOA-TOKEN";
    public static final String AMOUNT_LESS_THAN_MINIMUM_EGV_DENOMINATION = "Amount less than minimum EGV denomination";
    public static final String AMOUNT_MORE_THAN_MAXIMUM_EGV_DENOMINATION = "Amount more than maximum EGV denomination";
    public static final String EXTERNAL_SERVICE_CALL_FAILED = "External service call failed";
    public static final String SERVER_ERROR_DESCRIPTION = "Server Error";
    public static final String DUPLICATE_CUSTOMER_ID_AND_REQUEST_ID_COMBINATION = "Duplicate request for customer id and customer request id";
    public static final String TRANSACTION_STATE_CONFLICT = "Conflict with transaction status";

    public static boolean KEEP_RELAYING;

    //QUEUE PROPERTIES
    public static final String OUTBOUND_MESSAGE_EXCHANGE_TYPE_QUEUE = "queue";
    public static final String TRANSACTION_ID_PREFIX = "TXN-";
    public static final Integer OUTBOUND_MESSAGE_RETRIES = 0;

    public static final String GCMS_CLIENT_ID_HEADER =  "X_FLIPKART_GIFTING_CLIENT_ID"; //"Flipkart-Gifting-Client-Id";
    public static final String GCMS_CLIENT_TOKEN_HEADER = "X_FLIPKART_GIFTING_CLIENT_TOKEN"; //"Flipkart-Gifting-Client-Token";

    public static String WALLET_MERCHANT_TXN_ID_SEPARATOR = "_";
}
