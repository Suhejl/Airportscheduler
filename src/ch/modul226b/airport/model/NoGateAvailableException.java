package ch.modul226b.airport.model;

public class NoGateAvailableException extends Exception {

  public NoGateAvailableException() {
    super("SORRY - No free gates found");
  }
}
