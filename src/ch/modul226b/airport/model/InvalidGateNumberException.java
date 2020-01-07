package ch.modul226b.airport.model;

public class InvalidGateNumberException extends InvalidNumberException {

  public InvalidGateNumberException() {
    super("SORRY - This Gatenumber is not existing");
  }

  public InvalidGateNumberException(String message) {
    super(message);
  }
}
