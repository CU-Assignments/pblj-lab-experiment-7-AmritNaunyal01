

1. **Setup MySQL Database**  
   - Ensure MySQL is installed and running.  
   - Create a database and an `Employee` table with columns `EmpID`, `Name`, and `Salary`.

2. **Update Database Credentials**  
   - Replace `your_database`, `your_username`, and `your_password` in the code with actual database credentials.

3. **Add MySQL JDBC Driver**  
   - Download and add `mysql-connector-java.jar` to your project’s classpath.

4. **Compile and Run the Program**  
   - Compile: `javac MySQLConnection.java`  
   - Run: `java MySQLConnection`

5. **Verify Output**  
   - Ensure that employee records are displayed correctly from the database.

   CODE
   
   import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class EmployeeFetcher {

    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/your_database_name"; // replace with your DB URL
        String user = "your_username"; // replace with your DB username
        String password = "your_password"; // replace with your DB password

        Connection conn = null;
        Statement stmt = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(url, user, password);

            // Create a statement
            stmt = conn.createStatement();

            // SQL query to fetch all records from Employee table
            String query = "SELECT EmpID, Name, Salary FROM Employee";

            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Display the results
            System.out.println("EmpID\tName\t\tSalary");
            System.out.println("------\t--------\t------");

            while (rs.next()) {
                int empId = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");

                System.out.printf("%d\t%-10s\t%.2f%n", empId, name, salary);
            }

            // Close the ResultSet
            rs.close();

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred!");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
