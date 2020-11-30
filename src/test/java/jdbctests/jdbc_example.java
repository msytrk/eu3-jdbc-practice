package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class jdbc_example {

    String dbUrl = "jdbc:oracle:thin:@3.80.189.73:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void test1() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }
        //========================================
        resultSet = statement.executeQuery("select * from regions");

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void CountNavigate() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //how to find how many rows we have for the query
        //go to last row
        resultSet.last();
        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);
        //we need move before first row to get full list since we are at he last row right now.
        resultSet.beforeFirst();

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }



        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void MetaDataExample() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //get the database related data inside the dbMetaDate object
        DatabaseMetaData dbMetadata=connection.getMetaData();
        System.out.println("User =" + dbMetadata.getUserName());
        System.out.println("Database Product Name = " + dbMetadata.getDatabaseProductName());
        System.out.println("Database Product Version = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver Name = " + dbMetadata.getDriverName());
        System.out.println("Driver Version = " + dbMetadata.getDriverVersion());


        //get the database resultSet Object metadata

        ResultSetMetaData rsMetData= resultSet.getMetaData();

        //how many columns we have

        System.out.println("rsMetData.getColumnCount() = " + rsMetData.getColumnCount());

        //Column name

        System.out.println("rsMetData.getColumnName(1) = " + rsMetData.getColumnName(1));

        //Table name

        System.out.println("rsMetData.TableName(2) = " + rsMetData.getSchemaName(2));

        for (int i = 1; i <=rsMetData.getColumnCount(); i++) {
            System.out.println("rsMetData.getColumnName(1) = " + rsMetData.getColumnName(i));
        }



        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }







}