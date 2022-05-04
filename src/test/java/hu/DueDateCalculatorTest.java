package hu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DueDateCalculatorTest {
  @Test
  void calculateDueDateWeekdaysTest() {
    assertEquals(
        new Date(2022, 4, 20, 10, 0).toString(),
        DueDateCalculator.calculateDueDate(new Date(2022, 4, 20, 9, 0), 0, 0, 60).toString()
    );
    assertEquals(
        new Date(2022, 9, 9, 12, 30).toString(),
        DueDateCalculator.calculateDueDate(new Date(2022, 9, 9, 10, 0), 0, 2, 30).toString()
    );
    assertEquals(
        new Date(2022, 5, 5, 14, 12).toString(),
        DueDateCalculator.calculateDueDate(new Date(2022, 5, 3, 14, 12), 0, 16, 0).toString()
    );
    assertEquals(
        new Date(2022, 1, 20, 11, 45).toString(),
        DueDateCalculator.calculateDueDate(new Date(2022, 1, 17, 14, 30), 2, 5, 15).toString()
    );
  }

  @Test
  void calculateDueDateIncludingWeekendsTest() {
    assertEquals(
        new Date(2022, 5, 9, 16, 0).toString(),
        DueDateCalculator.calculateDueDate(new Date(2022, 5, 6, 16, 0), 1, 0, 0).toString()
    );
    assertEquals(
        new Date(2022, 5, 18, 16, 0).toString(),
        DueDateCalculator.calculateDueDate(new Date(2022, 5, 6, 16, 0), 8, 0, 0).toString()
    );
    assertEquals(
        new Date(2022, 5, 3, 12, 0).toString(),
        DueDateCalculator.calculateDueDate(new Date(2022, 4, 4, 12, 0), 21, 0, 0).toString()
    );
    assertEquals(
        new Date(2022, 6, 20, 12, 0).toString(),
        DueDateCalculator.calculateDueDate(new Date(2022, 5, 4, 12, 0), 33, 0, 0).toString()
    );
    assertEquals(
        new Date(2024, 7, 23, 15, 0).toString(),
        DueDateCalculator.calculateDueDate(new Date(2023, 2, 28, 15, 0), 365, 0, 0).toString()
    );
  }
}