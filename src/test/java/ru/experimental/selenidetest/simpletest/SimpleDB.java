package ru.experimental.selenidetest.simpletest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SimpleDB extends TestBase {

    // параметры подключения JDBC URL, username and password
    private static final String url = "jdbc:oracle:thin:@localhost:49161";
    private static final String user = "system";
    private static final String password = "oracle";

    // JDBC переменные для установки и управления подключением
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public  void countSQLRequest() {
        String query = "select count(*) from bank_customers";

        try {
            // установка соединения с БД
            con = DriverManager.getConnection(url, user, password);

            stmt = con.createStatement();

            // выполенения SELECT запроса
            rs = stmt.executeQuery(query);
            // счетчик
            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Total number of bank_customers in the table : " + count);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

    public String firstRowQueryDB(String query) {

        try {
            // устанавливаекм соединение
            con = DriverManager.getConnection(url, user, password);

            stmt = con.createStatement();

            // выполнение SELECT запроса
            rs = stmt.executeQuery(query);
            // получаем результат запроса
            while (rs.next()) {
                String result = rs.getString(1);
                System.out.println("Debug SQL query return : " + result);
                return result;
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }

        }
        return " ";
    }

}
