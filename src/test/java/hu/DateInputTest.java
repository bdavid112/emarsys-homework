package hu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// This test class is written to check that no invalid input could be used to create or change a Date type instance.
public class DateInputTest {
  Date testDate = new Date(2022, 1, 1, 16, 20);
  Date invalidDate;

  // Checking every possible invalid input scenarios
  @Test
  void invalidInputWhenConstructing() {
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
      invalidDate = new Date(2022, 4, -1, 10, 30);
    } catch (RuntimeException e) {
      assertEquals("Invalid day", e.getMessage());
    }
    assertNull(invalidDate);

    try {
      invalidDate = new Date(2022, 3, 31, 8, 30);
    } catch (RuntimeException e) {
      assertEquals("Invalid date", e.getMessage());
    }
    assertNull(invalidDate);

    try {
      invalidDate = new Date(2022, 3, 31, 17, 30);
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

  @Test
  void invalidInputWhenModifying() {
    Date modifiedTestDate = testDate;
    modifiedTestDate.setYear(-1);
    assertEquals(testDate, modifiedTestDate);
    modifiedTestDate.setMonth(-1);
    assertEquals(testDate, modifiedTestDate);
    modifiedTestDate.setMonth(13);
    assertEquals(testDate, modifiedTestDate);
    modifiedTestDate.setDay(-1);
    assertEquals(testDate, modifiedTestDate);
    modifiedTestDate.setDay(32);
    assertEquals(testDate, modifiedTestDate);
    modifiedTestDate.setMonth(2);
    modifiedTestDate.setDay(29);
    assertEquals(testDate, modifiedTestDate);
    modifiedTestDate.setMonth(4);
    modifiedTestDate.setDay(31);
    assertEquals(testDate, modifiedTestDate);
    modifiedTestDate.setHour(8);
    assertEquals(testDate, modifiedTestDate);
    modifiedTestDate.setHour(18);
    assertEquals(testDate, modifiedTestDate);
    modifiedTestDate.setMin(-1);
    assertEquals(testDate, modifiedTestDate);
    modifiedTestDate.setMin(60);
    assertEquals(testDate, modifiedTestDate);
  }
}
