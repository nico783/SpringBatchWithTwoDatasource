package com.nordnet.batchsecuritoo.destination.service;

import com.nordnet.batchsecuritoo.destination.entity.Customer;
import com.nordnet.batchsecuritoo.destination.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Permet de retrouver un customer par son idClient.
     *
     * @param idClient identifiant fonctionnel du customer.
     * @return le customer correspondant et null si aucun customer n'est trouvé.
     */
    public Customer findByIdClient(String idClient) {
        if (idClient != null) {
            return customerRepository.findByIdClient(idClient);
        } else {
            return null;
        }
    }

}
