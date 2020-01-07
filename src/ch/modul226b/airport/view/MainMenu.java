package ch.modul226b.airport.view;

import ch.modul226b.airport.model.*;
import ch.modul226b.airport.util.ConsoleReader;

public class MainMenu {
  private Airport airport;

  public MainMenu() {
    airport = new Airport();
  }

  public static void main(String[] args) {
    MainMenu menu = new MainMenu();
    menu.action();
    System.out.println("\nThank you and see you again");
  }

  private String menu() {
    return "\n\n\nWelcome to " + airport.getName() + "\nAirport Scheduler Mainmenu\n" +
        "===========================\n\n" +
        "[1] Land\n" +
        "[2] Start\n" +
        "[3] Gatedata\n\n" +
        "[4] End\n\n";
  }

  public void action() {
    while (true) {
      int input = ConsoleReader.readInteger(menu() + "Your choice");
      switch (input) {
        case 1:
          land();
          continue;
        case 2:
          start();
          continue;
        case 3:
          showGateData();
          continue;
        case 4:
          break;
        default:
          System.out.println("Invalid choice");
          continue;
      }
      break;
    }
  }

  private void land() {
    System.out.println("\n\nLand...\n\n");
    Flight flight = getFlightData();
    try {
      int gateNumber = airport.land(flight);
      System.out.println("Plane has landed on Gate " + gateNumber + ".");
    } catch (NoGateAvailableException noex) {
      System.out.println(noex.getMessage());
    }
  }

  private void start() {
    System.out.println("\n\nStart...\n\n");
    System.out.println("Flightnumbers:");
    while (true) {
      try {
        for (int i = 0; i < airport.GATES; i++) {
          if (!airport.getGate(i).isFree()) {
            System.out.println("\nOn Gate " + airport.getGate(i).getNumber() + ": " + airport.getGate(i).getFlight().getFlightnumber());
          }
        }
        int flightNumber = ConsoleReader.readInteger("Which flight do you want to start?");
        if (!flightNumberExists(flightNumber)) {
          throw new InvalidFlightNumberException();
        }

        for (int i = 0; i < airport.GATES; i++) {
          if (airport.getGate(i).getFlight() != null && airport.getGate(i).getFlight().getFlightnumber() == flightNumber) {
            airport.start(i);
            System.out.println("Flightnumber " + flightNumber + " has started on Gate " + i);
          }
        }

      } catch (InvalidNumberException inex) {
        System.out.println(inex.getMessage());
        continue;
      }
      break;
    }
  }

  private boolean flightNumberExists(int flightNumber) {
    try {
      for (int i = 0; i < airport.GATES; i++) {
        if (airport.getGate(i).getFlight() != null && flightNumber == airport.getGate(i).getFlight().getFlightnumber()) {
          return true;
        }
      }
    } catch (InvalidGateNumberException inex) {
      inex.printStackTrace();
    }

    return false;
  }

  private Flight getFlightData() {
    Flight flight = new Flight();
    System.out.println("\n\nPlease enter your flight data\n" +
        "--------------------------------");
    while (true) {
      int flightNumber = ConsoleReader.readInteger("Flight number");
      if (flightNumber <= 0 || flightNumberExists(flightNumber)) {
        System.out.println("This flightnumber is invalid");
        continue;
      }
      flight.setFlightnumber(flightNumber);
      break;
    }
    flight.setLandTime(ConsoleReader.readString("Land time"));
    flight.setStartTime(ConsoleReader.readString("Start time"));

    flight.setPlane(getPlaneData());
    return flight;
  }

  private Plane getPlaneData() {
    Plane plane = new Plane();

    while (true) {
      try {
        plane.setPlaneType(PlaneType.getPlaneType(ConsoleReader.readInteger("Plane type (0 = Prop, 1 = Jet)")));
      } catch (IllegalArgumentException illex) {
        continue;
      }
      break;
    }
    plane.setCapacity(ConsoleReader.readInteger("Number of passenger"));
    plane.setAfterFuelingTime(ConsoleReader.readString("After fueling time"));
    return plane;
  }

  private void showGateData() {
    System.out.println("\n\n\nShow Gate Data...");
    int gateNumber;
    while (true) {
      try {
        gateNumber = (ConsoleReader.readInteger("Enter Gatenumber") - 1);
        if (gateNumber < 0 || gateNumber > 10) {
          throw new IllegalArgumentException("Choose a gate between 1 - 10");
        }
      } catch (IllegalArgumentException illex) {
        System.out.println(illex.getMessage());
        continue;
      }
      break;
    }
    try {
      Gate gate = airport.getGate(gateNumber);
      if (gate.isFree()) {
        System.out.println("Gate is free\n\n\n");
        return;
      }
      if (gate instanceof NationalGate) {
        System.out.println("National Gate");
        System.out.println("--------------");
      } else {
        System.out.println("International Gate");
        System.out.println("------------------");
        System.out.println(
            ((InternationalGate) gate).getSize() == Size.LARGE
                ? "Large Gate" : "Small Gate"
        );
      }
      System.out.println("Flightnumber: " + gate.getFlight().getFlightnumber());
      System.out.println("Planetype: " + (gate.getPlane().getPlaneType() == PlaneType.JET ? "Jet" : "Prop"));
    } catch (InvalidGateNumberException inex) {
      inex.printStackTrace();
    }
  }
}
