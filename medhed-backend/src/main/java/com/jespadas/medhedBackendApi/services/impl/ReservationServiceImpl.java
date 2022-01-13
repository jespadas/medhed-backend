package com.jespadas.medhedBackendApi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jespadas.medhedBackendApi.entities.Hospital;
import com.jespadas.medhedBackendApi.entities.Reservation;
import com.jespadas.medhedBackendApi.entities.Shift;
import com.jespadas.medhedBackendApi.exceptions.InternalServerErrorException;
import com.jespadas.medhedBackendApi.exceptions.NotFoundException;
import com.jespadas.medhedBackendApi.exceptions.ReservationException;
import com.jespadas.medhedBackendApi.jsons.CreateReservationRest;
import com.jespadas.medhedBackendApi.jsons.ReservationRest;
import com.jespadas.medhedBackendApi.repositories.HospitalRepository;
import com.jespadas.medhedBackendApi.repositories.ReservationRepository;
import com.jespadas.medhedBackendApi.repositories.ShiftRepository;
import com.jespadas.medhedBackendApi.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	public static final ModelMapper modelMapper = new ModelMapper();

	int min = 1;
	int max = 10000;

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private ShiftRepository shiftRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	// TODO getReservationById(Long reservationId)
	public ReservationRest getReservationById(Long reservationId) throws ReservationException {
		return modelMapper.map(getReservationEntity(reservationId), ReservationRest.class);
	}

	private Reservation getReservationEntity(Long reservationId) throws ReservationException {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new NotFoundException("SNOT-404-1", "RESERVATION_NOT_FOUND"));
	}

	private String generateLocator(Hospital hospitalId, final CreateReservationRest createReservationRest)
			throws ReservationException {

		return hospitalId.getName() + "/" + getRandomNumber(min, max);
	}

	public String createReservation(CreateReservationRest createReservationRest) throws ReservationException {

		final Hospital hospitalId = hospitalRepository.findById(createReservationRest.getHospitalId())
				.orElseThrow(() -> new NotFoundException("HOSPITAL_NOT_FOUND", "HOSPITAL_NOT_FOUND"));

		final Shift shift = shiftRepository.findById(createReservationRest.getShiftId())
				.orElseThrow(() -> new NotFoundException("SHIFT_NOT_FOUND", "SHIFT_NOT_FOUND"));

		if (reservationRepository.findByShiftAndHospital(shift.getName(), hospitalId.getId()).isPresent()) {
			throw new NotFoundException("RESERVATION_ALREADY_EXISTS", "RESERVATION_ALREADY_EXISTS");
		}

		String locator = generateLocator(hospitalId, createReservationRest);

		final Reservation reservation = new Reservation();
		reservation.setLocator(locator);
		reservation.setPatient(createReservationRest.getPatient());
		reservation.setDate(createReservationRest.getDate());
		reservation.setHospital(hospitalId);
		reservation.setShift(shift.getName());

		try {
			reservationRepository.save(reservation);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}

		return locator;
	}

	public List<ReservationRest> getReservations() throws ReservationException {
		final List<Reservation> reservationsEntity = reservationRepository.findAll();
		return reservationsEntity.stream().map(service -> modelMapper.map(service, ReservationRest.class))
				.collect(Collectors.toList());
	}

}
