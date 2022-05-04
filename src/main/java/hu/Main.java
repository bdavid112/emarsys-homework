package hu;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    do {
      System.out.println("Enter a submit date (yyyy.mm.dd hh:mm):");
      String dateString =  sc.nextLine();
      String[] parts = dateString.split(" ");
      if (parts.length != 2) {
        System.out.println("Invalid date format");
        continue;
      }
      String[] dateParts = parts[0].split("\\.");
      String[] timeParts = parts[1].split(":");
      int year = 0, month = 0, day = 0;
      int hour = 0, min = 0;
      try {
        year = Integer.parseInt(dateParts[0]);
        month = Integer.parseInt(dateParts[1]);
        day = Integer.parseInt(dateParts[2]);
        hour = Integer.parseInt(timeParts[0]);
        min = Integer.parseInt(timeParts[1]);
      } catch (Exception e) {
        System.out.println("Invalid date format");
        continue;
      }
      System.out.println("Enter turnaround time (dd:hh:mm)");
      String turnAroundTimeString = sc.nextLine();
      parts = turnAroundTimeString.split(":");
      if (parts.length != 3) {
        System.out.println("Invalid date format");
        continue;
      }
      int d = 0, h = 0, m = 0;
      try {
        d = Integer.parseInt(parts[0]);
        h = Integer.parseInt(parts[1]);
        m = Integer.parseInt(parts[2]);
      } catch (Exception e) {
        System.out.println("Invalid turnaround time format");
        continue;
      }
      Date submitDate = null;
      try {
        submitDate = new Date(year, month, day, hour, min);
      } catch (Exception e) {
        System.out.println(e.getMessage());
        continue;
      }
      Date dueDate = DueDateCalculator.calculateDueDate(submitDate, d, h, m);
      System.out.println("The due date is: " + dueDate);
      int input = 0;
      do {
        System.out.println("Would you like to calculate another due date?\n1.Yes\n2.No");
        input = sc.nextInt();
      } while (input != 1 && input != 2);
      if (input == 2) {
        break;
      }
    } while (true);
  }
}
