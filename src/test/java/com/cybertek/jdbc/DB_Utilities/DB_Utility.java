package com.cybertek.jdbc.DB_Utilities;

import java.sql.*;
import java.util.*;

public class DB_Utility {


    // adding static field so we can access in all static methods
     private static Connection connection ;
     private static  ResultSet resultSet ;
     private static ResultSetMetaData resultSetMetaData;
     private static  Statement statement;

     /*
     closing all resources
      */

     public static void destroy(){

         try{

            if(resultSet != null){

                resultSet.close();

            }

            if(statement != null){

                statement.close();
            }

            if(connection != null){

                connection.close();
            }

         }catch (SQLException e){

             System.out.println("ERROR WHILE CLOSING");

             e.printStackTrace();
         }
     }


     /*
     we want to store certain row data as a map
     give me number 3 row ===> Map<String, String> ()
      */
     public static Map<String, String> getRowAsMap(int rowNumber){

         Map<String, String> rowMap = new LinkedHashMap<>();//linkedHashMap keeps insertion order

         try{

             resultSet.absolute(rowNumber);

             resultSetMetaData = resultSet.getMetaData();

             for (int columnNumber = 1; columnNumber <= getColumnCount(); columnNumber++) {

                 String columnName =  resultSetMetaData.getColumnName(columnNumber);

                 String columnValue = resultSet.getString(columnNumber);

                 rowMap.put(columnName, columnValue);
             }

         }catch (SQLException e){

             System.out.println("Error at row map function");
             e.printStackTrace();
         }


         return rowMap;
     }

    /**
     *
     * @return The entire resultset as List of Row Map
     *
     * so it's like : List = { map of 1st row}, { map of 2nd row}, {map of 3rd row}..
     */

    public static List<Map<String,String> > getAllDataAsListOfMap(){

        List<Map<String,String> > rowMapList = new ArrayList<>();

        for (int i = 1; i <= getRowCount(); i++) {

            rowMapList.add(   getRowAsMap(i)    ) ;
        }

        return rowMapList ;
    }



     /*
     @param columnIndex the column u want to get the list of
     @return list of string
      */

     public static List<String> getColumnDataAsList(int columnIndex){

         List<String> columnDataList = new ArrayList<>();

         try {

             resultSet.beforeFirst();

            while(resultSet.next()){

                //getting the data from column and adding it to the list
                String data = resultSet.getString(columnIndex);

                columnDataList.add(data);
            }

             resultSet.beforeFirst();

         } catch (SQLException e) {

             System.out.println("Error while getting column data into the list");
             e.printStackTrace();
         }

         return columnDataList;

     }


     /*
     overload the method with string parameter
      */
     public static List<String> getColumnDataAsList(String columnName){

         List<String> columnDataList = new ArrayList<>();

         try {

             resultSet.beforeFirst();

             while(resultSet.next()){

                 //getting the data from column and adding it to the list
                 String data = resultSet.getString(columnName);

                 columnDataList.add(data);
             }

             resultSet.beforeFirst();

         } catch (SQLException e) {

             System.out.println("Error while getting column data into the list");
             e.printStackTrace();
         }

         return columnDataList;

     }




    /*
    getting entire row data and store it into a list of strings
    @param rowNum the row number you want the list from
    @return list of string that contains the row data
     */

    public static List<String> getEntireRowDataAsList(int rowNum){

        List<String> rowDataList = new ArrayList<>();

        //how to move to that row number
        try {

            resultSet.absolute(rowNum);

            //iterate each column and add the value to the list
            for (int i = 1; i <= getColumnCount(); i++) {

                rowDataList.add(resultSet.getString(i));
            }

            //moving cursor back to before first location just in case
            resultSet.beforeFirst();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return  rowDataList;
    }

    /**
     getting single column cell value at certain row
     * @param rowNum    row number we want to get data from
     * @param columnIndex  column index we want to get the data from
     * @return the data in String
     */
    public static String getColumnDataAtRow(int rowNum, int columnIndex){

        //improve this method and check for valid rowNum and columnIndex
        //if invalid return empty string
        String result = "";

        try {

            resultSet.absolute(rowNum);

            result = resultSet.getString(columnIndex);

        } catch (SQLException e) {

            System.out.println("Error while getting column data at row");

            e.printStackTrace();
        }

        return result;

    }



    /*
    overloaded above method, it takes different type of parameter: int and String
     */

    public static String getColumnDataAtRow(int rowNum, String columnName){

        //improve this method and check for valid rowNum and columnIndex
        //if invalid return empty string
        String result = "";

        try {

            resultSet.absolute(rowNum);

            result = resultSet.getString(columnName);

        } catch (SQLException e) {

            System.out.println("Error while getting column data at row");

            e.printStackTrace();
        }

        return result;

    }



    /*
     * a method to display all the data in the result set
     *  get all row data  | without knowing the column names
     * */
     public static void displayAllData(){

         int colCount = DB_Utility.getColumnCount() ;
         // in order to get whole result cursor must be at before first location !

         try {
             // in order to start from beginning , we should be at beforefirst location
             resultSet.beforeFirst();

             while (resultSet.next()) { // row iteration

                 for (int i = 1; i <= colCount; i++) { // column iteration

                     System.out.print(resultSet.getString(i) + "\t");

                 }

                 System.out.println(); /// adding a blank line for next line
             }

             // now the cursor is at after last location
             // move it back to before first location so we can have no issue calling the method again
             resultSet.beforeFirst();

         }catch(SQLException e){

             System.out.println("ERROR WHILE GETTING ALL DATA");
             e.printStackTrace();
         }

     }

         /*
    getting a row count
     */

    public static int getRowCount(){

        int rowCount = 0;

        try {

            resultSet.last();

            rowCount = resultSet.getRow();

            //moving cursor to the initial position again
            resultSet.beforeFirst();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return rowCount;
    }



    /*
     * a method to get the column count of the current ResultSet
     *
     *   getColumnCNT()
     *
     * */
     public static int getColumnCount(){

         int columnCount = 0  ;

         try {

             resultSetMetaData= resultSet.getMetaData();

             columnCount = resultSetMetaData.getColumnCount() ;

         } catch (SQLException e) {

             System.out.println("ERROR WHILE COUNTING THE COLUMNS");

             e.printStackTrace();
         }

        return columnCount ;
     }



    /*
     * a static method to get the ResultSet object
     * with valid connection by executing query
     *
     * */
    public static ResultSet runQuery(String query){

        try {

           statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,

                    ResultSet.CONCUR_READ_ONLY);

            resultSet =  statement.executeQuery(query) ;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return  resultSet;
    }


    public static void createConnection(String env){

        String connectionStr = ConfigurationReader.getProperty(env+".database.url");

        String username = ConfigurationReader.getProperty(env+".database.username");

        String password = ConfigurationReader.getProperty(env+".database.password");

       createConnection(connectionStr, username, password);
        //just calling another method which creates a connection in order not to duplicate

        System.out.println("You are in "+env+" environment");
    }

    /*

    overload createConnection method by passing three parameters: connectionString, username, password
    so we can provide those info for different database

* @param url ==> connectionString
 * @param username
 * @param password
     */
    public static void createConnection(String connectionString,String username, String password){

        try {

            connection = DriverManager.getConnection(connectionString, username, password);

            System.out.println("CONNECTION SUCCESSFUL");

        }catch(SQLException e){

            System.out.println("Error while connecting with parameters");

            e.printStackTrace();

        }


    }


    /*
     * a static method to create connection between java and database
     * with valid url and username password
     * */
    public static void createConnection() {

        String connectionStr = ConfigurationReader.getProperty("database.url");

        String username = ConfigurationReader.getProperty("database.username");

        String password = ConfigurationReader.getProperty("database.password");

        try {

            connection = DriverManager.getConnection(connectionStr, username, password);

            System.out.println("CONNECTION SUCCESSFUL");


        } catch (SQLException e) {

            System.out.println("CONNECTION HAS FAILED!");

            e.printStackTrace();
        }

       // createConnection(connectionStr,username,password);

    }

}
