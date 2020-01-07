package ch.modul226b.airport.model;

public class Plane {
  PlaneType planeType;
  String afterFuelingTime;
  int capacity;

  public PlaneType getPlaneType() {
    return planeType;
  }

  public void setPlaneType(PlaneType planeType) {
    this.planeType = planeType;
  }

  public String getAfterFuelingTime() {
    return afterFuelingTime;
  }

  public void setAfterFuelingTime(String afterFuelingTime) {
    this.afterFuelingTime = afterFuelingTime;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public boolean isLarge() {
    return capacity > 2000;
  }
}
