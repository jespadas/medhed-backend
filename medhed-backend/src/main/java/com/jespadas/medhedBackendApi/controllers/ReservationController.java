package com.jespadas.medhedBackendApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jespadas.medhedBackendApi.exceptions.ReservationException;
import com.jespadas.medhedBackendApi.jsons.CreateReservationRest;
import com.jespadas.medhedBackendApi.jsons.ReservationRest;
import com.jespadas.medhedBackendApi.responses.ReservationResponse;
import com.jespadas.medhedBackendApi.services.ReservationService;

@RestController
@RequestMapping(path = "/reservation-hospital" + "/v1")
public class ReservationController {

	@Autowired
	ReservationService reservationService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "reservation" + "/{" + "reservationId"
			+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ReservationResponse<ReservationRest> getReservationById(@PathVariable Long reservationId)
			throws ReservationException {
		return new ReservationResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				reservationService.getReservationById(reservationId));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "reservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ReservationResponse<String> createReservation(@RequestBody CreateReservationRest createReservationRest)
			throws ReservationException {
		return new ReservationResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				reservationService.createReservation(createReservationRest));
	}

	/*
	 * @ResponseStatus(HttpStatus.OK)
	 * 
	 * @RequestMapping(value = "reservation", method = RequestMethod.PUT, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ReservationResponse<String>
	 * updateReservation(@RequestBody CreateReservationRest createReservationRest)
	 * throws ReservationException { return new ReservationResponse<>("Succes",
	 * String.valueOf(HttpStatus.OK), "OK",
	 * reservationService.createReservation(createReservationRest)); }
	 */

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "reservations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ReservationResponse<List<ReservationRest>> getReservations() throws ReservationException {
		return new ReservationResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				reservationService.getReservations());
	}

}
