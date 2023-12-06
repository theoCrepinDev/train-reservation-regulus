package fr.arolla.trainreservation.ticket_office.services;

import fr.arolla.trainreservation.ticket_office.Seat;
import fr.arolla.trainreservation.ticket_office.controllers.BookingResponse;
import fr.arolla.trainreservation.ticket_office.dto.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookingServiceImpl {

  private final InteractionService interactionService;

  @Autowired
  BookingServiceImpl(final InteractionService interactionService) {
    this.interactionService = interactionService;
  }

  public BookingResponse book(int seatCount, String trainId) {
    String bookingReference = interactionService.getBookingReference();

    // Step 2: Retrieve train data for the given train ID
    ArrayList<Seat> seats = interactionService.getTrainData(trainId);

    // Step 3: find available seats (hard-code coach 'A' for now)
    var availableSeats = seats.stream().filter(seat -> seat.coach().equals("A") && seat.bookingReference() == null);

    // Step 4: call the '/reserve' end point
    var toReserve = availableSeats.limit(seatCount);
    var ids = toReserve.map(seat -> seat.number() + seat.coach()).toList();

    if (ids.size() != seatCount) {
      return null;
    }

    final ReservationDto reservationDto = new ReservationDto();
    reservationDto.setBooking_reference(bookingReference);
    reservationDto.setSeats(ids);
    reservationDto.setTrainId(trainId);

    interactionService.reserve(reservationDto);

    // Step 5: return reference and booked seats
    return new BookingResponse(trainId, bookingReference, ids);
  }
}
