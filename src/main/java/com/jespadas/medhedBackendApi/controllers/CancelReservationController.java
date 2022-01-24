package com.jespadas.medhedBackendApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jespadas.medhedBackendApi.exceptions.ReservationException;
import com.jespadas.medhedBackendApi.responses.ReservationResponse;
import com.jespadas.medhedBackendApi.services.CancelReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/reservation-hospital" + "/v1")
public class CancelReservationController {

	@Autowired
	CancelReservationService cancelReservationService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/deleteReservation", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ReservationResponse<String> deleteReservation(@RequestParam String locator) throws ReservationException {
		return new ReservationResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				cancelReservationService.deleteReservation(locator));
	}
}
