package fr.arolla.trainreservation.ticket_office.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arolla.trainreservation.ticket_office.Seat;
import fr.arolla.trainreservation.ticket_office.dto.ReservationDto;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class InteractionServiceImpl implements InteractionService {
  private final static String BOOKING_REFERENCE_API_URL = "http://127.0.0.1:8082/booking_reference";
  private final static String TRAIN_DATE_API_URL = "http://127.0.0.1:8081";
  private final RestTemplate restTemplate;

  InteractionServiceImpl() {
    restTemplate = new RestTemplate();
  }

  @Override
  public String getBookingReference() {
    return restTemplate.getForObject(BOOKING_REFERENCE_API_URL, String.class);
  }

  @Override
  public ArrayList<Seat> getTrainData(String trainId) {
    var json = restTemplate.getForObject(TRAIN_DATE_API_URL + "/data_for_train/" + trainId, String.class);
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<Seat> seats = new ArrayList<>();
    try {
      var tree = objectMapper.readTree(json);
      var seatsNode = tree.get("seats");
      for (JsonNode node : seatsNode) {
        String coach = node.get("coach").asText();
        String seatNumber = node.get("seat_number").asText();
        var jsonBookingReference = node.get("booking_reference").asText();
        if (jsonBookingReference.isEmpty()) {
          var seat = new Seat(seatNumber, coach, null);
          seats.add(seat);
        } else {
          var seat = new Seat(seatNumber, coach, jsonBookingReference);
          seats.add(seat);
        }
      }
      return seats;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void reserve(ReservationDto reservationDto) {
    restTemplate.postForObject(TRAIN_DATE_API_URL + "/reserve", reservationDto, String.class);
  }
}
