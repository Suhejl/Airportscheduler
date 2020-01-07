package ch.modul226b.airport.model;

public enum PlaneType {
  PROP(0),
  JET(1);

  private int value;

  PlaneType(int value) {
    this.value = value;
  }
  public static PlaneType getPlaneType(int index) {
    for (PlaneType planeType : PlaneType.values()) {
      if (index == planeType.value)  return planeType;
    }
    throw new IllegalArgumentException("Plane type not found");
  }

}
