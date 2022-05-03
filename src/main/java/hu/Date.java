package hu;

public class Date {
  private int year;
  private int month;
  private int day;
  private int hour;
  private int min;

  private MonthLength monthLength;

  public enum MonthLength {
    _28,
    _30,
    _31
  }

  public Date(int year, int month, int day, int hour, int min) {
    this.year = year;
    this.month = month;
    this.day = day;
    this.hour = hour;
    this.min = min;
    this.monthLength = month == 2 ? MonthLength._28 : month % 2 == 0 ? MonthLength._30 : MonthLength._31;
    validInputCheck(this.year, this.month, this.monthLength, this.day, this.hour, this.min);
  }

  private void validInputCheck(int year, int month, MonthLength monthLength,  int day, int hour, int min) {
    if (monthLength == MonthLength._28 && (day < 1 || day > 28)) {
      throw new RuntimeException("Invalid day");
    } else if (monthLength == MonthLength._30 && (day < 1 || day > 30)) {
      throw  new RuntimeException("Invalid day");
    } else if (monthLength == MonthLength._31 && (day < 1 || day > 31)) {
      throw  new RuntimeException("Invalid day");
    }
    if (year < 0 || (month < 1 || month > 12) || (hour < 0 || hour > 23) || (min < 0 || min > 59)) {
      throw new RuntimeException("Invalid date");
    }
  }

  public int getYear() {
    return year;
  }
  public void setYear(int year) {
    if (year >= 0) {
      this.year = year;
    }
  }

  public int getMonth() {
    return month;
  }
  public void setMonth(int month) {
    this.month = month;
  }

  public int getDay() {
    return day;
  }
  public void setDay(int day) {
    this.day = day;
  }

  public int getHour() {
    return hour;
  }
  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getMin() {
    return min;
  }
  public void setMin(int min) {
    this.min = min;
  }

  public MonthLength getMonthLength() {
    return monthLength;
  }
  public void setMonthLength(MonthLength monthLength) {
    this.monthLength = monthLength;
  }
}
