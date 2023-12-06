package fr.arolla.trainreservation.ticket_office.services;

import fr.arolla.trainreservation.ticket_office.Seat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public interface InteractionService {
  String getBookingReference();
  ArrayList<Seat> getTrainData(final String trainId);
  void reserve(final Map<String, Object> payload );
}
