package com.modsensoftware.meetup.exception;

public class DuplicateFieldException extends AbstractLocalizedCustomException {

    /**
     * Custom exception thrown in the case if client sends to the datasource the value that duplicates existing
     * entry in the unique field.
     */
    public static final String MESSAGE_KEY = "message.duplicate_key";

    public DuplicateFieldException(Object entry, Object uniqueKey) {
        super(MESSAGE_KEY, new Object[]{entry, uniqueKey});
    }
}