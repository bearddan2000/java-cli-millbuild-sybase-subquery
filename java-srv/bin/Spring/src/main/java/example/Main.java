
package example;

import java.util.*;

import org.apache.log4j.PropertyConfigurator;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class Main {

  private static void print(
    final String username,
    final String password,
    final String connectionStr
  ){
    final String schema = "master.dbo";
    
    List<String> lst = new ArrayList<String>(){{
      add(String.format("%s.%s", schema, "dog"));
      add(String.format("%s.%s", schema, "breedLookup"));
      add(String.format("%s.%s", schema, "colorLookup"));
      add(String.format("%s.%s", schema, "dog_expanded"));
      add(String.format("%s.%s", schema, "breed_count"));
    }};

    example.db.print.output.IOutput output = new example.db.print.output.ToConsole();
    for(Iterator<String> itr = lst.iterator(); itr.hasNext();){
      String tbl = itr.next();
      System.out.println(String.format("Table: %s", tbl));
      example.db.DBQuery.query(username, password, connectionStr, tbl, output);
    }
  }
  private static void seed(){

    final String username = System.getenv("DB_USERNAME");
    final String password = System.getenv("DB_PASSWORD");
    final String driver = System.getenv("DB_DRIVER");
    final String connectionStr = System.getenv("DB_CONNECTION_STR");

    List<example.dto.SQLOPT> lst = new ArrayList<example.dto.SQLOPT>(){{
      add(example.dto.SQLOPT.CREATE);
      add(example.dto.SQLOPT.INDEX);
      add(example.dto.SQLOPT.INSERT);
      add(example.dto.SQLOPT.CREATE);
      add(example.dto.SQLOPT.INSERT);
      add(example.dto.SQLOPT.CREATE);
      add(example.dto.SQLOPT.INSERT);
      add(example.dto.SQLOPT.VIEW);
      add(example.dto.SQLOPT.VIEW);
    }};

    example.dto.Dog dog = new example.dto.Dog(driver, username, password, connectionStr);
    for(Iterator<example.dto.SQLOPT> itr = lst.iterator(); itr.hasNext();)
      dog.operation(itr.next());

    print(username, password, connectionStr);
  }

  public static void main(String[] args) {

    String PWD = System.getenv("PWD");
    PropertyConfigurator.configure(PWD + "/src/main/resources/log4j.xml");

    seed();
  }
}
