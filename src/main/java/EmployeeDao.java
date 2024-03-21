import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

  private static final String url = "jdbc:postgresql://localhost:5432/postgres?schema=employees_schema";
  private static final String username = "postgres";
  private static final String password = "111";

  public static List<Employee> findByLastName(String lastName) {
    registerDriver();
    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      if (connection == null) {
        throw new RuntimeException("Cannot connect to database");
      }
      System.out.println("Connected to database");
      try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees_schema.employee e WHERE e.last_name=?")) {
        preparedStatement.setString(1, lastName);
        // Выполнение запроса и получение результатов
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
          // Обработка результатов запроса
          List<Employee> employees = new ArrayList<>();
          while (resultSet.next()) {
            employees.add(
                new Employee(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age"),
                    resultSet.getString("gender")));
          }
          return employees;
        }
      } catch (SQLException e) {
        throw new RuntimeException("Error executing query", e);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Error connecting to database", e);
    }
  }

  private static void registerDriver() {
    try {
      DriverManager.registerDriver(new org.postgresql.Driver());
    } catch (SQLException e) {
      throw new RuntimeException("Error registering driver", e);
    }
  }

}
