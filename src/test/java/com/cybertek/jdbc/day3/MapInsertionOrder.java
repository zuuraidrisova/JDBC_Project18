package com.cybertek.jdbc.day3;

import java.util.HashMap;
import java.util.Map;

public class MapInsertionOrder {

    public static void main(String[] args) {

        Map<String, Integer> scoreMap = new HashMap<>();

        //hashmap does not keep the insertion order, and does not sort so it is faster

        scoreMap.put("USA",10);
        scoreMap.put("Japan",9);
        scoreMap.put("Canada",8);
        scoreMap.put("EU",6);

        System.out.println(scoreMap);


    }
}
