package com.nordnet.batchsecuritoo.destination.repository;

import com.nordnet.batchsecuritoo.destination.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Customer findByIdClient(String idClient);

}
