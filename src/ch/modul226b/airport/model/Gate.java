package ch.modul226b.airport.model;

public class Gate {
  private int number;
  private Flight flight;

  public Gate(int number) {
    this.number = number;
  }

  public void land(Flight flight) {
    this.flight = flight;
  }

  public void start() {
    flight = null;
  }

  public boolean isFree() {
    return (flight == null);
  }

  public Flight getFlight() {
    return flight;
  }

  public Plane getPlane() {
    if (flight == null) {
      return null;
    }
    return flight.getPlane();
  }

  public int getNumber() {
    return number;
  }
}
