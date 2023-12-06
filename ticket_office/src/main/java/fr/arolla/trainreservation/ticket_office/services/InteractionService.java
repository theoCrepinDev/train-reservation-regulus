package fr.arolla.trainreservation.ticket_office.services;

import fr.arolla.trainreservation.ticket_office.Seat;
import fr.arolla.trainreservation.ticket_office.dto.ReservationDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface InteractionService {
  String getBookingReference();

  ArrayList<Seat> getTrainData(final String trainId);

  void reserve(final ReservationDto reservationDto);
}
