package fr.arolla.trainreservation.ticket_office.services;

import fr.arolla.trainreservation.ticket_office.Seat;
import fr.arolla.trainreservation.ticket_office.controllers.BookingResponse;
import fr.arolla.trainreservation.ticket_office.dto.ReservationDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BookingServiceImplTest {


  private final InteractionService interactionService = mock(InteractionService.class);

  private final BookingServiceImpl service = new BookingServiceImpl(interactionService);

  @Test
  void giver_1_seat_to_reserve_when_book_on_available_train_then_should_return_booking_details() {
    String trainId = "trainId1";
    Seat seat1 = new Seat("1", "A", null);
    Seat seat2 = new Seat("2", "A", null);
    Seat seat3 = new Seat("3", "A", null);
    ArrayList<Seat> seats = new ArrayList<>();
    seats.add(seat1);
    seats.add(seat2);
    seats.add(seat3);

    ReservationDto dto = new ReservationDto(List.of("1A"), trainId, "ref1");

    when(interactionService.getBookingReference()).thenReturn("ref1");
    when(interactionService.getTrainData(trainId)).thenReturn(seats);
    doNothing().when(interactionService).reserve(dto);

    BookingResponse response = service.book(1, trainId);

    assertThat(response).isNotNull();
    assertThat(response.train_id()).isEqualTo(trainId);
    assertThat(response.seats()).isEqualTo(List.of("1A"));
    assertThat(response.booking_reference()).isEqualTo("ref1");

  }

  @Test
  void giver_3_seat_to_reserve_when_book_on_available_train_then_should_return_booking_details() {
    String trainId = "trainId1";
    Seat seat1 = new Seat("1", "A", null);
    Seat seat2 = new Seat("2", "A", null);
    Seat seat3 = new Seat("3", "A", null);
    ArrayList<Seat> seats = new ArrayList<>();
    seats.add(seat1);
    seats.add(seat2);
    seats.add(seat3);

    ReservationDto dto = new ReservationDto(List.of("1A"), trainId, "ref1");

    when(interactionService.getBookingReference()).thenReturn("ref1");
    when(interactionService.getTrainData(trainId)).thenReturn(seats);
    doNothing().when(interactionService).reserve(dto);

    BookingResponse response = service.book(3, trainId);


    assertThat(response).isNotNull();
    assertThat(response.train_id()).isEqualTo(trainId);
    assertThat(response.seats()).isEqualTo(List.of("1A", "2A", "3A"));
    assertThat(response.booking_reference()).isEqualTo("ref1");

  }

  @Test
  void given_2_seat_to_reserve_when_book_on_available_train_then_should_return_booking_details() {
    String trainId = "trainId1";
    Seat seat1 = new Seat("1", "A", null);
    Seat seat2 = new Seat("2", "A", "ref2");
    Seat seat3 = new Seat("3", "A", null);
    ArrayList<Seat> seats = new ArrayList<>();
    seats.add(seat1);
    seats.add(seat2);
    seats.add(seat3);

    ReservationDto dto = new ReservationDto(List.of("1A"), trainId, "ref1");

    when(interactionService.getBookingReference()).thenReturn("ref1");
    when(interactionService.getTrainData(trainId)).thenReturn(seats);
    doNothing().when(interactionService).reserve(dto);

    BookingResponse response = service.book(2, trainId);

    assertThat(response).isNotNull();
    assertThat(response.train_id()).isEqualTo(trainId);
    assertThat(response.seats()).isEqualTo(List.of("1A", "3A"));
    assertThat(response.booking_reference()).isEqualTo("ref1");

  }

  @Test
  void given_3_seat_to_reserve_when_book_on_not_enough_place_available_train_then_should_return_booking_details() {
    String trainId = "trainId1";
    Seat seat1 = new Seat("1", "A", null);
    Seat seat2 = new Seat("2", "A", "ref2");
    Seat seat3 = new Seat("3", "A", null);
    ArrayList<Seat> seats = new ArrayList<>();
    seats.add(seat1);
    seats.add(seat2);
    seats.add(seat3);

    ReservationDto dto = new ReservationDto(List.of("1A"), trainId, "ref1");

    when(interactionService.getBookingReference()).thenReturn("ref1");
    when(interactionService.getTrainData(trainId)).thenReturn(seats);
    doNothing().when(interactionService).reserve(dto);

    BookingResponse response = service.book(3, trainId);

    assertThat(response).isNull();

  }
}