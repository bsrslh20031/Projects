/*Program to display Student Records*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

class Database {
    Connection connection = null;
    String dataBaseName;
    String jdbcUrl;
    String user;
    String password;

    Database(String dataBaseName) {
        this.dataBaseName = dataBaseName;
        jdbcUrl = "jdbc:mysql://localhost:3306/".concat(dataBaseName);
        user = "Harshitha";
        password = "bsrslh2003@";
    }

    Connection getConnection() {
        try {
            connection = DriverManager.getConnection(jdbcUrl, user, password);

        } catch (SQLException sqlException) {
            System.out.println("Error Raised \nConection Failed");
        }
        return connection;
    }
}
public class StudentDetails {
    public static void main(String[] args) {
        Database studentTable = new DatabaseConn("Student");
        Connection connection = studentTable.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResultSetMetaData metaData=null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM STUDENT_INFO");
            resultSet = preparedStatement.executeQuery();

            // Get ResultSetMetaData
            metaData = resultSet.getMetaData();

            // Get column count
            int columnCount = metaData.getColumnCount();
            // System.out.println("Total columns in ResultSet: " + columnCount);

            // Printing the output (Columns Headers)
            System.out.println("\n");
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnLabel(i) + "\t");
            }
            System.out.println("\n");
            //Now Printing the Data 
            while (resultSet.next()) {
                System.out.println("\t" + resultSet.getString(1) + "\t" +
                        resultSet.getString(2) + "\t" +
                        resultSet.getString(3));
            }

        } catch (SQLException sqlException) {
            System.out.println("The Operation Was Unsuccesful");
        } finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();
                
            } catch (SQLException sqlException2) {
                System.out.println("The Objects Didn't CLose");
            }
        }

    }
}