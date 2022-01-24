package com.jespadas.medhedBackendApi.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.jespadas.medhedBackendApi.dtos.ErrorDto;

public class NotFoundException extends ReservationException {

	/**
	 * DEFAULT SERIAL VERSION
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String code, String message) {
		super(code, HttpStatus.NOT_FOUND.value(), message);
	}

	public NotFoundException(String code, String message, ErrorDto data) {
		super(code, HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
	}

}
