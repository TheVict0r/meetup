package com.modsensoftware.meetup.exception;

public class DuplicateUniqueFieldException extends AbstractLocalizedCustomException {

    /**
     * Custom exception thrown in the case if client sends to the datasource the value that duplicates existing
     * entry in the unique field.
     */
    public static final String MESSAGE_KEY = "message.duplicate_key";

    public DuplicateUniqueFieldException(Object entry, Object uniqueKey) {
        super(MESSAGE_KEY, new Object[]{entry, uniqueKey});
    }
}