package com.cybertek.jdbc.day2;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Practice2 {

    public static void main(String[] args) throws SQLException {

        // print out all data from Jobs Table
        DB_Utility.createConnection();

        ResultSet resultSet = DB_Utility.runQuery("SELECT * FROM REGIONS");

        // this method call is displaying the data of the resultset
        DB_Utility.displayAllData();

        // move resultset cursor to second row
        //resultSet.absolute(2);

        DB_Utility.displayAllData();

        System.out.println("DB_Utility.getColumnDataAtRow(3,2) = " + DB_Utility.getEntireColumnDataAtRow(3, 2));


        System.out.println("DB_Utility.getColumnDataAtRow(2, \"region_name\") = " + DB_Utility.getEntireColumnDataAtRow(2, "region_name"));

        // we get the entire data of row 4 as a list
        System.out.println("DB_Utility.getEntireRowDataAsList(4) = " + DB_Utility.getEntireRowDataAsList(4));


        DB_Utility.runQuery("select * from  employees");
        System.out.println("DB_Utility.getColumnDataAsList(1) = " + DB_Utility.getColumnDataAsList(2));


        DB_Utility.runQuery("select * from  employees");
        System.out.println("DB_Utility.getColumnDataAsList(\"last_name\") = " + DB_Utility.getColumnDataAsList("last_name"));


        DB_Utility.destroy();
    }

}
