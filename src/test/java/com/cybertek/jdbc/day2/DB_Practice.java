package com.cybertek.jdbc.day2;

import com.cybertek.jdbc.DB_Utilities.DB_Utility;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Practice {

    public static void main(String[] args) throws SQLException {

        // print out all data from Jobs Table
        DB_Utility.createConnection();


        ResultSet resultSet = DB_Utility.runQuery("SELECT * FROM JOBS");
        // ITERATE OVER THE RESULTSET
//        rs.next();
//        // get first 2 column
//        System.out.println(  rs.getString(1)  );

         while(resultSet.next() ){

             System.out.println(  resultSet.getString(1)  );

         }

        System.out.println("columnCount = " + DB_Utility.getColumnCount() );

        // what if we want to print out everything in the resultset
        // without knowing the column names
        //System.out.println(  rs.getString(1) ...2 .3.4.5..6.6.6.7.  );

        // get the first row data  | without knowing the column names

        int colCount = DB_Utility.getColumnCount() ;

        resultSet.first() ; // moving to the first row

        for (int i = 1; i <= colCount ; i++) {

            System.out.print(  resultSet.getString( i )  + "\t" );

        }


        DB_Utility.destroy();

    }

}
