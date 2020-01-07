package ch.modul226b.airport.model;

public class Flight {
  private int number;
  private String startTime;
  private String landTime;
  Plane plane;

  public int getFlightnumber() {
    return number;
  }

  public String getStartTime() {
    return startTime;
  }

  public String getLandTime() {
    return landTime;
  }

  public Plane getPlane() {
    return plane;
  }

  public void setFlightnumber(int number) {
    this.number = number;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public void setLandTime(String landTime) {
    this.landTime = landTime;
  }

  public void setPlane(Plane plane) {
    this.plane = plane;
  }

  public boolean isNational() {
    return plane.capacity < 1000;
  }
}
