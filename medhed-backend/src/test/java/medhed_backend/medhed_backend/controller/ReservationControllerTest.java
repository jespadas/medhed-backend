package medhed_backend.medhed_backend.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.jespadas.medhedBackendApi.controllers.ReservationController;
import com.jespadas.medhedBackendApi.exceptions.ReservationException;
import com.jespadas.medhedBackendApi.jsons.CreateReservationRest;
import com.jespadas.medhedBackendApi.jsons.ReservationRest;
import com.jespadas.medhedBackendApi.responses.ReservationResponse;
import com.jespadas.medhedBackendApi.services.ReservationService;

public class ReservationControllerTest {

	private static final Date DATE = new Date();
	private static final Long HOSPITAL_ID = 1L;
	private static final Long PATIENT = 1L;
	private static final Long SHIFT_ID = 1L;
	private static final String LOCATOR = "Hôpital Purpan";
	private static final Long RESERVATION_ID = 1L;

	private static final String SUCCES_STATUS = "Succes";
	private static final String SUCCES_CODE = "200";
	private static final String OK = "OK";

	public static final ReservationRest RESERVATION_REST = new ReservationRest();
	public static final List<ReservationRest> RESERVATION_REST_LIST = new ArrayList<>();
	CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();

	@Mock
	ReservationService reservationService;

	@InjectMocks
	ReservationController reservationController;

	@Before
	public void init() throws ReservationException {
		MockitoAnnotations.initMocks(this);

		CREATE_RESERVATION_REST.setDate(DATE);
		CREATE_RESERVATION_REST.setPatient(PATIENT);
		CREATE_RESERVATION_REST.setHospitalId(HOSPITAL_ID);
		CREATE_RESERVATION_REST.setShiftId(SHIFT_ID);

		RESERVATION_REST.setDate(DATE);
		RESERVATION_REST.setHospitalId(HOSPITAL_ID);
		RESERVATION_REST.setLocator(LOCATOR);
		RESERVATION_REST.setPatient(PATIENT);
		RESERVATION_REST.setShiftId(SHIFT_ID);

		Mockito.when(reservationService.createReservation(CREATE_RESERVATION_REST)).thenReturn(LOCATOR);
		Mockito.when(reservationService.getReservationById(HOSPITAL_ID)).thenReturn(RESERVATION_REST);
	}

	@Test
	public void createReservationTest() throws ReservationException {
		ReservationResponse<String> response = reservationController.createReservation(CREATE_RESERVATION_REST);

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), LOCATOR);
	}

	@Test
	public void getReservationByIdTest() throws ReservationException {
		final ReservationResponse<ReservationRest> response = reservationController.getReservationById(RESERVATION_ID);
		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESERVATION_REST);
	}

	@Test
	public void getReservationsTest() throws ReservationException {
		final ReservationResponse<List<ReservationRest>> response = reservationController.getReservations();

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESERVATION_REST_LIST);

	}

}
