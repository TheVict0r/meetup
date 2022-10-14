package com.modsensoftware.meetup.exception;

/**
 * Custom exception in the case if there are needless extra data in request body
 * (such as ID while creating a new entity).
 */
public class InappropriateBodyContentException extends AbstractLocalizedCustomException {

	public static final String MESSAGE_KEY = "message.inappropriate_body_content";

	public InappropriateBodyContentException(Object inappropriateData) {
		super(MESSAGE_KEY, new Object[]{inappropriateData});
	}
}
