package com.nordnet.batchsecuritoo.batch.strategy;

import com.nordnet.batchsecuritoo.batch.strategy.internal.DeleteStrategy;
import com.nordnet.batchsecuritoo.batch.strategy.internal.UpdateStrategy;
import com.nordnet.batchsecuritoo.source.entity.SecuritooCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActualizationStrategyFactory {

    @Autowired
    private DeleteStrategy deleteStrategy;

    @Autowired
    private UpdateStrategy updateStrategy;

    public ActualizationStrategy getActualizationStrategy(SecuritooCustomer securitooCustomer) {
        if ("DELETE".equals(securitooCustomer.getAction())) {
            return deleteStrategy;
        } else if ("UPDATE".equals(securitooCustomer.getAction())|| "INSERT".equals(securitooCustomer.getAction())) {
            return updateStrategy;
        } else {
            throw new RuntimeException("Strategy d'actualisation non definie.");
        }
    }

}
