package com.cybertek.jdbc.day2;

import com.cybertek.jdbc.DB_Utilities.DB_Utility;

public class methodCalls {

    public static void main(String[] args) {

        DB_Utility.createConnection();
        DB_Utility.runQuery("select * from jobs");

        DB_Utility.displayAllData();

        System.out.println("Column count is: "+DB_Utility.getColumnCount());

        System.out.println("Row count is: "+DB_Utility.getRowCount());

        System.out.println("Getting 3rd of jobs table as a list : "+ DB_Utility.getEntireRowDataAsList(3));

        System.out.println("Getting 2nd column as a list: "+ DB_Utility.getColumnDataAsList(2));

        System.out.println("Getting job_title column as a list: "+DB_Utility.getColumnDataAsList("job_title"));

        System.out.println("Getting 3rd row, 2nd column data: "+DB_Utility.getEntireColumnDataAtRow(3,2));

        System.out.println("Getting 3rd row, job_title column data: "+DB_Utility.getEntireColumnDataAtRow(3, "job_title"));

        System.out.println("Getting the data at 3rd row as a map: "+DB_Utility.getRowAsMap(3));

        DB_Utility.destroy();
    }
}
