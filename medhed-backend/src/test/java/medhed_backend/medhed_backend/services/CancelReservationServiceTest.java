package medhed_backend.medhed_backend.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.jespadas.medhedBackendApi.entities.Reservation;
import com.jespadas.medhedBackendApi.exceptions.ReservationException;
import com.jespadas.medhedBackendApi.repositories.ReservationRepository;
import com.jespadas.medhedBackendApi.services.impl.CancelReservationServiceImpl;

public class CancelReservationServiceTest {

	private static final String LOCATOR = "Hôpital Garonne/1986";
	private static final String RESERVATION_DELETED = "LOCATOR_DELETED";

	private static final Reservation RESERVATION = new Reservation();

	private static final Optional<Reservation> OPTIONAL_RESERVATION_EMPTY = Optional.empty();
	private static final Optional<Reservation> OPTIONAL_RESERVATION = Optional.of(RESERVATION);

	@Mock
	private ReservationRepository reservationRepository;

	@InjectMocks
	private CancelReservationServiceImpl cancelReservationServiceImpl;

	@Before
	public void init() throws ReservationException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deleteReservation() throws ReservationException {
		Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION);
		Mockito.when(reservationRepository.deleteByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION);
		final String response = cancelReservationServiceImpl.deleteReservation(LOCATOR);
		assertEquals(response, RESERVATION_DELETED);
	}

	@Test(expected = ReservationException.class)
	public void deleteReservationNotFoundError() throws ReservationException {
		Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION_EMPTY);
		Mockito.when(reservationRepository.deleteByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION);
		final String response = cancelReservationServiceImpl.deleteReservation(LOCATOR);
		assertEquals(response, RESERVATION_DELETED);
		fail();
	}

	@Test(expected = ReservationException.class)
	public void deleteReservationInternalServerError() throws ReservationException {
		Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION);
		Mockito.doThrow(Exception.class).when(reservationRepository).deleteByLocator(LOCATOR);
		final String response = cancelReservationServiceImpl.deleteReservation(LOCATOR);
		assertEquals(response, RESERVATION_DELETED);
		fail();
	}

}
