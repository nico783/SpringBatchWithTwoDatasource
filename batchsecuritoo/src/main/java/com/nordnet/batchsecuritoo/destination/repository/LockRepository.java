package com.nordnet.batchsecuritoo.destination.repository;

import com.nordnet.batchsecuritoo.destination.entity.Lock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockRepository extends JpaRepository<Lock, Long> {

    Lock findByBatchName(String name);

}
