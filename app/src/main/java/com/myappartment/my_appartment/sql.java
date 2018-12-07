package com.myappartment.my_appartment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class sql {
    Connection con;
    Statement stmt;
    public sql() throws SQLException, ClassNotFoundException {


        String url = "jdbc:mysql://localhost:3306/myApartment";
        Class.forName("com.mysql.jdbc.Driver");

        con=DriverManager.getConnection(url
                ,"root","sergey91");
        stmt = con.createStatement();


    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


          ///SQL TEST///
        sql sql = new sql();
        String s = "INSERT INTO idd "+"(id)"+"VALUES(222212)";
        sql.stmt.executeUpdate(s);

    }


}
