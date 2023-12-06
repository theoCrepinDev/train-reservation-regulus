package fr.arolla.trainreservation.ticket_office.dto;

import java.util.List;

public class ReservationDto {
  private List<String> seats;
  private String trainId;
  private String booking_reference;

  public ReservationDto(List<String> seats, String trainId, String booking_reference) {
    this.seats = seats;
    this.trainId = trainId;
    this.booking_reference = booking_reference;
  }

  public ReservationDto() {
  }

  public List<String> getSeats() {
    return seats;
  }

  public void setSeats(List<String> seats) {
    this.seats = seats;
  }

  public String getTrainId() {
    return trainId;
  }

  public void setTrainId(String trainId) {
    this.trainId = trainId;
  }

  public String getBooking_reference() {
    return booking_reference;
  }

  public void setBooking_reference(String booking_reference) {
    this.booking_reference = booking_reference;
  }
}
