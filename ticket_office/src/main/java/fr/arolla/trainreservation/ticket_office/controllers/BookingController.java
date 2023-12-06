package fr.arolla.trainreservation.ticket_office.controllers;

import fr.arolla.trainreservation.ticket_office.services.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BookingController {

  private final BookingServiceImpl bookingService;

  @Autowired
  BookingController(
    final BookingServiceImpl service
  ) {
    bookingService = service;
  }

  @RequestMapping("/reserve")
  ResponseEntity<BookingResponse> reserve(@RequestBody BookingRequest bookingRequest) {
    var seatCount = bookingRequest.count();
    var trainId = bookingRequest.train_id();
    BookingResponse booking = bookingService.book(seatCount, trainId);
    return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.badRequest().body(null);
  }
}
