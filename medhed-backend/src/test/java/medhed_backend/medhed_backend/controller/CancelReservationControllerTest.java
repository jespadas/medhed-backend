package medhed_backend.medhed_backend.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.jespadas.medhedBackendApi.controllers.CancelReservationController;
import com.jespadas.medhedBackendApi.exceptions.ReservationException;
import com.jespadas.medhedBackendApi.responses.ReservationResponse;
import com.jespadas.medhedBackendApi.services.CancelReservationService;

public class CancelReservationControllerTest {

	private static final String SUCCES_STATUS = "Succes";
	private static final String SUCCES_CODE = "200";
	private static final String OK = "OK";
	private static final String RESERVATION_DELETED = "LOCATOR_DELETED";
	
	private static final String LOCATOR = "Hôpital Garonne/1986";

	@Mock
	CancelReservationService cancelReservationService;

	@InjectMocks
	CancelReservationController cancelReservationController;

	@Before
	public void init() throws ReservationException {
		MockitoAnnotations.initMocks(this);
		Mockito.when(cancelReservationService.deleteReservation(LOCATOR)).thenReturn(RESERVATION_DELETED);
	}

	@Test
	public void deleteReservationTest() throws ReservationException {
		final ReservationResponse<String> response = cancelReservationController.deleteReservation(LOCATOR);

		assertEquals(response.getStatus(), SUCCES_STATUS);
		assertEquals(response.getCode(), SUCCES_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESERVATION_DELETED);
	}

}
