package jdbcReviews;

import org.testng.annotations.Test;

import java.sql.*;

public class MainConnections {
    @Test
    public void test1() throws SQLException {

        String dbUrl = "jdbc:oracle:thin:@3.92.52.96:1521:xe";

        String dbUser="hr";
        String dbpassWord="hr";

        Connection connection= DriverManager.getConnection(dbUrl,dbUser,dbpassWord);

        Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("select * from departments");

        resultSet.absolute(3);
        while(resultSet.next()) {
            System.out.println("resultSet.getArray(0) = " + resultSet.getString(2));
        }
        System.out.println("TEST");
        resultSet.isBeforeFirst();
        resultSet.absolute(1);
        System.out.println("resultSet.findColumn(\"department_id\") = " + resultSet.findColumn("department_id"));
        System.out.println("resultSet.getArray(2) = " + resultSet.getString(2));

        resultSet.last();
        System.out.println("resultSet.relative(1) = " + resultSet.relative(-1));
        System.out.println("resultSet.getRow() = " + resultSet.getRow());


        System.out.println("resultSet.getRow() = " + resultSet.getRow());

        resultSet.close();
        statement.close();
        connection.close();
    }


}
