package fr.arolla.trainreservation.ticket_office.services;

import fr.arolla.trainreservation.ticket_office.controllers.BookingRequest;
import fr.arolla.trainreservation.ticket_office.controllers.BookingResponse;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {
  BookingResponse book (final int seatCount, String trainId);
}
