package com.jespadas.medhedBackendApi.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jespadas.medhedBackendApi.exceptions.InternalServerErrorException;
import com.jespadas.medhedBackendApi.exceptions.NotFoundException;
import com.jespadas.medhedBackendApi.exceptions.ReservationException;
import com.jespadas.medhedBackendApi.repositories.ReservationRepository;
import com.jespadas.medhedBackendApi.services.CancelReservationService;

@Service
public class CancelReservationServiceImpl implements CancelReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CancelReservationServiceImpl.class);

	@Autowired
	private ReservationRepository reservationRepository;

	public String deleteReservation(String locator) throws ReservationException {

		reservationRepository.findByLocator(locator)
				.orElseThrow(() -> new NotFoundException("LOCATOR_NOT_FOUND", "LOCATOR_NOT_FOUND"));

		try {
			reservationRepository.deleteByLocator(locator);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}

		return "LOCATOR_DELETED";
	}

}
