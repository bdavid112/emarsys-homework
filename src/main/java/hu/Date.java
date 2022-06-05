package hu;

public class Date {
  private final int MIN_HOURS_IN_MINUTES = 540;
  private final int MAX_HOURS_IN_MINUTES = 1020;
  private final int START_OF_WORK_HOURS = 9;
  private final int END_OF_WORK_HOURS = 17;
  private final int TOTAL_WORK_HOURS = 8;
  private int year;
  private int month;
  private int day;
  private int hour;
  private int min;
  private boolean leapYear;

  private String dayOfWeek;
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
    setMonthLength(month);
    setLeapYear(year);
    validInputCheck(this.year, this.month, this.monthLength, this.day, this.hour, this.min);
  }

  private void validInputCheck(int year, int month, MonthLength monthLength, int day, int hour, int min) {
    if (monthLength == MonthLength._28 && (day < 1 || day > 28) && !leapYear) {
      throw new RuntimeException("Invalid day");
    } else if (monthLength == MonthLength._28 && (day < 1 || day > 29)) {
      throw new RuntimeException("Invalid day");
    } else if (monthLength == MonthLength._30 && (day < 1 || day > 30)) {
      throw new RuntimeException("Invalid day");
    } else if (monthLength == MonthLength._31 && (day < 1 || day > 31)) {
      throw new RuntimeException("Invalid day");
    }
    int hourInMin = hour * 60 + min;
    if ((year < 2000 || year > 2099) || (month < 1 || month > 12) || (hour < START_OF_WORK_HOURS || hour > END_OF_WORK_HOURS) ||
        (min < 0 || min > 59) || (hourInMin < MIN_HOURS_IN_MINUTES || hourInMin > MAX_HOURS_IN_MINUTES)) {
      throw new RuntimeException("Invalid date");
    }
    setDayOfWeek();
    if (dayOfWeek.equals("Saturday") || dayOfWeek.equals("Sunday")) {
      throw new RuntimeException("Submit date must be a workday!");
    }
  }

  public Date add(int workday, int hour, int min) {
    if (this.min + min >= 60) {
      hour = hour + min / 60;
      min %= 60;
    }
    if (hour > 0 || workday > 0) {
      addHours(workday, hour);
    }
    setMin(this.min + min);
    return this;
  }

  private void addHours(int workday, int hour) {
    if (hour >= TOTAL_WORK_HOURS) {
      workday = workday + hour / TOTAL_WORK_HOURS;
      hour %= TOTAL_WORK_HOURS;
    }
    if (workday > 0) {
      addDays(workday);
    }
    if (this.hour + hour >= END_OF_WORK_HOURS) {
      setDay(this.day + 1);
      hour = hour + this.hour - END_OF_WORK_HOURS;
      setHour(START_OF_WORK_HOURS);
    }
    setHour(this.hour + hour);
  }

  private void addDays(int workday) {
    if (workday < 0) {
      System.out.println("No changes due to invalid input");
    }
    int remainingDays = workday;
    while (remainingDays > 0) {
      setDay(this.day + 1);
      remainingDays--;
      if (this.dayOfWeek.equals("Saturday")) {
        setDay(this.day + 1);
      }
      if (this.dayOfWeek.equals("Sunday")) {
        setDay(this.day + 1);
      }
    }
  }

  private void nextMonth(int month) {
    if (month == 12) {
      setYear(this.year + 1);
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
      setLeapYear(year);
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
      setMonthLength(month);
    } else {
      System.out.println("month field did not change due to invalid input");
    }
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    if ((this.monthLength == MonthLength._28 && day >= 1 && day <= 28 && !leapYear) ||
        (this.monthLength == MonthLength._28 && day >= 1 && day <= 29 && leapYear) ||
        (this.monthLength == MonthLength._30 && day >= 1 && day <= 30) ||
        (this.monthLength == MonthLength._31 && day >= 1 && day <= 31)
    ) {
      this.day = day;
      setDayOfWeek();
    } else if (this.monthLength == MonthLength._28 && day == 29 ||
        this.monthLength == MonthLength._28 && day == 30 && leapYear ||
        this.monthLength == MonthLength._30 && day == 31 ||
        this.monthLength == MonthLength._31 && day == 32
    ) {
      nextMonth(this.month);
      setDay(1);
      setDayOfWeek();
    } else {
      System.out.println("day field did not change due to invalid input");
    }
  }

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    int hourInMin = hour * 60 + this.min;
    if (hour >= START_OF_WORK_HOURS && hour <= END_OF_WORK_HOURS && (hourInMin >= MIN_HOURS_IN_MINUTES && hourInMin <= MAX_HOURS_IN_MINUTES)) {
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

  private void setMonthLength(int month) {
    if (month == 2) {
      this.monthLength = MonthLength._28;
    } else if (month < 8) {
      this.monthLength = month % 2 == 0 ? MonthLength._30 : MonthLength._31;
    } else {
      this.monthLength = month % 2 == 0 ? MonthLength._31 : MonthLength._30;
    }
  }

  public boolean isLeapYear() {
    return leapYear;
  }

  private void setLeapYear(int year) {
    if (year % 100 == 0 && year % 400 == 0) {
      this.leapYear =  true;
    } else {
      this.leapYear = year % 4 == 0;
    }
  }

  public String getDayOfWeek() {
    return dayOfWeek;
  }

  private void setDayOfWeek() {
    int[] monthValue = {0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5};
    String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    int lastTwoDigitsOfYear = year % 100;
    int dayIndex = (lastTwoDigitsOfYear + lastTwoDigitsOfYear / 4 + day + monthValue[month - 1] + 6) % 7;
    if (leapYear) {
      if (dayIndex > 0) {
        dayIndex--;
      } else {
        dayIndex += 6;
      }
    }
    dayOfWeek = daysOfWeek[dayIndex];
  }

  @Override
  public String toString() {
    String monthString = this.month < 10 ? "0" + this.month : "" + this.month;
    String dayString = this.day < 10 ? "0" + this.day : "" + this.day;
    String hourString = this.hour < 10 ? "0" + this.hour : "" + this.hour;
    String minString = this.min < 10 ? "0" + this.min : "" + this.min;
    return this.year + "." + monthString + "." + dayString + "." + " " + hourString + ":" + minString + " " + dayOfWeek;
  }
}
