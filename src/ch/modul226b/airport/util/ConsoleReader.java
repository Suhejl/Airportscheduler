package ch.modul226b.airport.util;

import java.util.Scanner;

public class ConsoleReader {
  private static Scanner scan = new Scanner(System.in);

  private static void printInstruction(String instruction) {
    System.out.print(instruction + "\n>");
  }

  public static int readInteger(String instruction) {
    int input;
    while (true) {
      try {
        printInstruction(instruction);
        input = Integer.parseInt(scan.nextLine());
      } catch (NumberFormatException nuex) {
        continue;
      }
      break;
    }
    return input;
  }

  public static String readString(String instruction) {
    String input;
    while (true) {
      try {
        printInstruction(instruction);
        input = scan.nextLine();
      } catch (NumberFormatException nuex) {
        continue;
      }
      break;
    }
    return input;
  }
}
