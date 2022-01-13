package com.jespadas.medhedBackendApi.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.jespadas.medhedBackendApi.dtos.ErrorDto;

public class InternalServerErrorException extends ReservationException {

	/**
	 * DEFAULT SERIAL VERSION
	 */
	private static final long serialVersionUID = 1L;

	public InternalServerErrorException(String code, String message) {
		super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}

	public InternalServerErrorException(String code, String message, ErrorDto data) {
		super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Arrays.asList(data));
	}

}
