package com.nordnet.batchsecuritoo.batch.strategy.internal;

import com.nordnet.batchsecuritoo.batch.strategy.ActualizationStrategy;
import com.nordnet.batchsecuritoo.destination.entity.Customer;
import com.nordnet.batchsecuritoo.destination.service.CustomerService;
import com.nordnet.batchsecuritoo.source.entity.SecuritooCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteStrategy implements ActualizationStrategy {

    @Autowired
    private CustomerService customerService;

    @Override
    public Customer getCustomer(SecuritooCustomer securitooCustomer) {

        Customer customer = customerService.findByIdClient(securitooCustomer.getCustomerId());

        // Cas d'un delete sans correspondance dans identification rapide.
        // Retourne null: la donnée ne sera pas traité par le batch.
        if (customer == null) {
            return null;

            // Cas d'un delete, l'identifiant est sétté a null. Les customers avec un identifiant null seront ensuite supprimés.
        } else {
            customer.setIdClient(null);
        }

        return customer;

    }
}
