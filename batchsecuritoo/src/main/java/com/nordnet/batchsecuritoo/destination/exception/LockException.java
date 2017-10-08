package com.nordnet.batchsecuritoo.destination.exception;

public class LockException extends RuntimeException {

    private static final String MESSAGE_LOCK_EXCEPTION = "Impossible de prendre le lock. Un processus concurrent s'execute. Arretez le ou attendez qu'il se finisse avant de relancer.";

    public LockException() {
        super(MESSAGE_LOCK_EXCEPTION);
    }

}
