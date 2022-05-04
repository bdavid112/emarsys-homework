package hu;

public class DueDateCalculator {
  // D -> days  H -> hours  M -> minutes
  public static Date calculateDueDate(Date submitDate, int turnaroundD, int turnaroundH, int turnaroundM) {
    submitDate.add(turnaroundD, turnaroundH, turnaroundM);
    return submitDate;
  }
}
