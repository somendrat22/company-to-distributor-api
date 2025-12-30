package com.supplynext.company_api.constants;

public final class OperationNameConstants {

    /* ===================== COMPANY ===================== */
    public static final String COMPANY_CREATE = "COMPANY_CREATE";
    public static final String COMPANY_VIEW = "COMPANY_VIEW";
    public static final String COMPANY_UPDATE = "COMPANY_UPDATE";
    public static final String COMPANY_DELETE = "COMPANY_DELETE";
    public static final String COMPANY_ONBOARD = "COMPANY_ONBOARD";
    public static final String COMPANY_VERIFY = "COMPANY_VERIFY";
    public static final String COMPANY_ACTIVATE = "COMPANY_ACTIVATE";
    public static final String COMPANY_DEACTIVATE = "COMPANY_DEACTIVATE";

    /* ===================== PRODUCT ===================== */
    public static final String PRODUCT_CREATE = "PRODUCT_CREATE";
    public static final String PRODUCT_VIEW = "PRODUCT_VIEW";
    public static final String PRODUCT_UPDATE = "PRODUCT_UPDATE";
    public static final String PRODUCT_DELETE = "PRODUCT_DELETE";
    public static final String PRODUCT_PRICE_UPDATE = "PRODUCT_PRICE_UPDATE";
    public static final String PRODUCT_STOCK_UPDATE = "PRODUCT_STOCK_UPDATE";
    public static final String PRODUCT_BULK_UPLOAD = "PRODUCT_BULK_UPLOAD";

    /* ===================== PURCHASE ORDER ===================== */
    public static final String PO_CREATE = "PO_CREATE";
    public static final String PO_VIEW = "PO_VIEW";
    public static final String PO_UPDATE = "PO_UPDATE";
    public static final String PO_CANCEL = "PO_CANCEL";
    public static final String PO_APPROVE = "PO_APPROVE";
    public static final String PO_REJECT = "PO_REJECT";
    public static final String PO_CLOSE = "PO_CLOSE";

    /* ===================== SALES ORDER ===================== */
    public static final String SO_CREATE = "SO_CREATE";
    public static final String SO_VIEW = "SO_VIEW";
    public static final String SO_UPDATE = "SO_UPDATE";
    public static final String SO_CANCEL = "SO_CANCEL";
    public static final String SO_DISPATCH = "SO_DISPATCH";
    public static final String SO_DELIVER = "SO_DELIVER";

    /* ===================== INVENTORY ===================== */
    public static final String INVENTORY_VIEW = "INVENTORY_VIEW";
    public static final String INVENTORY_ADD_STOCK = "INVENTORY_ADD_STOCK";
    public static final String INVENTORY_REMOVE_STOCK = "INVENTORY_REMOVE_STOCK";
    public static final String INVENTORY_ADJUSTMENT = "INVENTORY_ADJUSTMENT";
    public static final String INVENTORY_TRANSFER = "INVENTORY_TRANSFER";

    /* ===================== USER ===================== */
    public static final String USER_CREATE = "USER_CREATE";
    public static final String USER_VIEW = "USER_VIEW";
    public static final String USER_UPDATE = "USER_UPDATE";
    public static final String USER_DELETE = "USER_DELETE";
    public static final String USER_ASSIGN_ROLE = "USER_ASSIGN_ROLE";
    public static final String USER_REVOKE_ROLE = "USER_REVOKE_ROLE";

    /* ===================== PAYMENT ===================== */
    public static final String PAYMENT_INITIATE = "PAYMENT_INITIATE";
    public static final String PAYMENT_RECEIVE = "PAYMENT_RECEIVE";
    public static final String PAYMENT_REFUND = "PAYMENT_REFUND";
    public static final String PAYMENT_FAILED = "PAYMENT_FAILED";

    /* ===================== SECURITY ===================== */
    public static final String INVITE_EMPLOYEE = "INVITE_EMPLOYEE";
    public static final String CREATE_ROLE = "CREATE_ROLE";
    public static final String VIEW_ROLE = "VIEW_ROLE";
    public static final String VIEW_OPERATIONS = "VIEW_OPERATIONS";
}
