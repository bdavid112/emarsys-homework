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
    this.monthLength = getMonthLength(month);
    validInputCheck(this.year, this.month, this.monthLength, this.day, this.hour, this.min);
  }

  private void validInputCheck(int year, int month, MonthLength monthLength, int day, int hour, int min) {
    if (monthLength == MonthLength._28 && (day < 1 || day > 28)) {
      throw new RuntimeException("Invalid day");
    } else if (monthLength == MonthLength._30 && (day < 1 || day > 30)) {
      throw new RuntimeException("Invalid day");
    } else if (monthLength == MonthLength._31 && (day < 1 || day > 31)) {
      throw new RuntimeException("Invalid day");
    }
    int hourInMin = hour * 60 + min;
    if (year < 0 || (month < 1 || month > 12) || (hour < 9 || hour > 17) || (min < 0 || min > 59) || (hourInMin < 540 || hourInMin > 1020)) {
      throw new RuntimeException("Invalid date");
    }
  }

  private MonthLength getMonthLength(int month) {
    if (month == 2) {
      return MonthLength._28;
    }
    if (month < 8) {
      return month % 2 == 0 ? MonthLength._30 : MonthLength._31;
    } else {
      return month % 2 == 0 ? MonthLength._31 : MonthLength._30;
    }
  }

  public Date add(int workday) {
    if (workday < 0) {
      System.out.println("No changes due to invalid input");
      return this;
    }
    while (workday >= 28) {
      if (monthLength == MonthLength._28) {
        workday -= 28;
        nextMonth(this.month);
      } else if (monthLength == MonthLength._30) {
        workday -= 30;
        nextMonth(this.month);
      } else {
        workday -= 31;
        nextMonth(this.month);
      }
    }
    int remainingDays = workday;
    remainingDays = getRemainingDays(workday, remainingDays);
    setDay(this.day + remainingDays);
    return this;
  }

  public Date add(int workday, int hour) {
    if (hour >= 8) {
      workday = workday + hour / 8;
      hour %= 8;
    }
    if (workday > 0) {
      add(workday);
    }
    if (this.hour + hour >= 17) {
      setDay(this.day + 1);
      hour = hour + this.hour - 17;
      setHour(9);
    }
    setHour(this.hour + hour);
    return this;
  }

  public Date add(int workday, int hour, int min) {
    if (this.min + min >= 60) {
      hour = hour + min / 60;
      min %= 60;
    }
    if (hour > 0) {
      add(workday, hour);
    }
    setMin(this.min + min);
    return this;
  }

  private int getRemainingDays(int workday, int remainingDays) {
    switch (monthLength) {
      case _28:
        if (this.day + workday > 28) {
          nextMonth(this.month);
          remainingDays = getRemainingWorkday(workday, this.day, 28);
          setDay(1);
        }
        break;
      case _30:
        if (this.day + workday > 30) {
          nextMonth(this.month);
          remainingDays = getRemainingWorkday(workday, this.day, 30);
          setDay(1);
        }
        break;
      case _31:
        if (this.day + workday > 31) {
          nextMonth(this.month);
          remainingDays = getRemainingWorkday(workday, this.day, 31);
          setDay(1);
        }
        break;
    }
    return remainingDays;
  }

  private int getRemainingWorkday(int workday, int currDay, int numberOfDaysInMonth) {
    int remainingDays;
    if (workday >= currDay) {
      remainingDays = numberOfDaysInMonth - (workday - this.day);
    } else  {
      remainingDays = workday - (numberOfDaysInMonth - currDay) - 1;
    }
    return remainingDays;
  }

  private void nextMonth(int month) {
    if (month == 12) {
      setMonth(1);
    } else {
      setMonth(this.month + 1);
    }
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    if (year >= 0) {
      this.year = year;
    } else {
      System.out.println("year field did not change due to invalid input");
    }
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    if (month >= 1 && month <= 12) {
      this.month = month;
      this.monthLength = month == 2 ? MonthLength._28 : month % 2 == 0 ? MonthLength._30 : MonthLength._31;
    } else {
      System.out.println("month field did not change due to invalid input");
    }
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    if ((this.monthLength == MonthLength._28 && day >= 1 && day <= 28) ||
        (this.monthLength == MonthLength._30 && day >= 1 && day <= 30) ||
        (this.monthLength == MonthLength._31 && day >= 1 && day <= 31)
    ) {
      this.day = day;
    } else {
      System.out.println("day field did not change due to invalid input");
    }
  }

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    int hourInMin = hour * 60 + this.min;
    if (hour >= 9 && hour <= 17 && (hourInMin >= 540 && hourInMin <= 1020)) {
      this.hour = hour;
    } else {
      System.out.println("hour field did not change due to invalid input");
    }
  }

  public int getMin() {
    return min;
  }

  public void setMin(int min) {
    if (min >= 0 && min <= 59) {
      this.min = min;
    } else {
      System.out.println("min field did not change due to invalid input");
    }
  }

  public MonthLength getMonthLength() {
    return monthLength;
  }

  @Override
  public String toString() {
    String monthString = this.month < 10 ? "0" + this.month : "" + this.month;
    String dayString = this.day < 10 ? "0" + this.day : "" + this.day;
    String hourString = this.hour < 10 ? "0" + this.hour : "" + this.hour;
    String minString = this.min < 10 ? "0" + this.min : "" + this.min;
    return this.year + "." + monthString + "." + dayString + "." + " " + hourString + ":" + minString;
  }
}
