package com.supplynext.company_api.jobs;

import com.supplynext.company_api.models.Operation;
import com.supplynext.company_api.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OperationScheduler {

    @Autowired
    OperationRepository operationRepository;


    @Scheduled(initialDelay = 5000, fixedDelay = Long.MAX_VALUE)
    public void insertOperations() {
        this.insertOperation("COMPANY_CREATE", "COMPANY");
        this.insertOperation("COMPANY_VIEW", "COMPANY");
        this.insertOperation("COMPANY_UPDATE", "COMPANY");
        this.insertOperation("COMPANY_DELETE", "COMPANY");
        this.insertOperation("COMPANY_ONBOARD", "COMPANY");
        this.insertOperation("COMPANY_VERIFY", "COMPANY");
        this.insertOperation("COMPANY_ACTIVATE", "COMPANY");
        this.insertOperation("COMPANY_DEACTIVATE", "COMPANY");
        this.insertOperation("PRODUCT_CREATE", "PRODUCT");
        this.insertOperation("PRODUCT_VIEW", "PRODUCT");
        this.insertOperation("PRODUCT_UPDATE", "PRODUCT");
        this.insertOperation("PRODUCT_DELETE", "PRODUCT");
        this.insertOperation("PRODUCT_PRICE_UPDATE", "PRODUCT");
        this.insertOperation("PRODUCT_STOCK_UPDATE", "PRODUCT");
        this.insertOperation("PRODUCT_BULK_UPLOAD", "PRODUCT");
        this.insertOperation("PO_CREATE", "PURCHASE_ORDER");
        this.insertOperation("PO_VIEW", "PURCHASE_ORDER");
        this.insertOperation("PO_UPDATE", "PURCHASE_ORDER");
        this.insertOperation("PO_CANCEL", "PURCHASE_ORDER");
        this.insertOperation("PO_APPROVE", "PURCHASE_ORDER");
        this.insertOperation("PO_REJECT", "PURCHASE_ORDER");
        this.insertOperation("PO_CLOSE", "PURCHASE_ORDER");
        this.insertOperation("SO_CREATE", "SALES_ORDER");
        this.insertOperation("SO_VIEW", "SALES_ORDER");
        this.insertOperation("SO_UPDATE", "SALES_ORDER");
        this.insertOperation("SO_CANCEL", "SALES_ORDER");
        this.insertOperation("SO_DISPATCH", "SALES_ORDER");
        this.insertOperation("SO_DELIVER", "SALES_ORDER");
        this.insertOperation("INVENTORY_VIEW", "INVENTORY");
        this.insertOperation("INVENTORY_ADD_STOCK", "INVENTORY");
        this.insertOperation("INVENTORY_REMOVE_STOCK", "INVENTORY");
        this.insertOperation("INVENTORY_ADJUSTMENT", "INVENTORY");
        this.insertOperation("INVENTORY_TRANSFER", "INVENTORY");
        this.insertOperation("USER_CREATE", "USER");
        this.insertOperation("USER_VIEW", "USER");
        this.insertOperation("USER_UPDATE", "USER");
        this.insertOperation("USER_DELETE", "USER");
        this.insertOperation("USER_ASSIGN_ROLE", "USER");
        this.insertOperation("USER_REVOKE_ROLE", "USER");
        this.insertOperation("PAYMENT_INITIATE", "PAYMENT");
        this.insertOperation("PAYMENT_RECEIVE", "PAYMENT");
        this.insertOperation("PAYMENT_REFUND", "PAYMENT");
        this.insertOperation("PAYMENT_FAILED", "PAYMENT");
        this.insertOperation("INVITE_EMPLOYEE", "SECURITY");
        this.insertOperation("CREATE_ROLE", "SECURITY");
        this.insertOperation("VIEW_ROLE", "SECURITY");
        this.insertOperation("VIEW_OPERATIONS", "SECURITY");
    }

    public void insertOperation(String operationName, String operationType) {

        // Prevent duplicate insert
        if (operationRepository.findByOperationName(operationName) != null) {
            return;
        }

        Operation operation = new Operation();
        operation.setOperationName(operationName);
        operation.setOperationType(operationType);

        operationRepository.save(operation);
    }
}
