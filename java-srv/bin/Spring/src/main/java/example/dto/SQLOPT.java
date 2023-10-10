package example.dto;

public enum SQLOPT {
  CREATE("-create-table.sql", "Create"),
  INDEX("-create-index.sql", "Index"),
  INSERT("-insert-table.sql", "Insert"),
  VIEW("-create-view.sql", "View");

  public final String sqlFile;
  public final String operation;

  private SQLOPT(String file, String op)
  {
    this.sqlFile = file;
    this.operation = op;
  }
}
