package com.cybertek.jdbc.day2;

import com.cybertek.jdbc.DB_Utilities.DB_Utility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DB_Practice3 {


    public static void main(String[] args) throws SQLException {

        DB_Utility.createConnection();

        ResultSet resultSet = DB_Utility.runQuery("select * from countries");

        //store first row data as a map of string and string
        //the key of the map is column_name, the value of map is column_date

        Map<String, String> rowMap = new HashMap<>();

      // rowMap.put("region_id", resultSet.getString(1));
       //rowMap.put("region_name", resultSet.getString(2));

        resultSet.next();

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        for (int i = 1; i <= resultSetMetaData.getColumnCount() ; i++) {

            //System.out.println(resultSetMetaData.getColumnName(1));
            String columnName = resultSetMetaData.getColumnName(i);

            String columnValue =  resultSet.getString(i);

            rowMap.put(columnName, columnValue);

        }

        System.out.println("rowMap = " + rowMap);

        System.out.println("Country name is "+ rowMap.get("country_name"));

        DB_Utility.destroy();

    }
}
