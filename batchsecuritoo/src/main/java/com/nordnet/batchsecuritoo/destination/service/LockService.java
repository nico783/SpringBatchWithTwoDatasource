package com.nordnet.batchsecuritoo.destination.service;

import com.nordnet.batchsecuritoo.destination.entity.Lock;
import com.nordnet.batchsecuritoo.destination.exception.LockException;
import com.nordnet.batchsecuritoo.destination.repository.LockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LockService {

    static final String BATCH_NAME = "securitoo";
    static final String SUCCESS = "OK";
    static final String ERROR = "ERROR";

    @Autowired
    private LockRepository lockRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void lock() {
        Lock lock = lockRepository.findByBatchName(BATCH_NAME);
        LockStatus status = LockStatus.valueOf(lock.getLockStatus());
        if (status.isAvailable()) {
            lock.setLockStatus(LockStatus.LOCKED.name());
            lock.setJobStart(new Date());
        } else {
            throw new LockException();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void unlockOnSuccess() {
        Lock lock = lockRepository.findByBatchName(BATCH_NAME);
        lock.setLockStatus(LockStatus.AVAILABLE.name());
        lock.setJobEnd(new Date());
        lock.setStatusAtEnd(SUCCESS);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void unlockOnError() {
        Lock lock = lockRepository.findByBatchName(BATCH_NAME);
        lock.setLockStatus(LockStatus.AVAILABLE.name());
        lock.setJobEnd(new Date());
        lock.setStatusAtEnd(ERROR);
    }

    private enum LockStatus {
        LOCKED,
        AVAILABLE;

        public boolean isAvailable() {
            return this == AVAILABLE;
        }
    }
}