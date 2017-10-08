package com.nordnet.batchsecuritoo.batch.strategy;

import com.nordnet.batchsecuritoo.destination.entity.Customer;
import com.nordnet.batchsecuritoo.source.entity.SecuritooCustomer;

public interface ActualizationStrategy {

    Customer getCustomer(SecuritooCustomer securitooCustomer);

}
