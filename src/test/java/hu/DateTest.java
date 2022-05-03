package hu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DateTest {
  Date testDate1;
  Date testDate2;
  Date testDate3;
  Date testDate4;

  @BeforeEach
  void setUp() {
    testDate1 = new Date(2022, 1, 1, 9, 0);
    testDate2 = new Date(2022, 1, 30, 9, 12);
    testDate3 = new Date(2022, 2, 2, 9, 12);
    testDate4 = new Date(2022, 9, 1, 10, 0);
  }

  @Test
  void lengthOfMonth() {
    assertEquals(testDate1.getMonthLength(), Date.MonthLength._31);
    assertEquals(testDate2.getMonthLength(), Date.MonthLength._28);
    assertEquals(testDate3.getMonthLength(), Date.MonthLength._30);
  }

  @Test
  void addWorkdaysTest() {
    assertEquals(new Date(2022, 3, 6, 9, 0).toString(), testDate1.add(64).toString());
    assertEquals(new Date(2022, 2, 2, 9, 12).toString(), testDate2.add(3).toString());
    assertEquals(new Date(2022, 3, 2, 9, 12).toString(), testDate3.add(28).toString());
    assertEquals(new Date(2022, 9, 1, 10, 0).toString(), testDate4.add(0).toString());
  }
}