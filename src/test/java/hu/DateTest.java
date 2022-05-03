package hu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DateTest {
  Date testDate1;
  Date testDate2;
  Date testDate3;

  @BeforeEach
  void setUp() {
    testDate1 = new Date(2022, 1, 3, 7, 0);
    testDate2 = new Date(2022, 2, 6, 5, 0);
    testDate3 = new Date(2022, 4, 8, 2, 0);
  }

  @Test
  void lengthOfMonth() {
    assertEquals(testDate1.getMonthLength(), Date.MonthLength._31);
    assertEquals(testDate2.getMonthLength(), Date.MonthLength._28);
    assertEquals(testDate3.getMonthLength(), Date.MonthLength._30);
  }
}