package hu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTest {
  Date testDate1;
  Date testDate2;
  Date testDate3;
  Date testDate4;
  Date testDate5;

  @BeforeEach
  void setUp() {
    testDate1 = new Date(2022, 1, 1, 9, 0);
    testDate2 = new Date(2022, 1, 30, 9, 12);
    testDate3 = new Date(2022, 2, 2, 9, 12);
    testDate4 = new Date(2022, 10, 1, 10, 0);
    testDate5 = new Date(2022, 6, 1, 10, 0);
  }

  @Test
  void lengthOfMonthTest() {
    assertEquals(testDate1.getMonthLength(), Date.MonthLength._31);
    assertEquals(testDate3.getMonthLength(), Date.MonthLength._28);
    assertEquals(testDate4.getMonthLength(), Date.MonthLength._31);
  }

  @Test
  void addWorkdaysTest() {
    assertEquals(new Date(2022, 3, 6, 9, 0).toString(), testDate1.add(64).toString());
    assertEquals(new Date(2022, 2, 2, 9, 12).toString(), testDate2.add(3).toString());
    assertEquals(new Date(2022, 3, 2, 9, 12).toString(), testDate3.add(28).toString());
    assertEquals(new Date(2022, 10, 1, 10, 0).toString(), testDate4.add(0).toString());
  }

  @Test
  void addHoursTest() {
    assertEquals(new Date(2022, 1, 1, 12, 0).toString(), testDate1.add(0, 3).toString());
    assertEquals(new Date(2022, 1, 31, 12, 12).toString(), testDate2.add(1, 3).toString());
    assertEquals(new Date(2022, 2, 3, 9, 12).toString(), testDate3.add(0, 8).toString());
    assertEquals(new Date(2022, 10, 6, 9, 0).toString(), testDate4.add(3, 15).toString());
    assertEquals(new Date(2022, 6, 6, 9, 0).toString(), testDate5.add(3, 15).toString());
  }

  @Test
  void addMinutesTest() {
    assertEquals(new Date(2022, 1, 1, 9, 15).toString(), testDate1.add(0, 0, 15).toString());
    assertEquals(new Date(2022, 1, 30, 10, 42).toString(), testDate2.add(0, 0, 90).toString());
    assertEquals(new Date(2022, 2, 2, 11, 42).toString(), testDate3.add(0, 2, 30).toString());
    assertEquals(new Date(2022, 10, 2, 10, 45).toString(), testDate4.add(0, 8, 45).toString());
    assertEquals(new Date(2022, 6, 25, 14, 0).toString(), testDate5.add(23, 10, 120).toString());
  }
}