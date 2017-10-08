package com.nordnet.batchsecuritoo.batch;

import com.nordnet.batchsecuritoo.batch.strategy.ActualizationStrategy;
import com.nordnet.batchsecuritoo.batch.strategy.ActualizationStrategyFactory;
import com.nordnet.batchsecuritoo.destination.entity.Customer;
import com.nordnet.batchsecuritoo.source.entity.SecuritooCustomer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchSecuritooProcessor implements ItemProcessor<SecuritooCustomer, Customer> {

    @Autowired
    private ActualizationStrategyFactory strategyFactory;

    @Override
    public Customer process(SecuritooCustomer securitooCustomer) throws Exception {
        ActualizationStrategy actualizationStrategy = strategyFactory.getActualizationStrategy(securitooCustomer);
        return actualizationStrategy.getCustomer(securitooCustomer);
    }



}
