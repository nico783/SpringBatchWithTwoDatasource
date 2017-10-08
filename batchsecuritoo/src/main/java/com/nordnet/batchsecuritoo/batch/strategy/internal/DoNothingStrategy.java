package com.nordnet.batchsecuritoo.batch.strategy.internal;

import com.nordnet.batchsecuritoo.batch.strategy.ActualizationStrategy;
import com.nordnet.batchsecuritoo.destination.entity.Customer;
import com.nordnet.batchsecuritoo.source.entity.SecuritooCustomer;
import org.springframework.stereotype.Component;

@Component
public class DoNothingStrategy implements ActualizationStrategy {

    /**
     * Un customer null est retourné. Dans ce cas le securitooCustomer ne sera pas traité par le batch.
     * @param securitooCustomer
     * @return null
     */
    @Override
    public Customer getCustomer(SecuritooCustomer securitooCustomer) {
        return null;
    }
}
