package ch.modul226b.airport.model;

public class Airport {
  public final int GATES = 10;

  private Gate[] gates;
  private String name;

  public Airport() {
    name = "Gatwick";
    gates = new Gate[GATES];
    initializeGates();
  }

  private void initializeGates() {
    // 4 national Gates
    gates[0] = new NationalGate(1);
    gates[1] = new NationalGate(2);
    gates[2] = new NationalGate(3);
    gates[3] = new NationalGate(4);

    // 2 small international Gates
    gates[4] = new InternationalGate(5, Size.SMALL);
    gates[5] = new InternationalGate(6, Size.SMALL);

    // 4 large international Gates
    gates[6] = new InternationalGate(7, Size.LARGE);
    gates[7] = new InternationalGate(8, Size.LARGE);
    gates[8] = new InternationalGate(9, Size.LARGE);
    gates[9] = new InternationalGate(10, Size.LARGE);
  }

  public int land(Flight flight) throws NoGateAvailableException {
    Gate gate = searchFreeGate(flight);
    gate.land(flight);
    return gate.getNumber();
  }

  public void start(int gateNumner) {
    gates[gateNumner].start();
  }

  public void gateState(int gateNumber) {

  }

  public String getName() {
    return name;
  }

  public Gate getGate(int gateNumber) throws InvalidGateNumberException {
    try {
      return gates[gateNumber];
    } catch (ArrayIndexOutOfBoundsException arrex) {
      arrex.printStackTrace();
    }
    return null;
  }

  private Gate searchFreeGate(Flight flight) throws NoGateAvailableException {
    for (int i = 0; i < gates.length; i++) {

      if (gates[i].isFree()) {
        if (flight.isNational() && !(flight.getPlane().getPlaneType() == PlaneType.JET)) {
          return gates[i];
        } else {
          if (gates[i] instanceof InternationalGate ) {
            if (!flight.getPlane().isLarge()) {
              return gates[i];
            } else if (((InternationalGate) gates[i]).getSize() == Size.LARGE) {
              return gates[i];
            }
          }
        }
      }
    }
    throw new NoGateAvailableException();
  }

}
