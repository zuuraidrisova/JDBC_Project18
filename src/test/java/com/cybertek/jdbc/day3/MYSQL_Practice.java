package com.cybertek.jdbc.day3;

import com.cybertek.jdbc.DB_Utilities.DB_Utility;

public class MYSQL_Practice {

    public static void main(String[] args) {


        // added mysql pom dependency for mysql driver
        // we provided connection string , username password
        // and used the utility to make connection

        String connectionStr = "jdbc:mysql://18.233.97.71:3306/library1";
        String username = "library1_client" ;
        String password = "WVF4NdGXCKHeE6VQ" ;

        DB_Utility.createConnection(connectionStr,username,password);

        DB_Utility.destroy();

    }

}
