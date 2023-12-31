package example.dto;

import org.apache.log4j.Logger;

// Importing required classes
import java.io.*;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Dog {
  private static final Logger logger = Logger.getLogger(Dog.class);

  String USERNAME = null;
  String PASSWORD = null;

  // JDBC driver name and database URL
  String JDBC_DRIVER = null;
  String DB_URL = null;

  int index = 0;

  public Dog(String dbDriver, String connectionStr)
  {
    JDBC_DRIVER = dbDriver;
    DB_URL = connectionStr;
  }

  public Dog(String dbDriver, String user, String psw, String connectionStr)
  {
    JDBC_DRIVER = dbDriver;
    USERNAME = user;
    PASSWORD = psw;
    DB_URL = connectionStr;
  }

  private String readSQLFile(String sqlFile) throws IOException
  {
    String PWD = System.getenv("PWD");
    // Now calling Files.readString() method to
    // read the file
    String fileName = PWD + "/src/main/resources/sql/" + sqlFile;
    // Now calling Files.readString() method to
    // read the file
    String str = new String(
      java.nio.file.Files.readAllBytes(
      java.nio.file.Paths.get(fileName)), java.nio.charset.StandardCharsets.UTF_8);

    str = str.trim().replace("\t", " ").replace("\r", "").replace("\n", "");

    // Printing the string

    return str;
  }
  public void operation(SQLOPT opt)
  {
    Connection conn = null;
    Statement stmt = null;
    String fileName = String.format("%02d%s", this.index++, opt.sqlFile);
    System.out.println(String.format("SQL File: %s", fileName));
    try {
       // STEP 1: Register JDBC driver
       Class.forName(JDBC_DRIVER);

       //STEP 2: Open a connection
       logger.info("Connecting to database...");
       if (USERNAME == null) {
         logger.info("Connecting with no credentials");
         conn = DriverManager.getConnection(DB_URL);
       }
       else {
         logger.info("Connecting with credentials");
         conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
       }


       //STEP 3: Execute a query
       logger.info(opt.operation + " table in given database...");
       stmt = conn.createStatement();
       String sql =  this.readSQLFile(fileName);

        stmt.executeUpdate(sql);

       logger.info(opt.operation + " table in given database...");

       // STEP 4: Clean-up environment
       stmt.close();
       conn.close();
    } catch(SQLException se) {
       //Handle errors for JDBC
       se.printStackTrace();
    } catch(Exception e) {
       //Handle errors for Class.forName
       e.printStackTrace();
    } finally {
       //finally block used to close resources
       try{
          if(stmt!=null) stmt.close();
       } catch(SQLException se2) {
       } // nothing we can do
       try {
          if(conn!=null) conn.close();
       } catch(SQLException se){
          se.printStackTrace();
       } //end finally try
    } //end try
    logger.info("Goodbye!");
  }
}
