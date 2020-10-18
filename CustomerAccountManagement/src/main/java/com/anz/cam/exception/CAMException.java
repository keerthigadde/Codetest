package com.anz.cam.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CAMException extends RuntimeException {

	private static final long serialVersionUID = 5861310537366287163L;

	public CAMException() {
		super();
	}

	public CAMException(final String message, final Throwable cause) {
		super(message, cause);
		log.debug(message);
	}

	public CAMException(final String message) {
		super(message);
		log.debug(message);
	}

	public CAMException(final Throwable cause) {
		super(cause);
		log.debug(cause.toString());
	}
}
