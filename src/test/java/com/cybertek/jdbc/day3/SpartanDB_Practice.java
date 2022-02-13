package com.cybertek.jdbc.day3;

import com.cybertek.jdbc.DB_Utilities.DB_Utility;
import static  com.cybertek.jdbc.DB_Utilities.DB_Utility.*;

public class SpartanDB_Practice {


    public static void main(String[] args) {

//        String connectionString = "jdbc:oracle:thin:@52.71.242.164:1521:XE";
//        String username = "SP";
//        String password = "SP";

        DB_Utility.createConnection("test");



//     Run query "SELECT * FROM EMPLOYEES"

        DB_Utility.runQuery("select * from spartans");




//    1. Display all data in console
      //  DB_Utility.displayAllData();





//    2. Print column count

        int columnCount = getColumnCount();
        System.out.println("columnCount = " + columnCount);




//    3. Print row count

        int rowCount = getRowCount();
        System.out.println("rowCount = " + rowCount);





//    4. Print out 3rd row data as a list

        System.out.println("getEntireRowDataAsList(3) = " + getEntireRowDataAsList(3));




//    5. Print out 2nd column data as a list

        System.out.println("DB_Utility.getColumnDataAsList(2) = " + getColumnDataAsList(2));




//    6, Print out Name column data as a list

        System.out.println("DB_Utility.getColumnDataAsList(\"Name\") = " + getColumnDataAsList("Name"));



//    7, Print out 4th row as a Map

        System.out.println("getRowAsMap(4) = " + getRowAsMap(4));




//    8, Print out the data at row 5, column 1




        System.out.println("getEntireColumnDataAtRow(5,1) = " + getColumnDataAtRow(5, 1));





//    9, Print out the data at row 53, phone column

        System.out.println("getEntireColumnDataAtRow(53, \"phone\") = " + getColumnDataAtRow(53, "phone"));





//    10. Print out all the data as List of Map
        System.out.println("getAllDataAsListOfMap() = " + getAllDataAsListOfMap());


        DB_Utility.destroy();

    }



}
