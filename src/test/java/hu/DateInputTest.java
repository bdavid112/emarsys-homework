package hu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DateInputTest {
  Date invalidDate;

  // checking every possible invalid input scenarios
  @Test
  void invalidInput() {
    try {
      invalidDate = new Date(-2000, 11, 27, 16, 20);
    } catch (RuntimeException e) {
      assertEquals("Invalid date", e.getMessage());
    }
    assertNull(invalidDate);

    try {
      invalidDate = new Date(2022, 0, 4, 10, 30);
    } catch (RuntimeException e) {
      assertEquals("Invalid date", e.getMessage());
    }
    assertNull(invalidDate);

    try {
      invalidDate = new Date(2022, 13, 10, 15, 15);
    } catch (RuntimeException e) {
      assertEquals("Invalid date", e.getMessage());
    }
    assertNull(invalidDate);

    try {
      invalidDate = new Date(2022, 4, -2, 10, 30);
    } catch (RuntimeException e) {
      assertEquals("Invalid day", e.getMessage());
    }
    assertNull(invalidDate);

    try {
      invalidDate = new Date(2022, 2, 29, 10, 30);
    } catch (RuntimeException e) {
      assertEquals("Invalid day", e.getMessage());
    }
    assertNull(invalidDate);

    try {
      invalidDate = new Date(2022, 1, 32, 10, 30);
    } catch (RuntimeException e) {
      assertEquals("Invalid day", e.getMessage());
    }
    assertNull(invalidDate);

    try {
      invalidDate = new Date(2022, 4, 31, 10, 30);
    } catch (RuntimeException e) {
      assertEquals("Invalid day", e.getMessage());
    }
    assertNull(invalidDate);

    try {
      invalidDate = new Date(2022, 3, 31, -1, 30);
    } catch (RuntimeException e) {
      assertEquals("Invalid date", e.getMessage());
    }
    assertNull(invalidDate);

    try {
      invalidDate = new Date(2022, 3, 31, 24, 30);
    } catch (RuntimeException e) {
      assertEquals("Invalid date", e.getMessage());
    }
    assertNull(invalidDate);

    try {
      invalidDate = new Date(2022, 3, 31, 20, -1);
    } catch (RuntimeException e) {
      assertEquals("Invalid date", e.getMessage());
    }
    assertNull(invalidDate);

    try {
      invalidDate = new Date(2022, 3, 31, 20, 60);
    } catch (RuntimeException e) {
      assertEquals("Invalid date", e.getMessage());
    }
    assertNull(invalidDate);
  }
}
