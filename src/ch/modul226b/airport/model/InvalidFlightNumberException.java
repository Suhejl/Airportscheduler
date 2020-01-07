package ch.modul226b.airport.model;

public class InvalidFlightNumberException extends InvalidNumberException {
  public InvalidFlightNumberException() {
    super("SORRY - This Flightnumber is not existing");
  }

  public InvalidFlightNumberException(String message) {
    super(message);
  }
}
