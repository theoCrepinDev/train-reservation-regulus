package fr.arolla.trainreservation.ticket_office.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arolla.trainreservation.ticket_office.Seat;
import fr.arolla.trainreservation.ticket_office.controllers.BookingResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookingServiceImpl implements BookingService{
  
  private final InteractionService interactionService;

  @Autowired
  BookingServiceImpl(final InteractionService interactionService){
    this.interactionService = interactionService;
  }
  
  @Override
  public BookingResponse book(int seatCount, String trainId) {
    String bookingReference = interactionService.getBookingReference();

    // Step 2: Retrieve train data for the given train ID
    ArrayList<Seat> seats = interactionService.getTrainData(trainId);

    // Step 3: find available seats (hard-code coach 'A' for now)
    var availableSeats = seats.stream().filter(seat -> seat.coach().equals("A") && seat.bookingReference() == null);

    // Step 4: call the '/reserve' end point
    var toReserve = availableSeats.limit(seatCount);
    var ids = toReserve.map(seat -> seat.number() + seat.coach()).toList();

    Map<String, Object> payload = new HashMap<>();
    payload.put("train_id", trainId);
    payload.put("seats", ids);
    payload.put("booking_reference", bookingReference);

    interactionService.reserve(payload);

    // Step 5: return reference and booked seats
    return new BookingResponse(trainId, bookingReference, ids);
  }
}
