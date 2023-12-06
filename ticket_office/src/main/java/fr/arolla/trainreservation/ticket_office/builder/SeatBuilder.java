package fr.arolla.trainreservation.ticket_office.builder;

import fr.arolla.trainreservation.ticket_office.Seat;

public class SeatBuilder {
  private String bookingReference;
  private String coach;
  private String number;

  public SeatBuilder() {
    number = "0";
    coach = "A";
    bookingReference = null;
  }

  public SeatBuilder withSeatNumber(String seatNumber) {
    this.number = seatNumber;
    return this;
  }

  public SeatBuilder withCoach(String coach) {
    this.coach = coach;
    return this;
  }

  public SeatBuilder withReference(String ref) {
    this.bookingReference = ref;
    return this;
  }

  public Seat free() {
    return new Seat(number, coach, null);
  }

  public Seat build() {
    return new Seat(number, coach, bookingReference);
  }

}
