package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@3.92.52.96:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";


        //create conn
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //create statement object
        Statement statement=connection.createStatement();

        //run query and get the result in resultSet Object

        ResultSet resultSet=statement.executeQuery("Select first_name,last_name,salary from employees");


        /*
        //move pointer to do first row
        resultSet.next();

        // getting one specific row
        System.out.println("resultSet.getString(\"region_name\") = " + resultSet.getString("region_name"));
        System.out.println("resultSet.getString(2) = " + resultSet.getString(2));

        resultSet.next();
        System.out.println("resultSet.getString(\"region_name\") = " + resultSet.getString("region_name"));
        System.out.println(resultSet.getInt(1)+"-"+resultSet.getString("region_name"));
          */

        while(resultSet.next()){
            System.out.println(resultSet.getString(1)+"-"
                    +resultSet.getString(2)+" "+resultSet.getString("salary"));


        }


        //closeAll Connection
        resultSet.close();
        statement.close();
        connection.close();
    }
}