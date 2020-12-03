package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day2 {
    private String fileName;
    private ArrayList<String> lines;

    Day2(String fileName){
        this.fileName = fileName;
        lines = new ArrayList<>();

        try{
            File dataReader = new File(fileName);
            Scanner fileReader = new Scanner(dataReader);

            while(fileReader.hasNext()){
                lines.add(fileReader.nextLine());
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public int validPasswordsPart1(){
        int validPasswordCount = 0;

        for(int i = 0; i < lines.size(); i++){
            String[] lineSplit = lines.get(i).split("[: \\- \\s++]");

            int min = Integer.parseInt(lineSplit[0]);
            int max = Integer.parseInt(lineSplit[1]);
            char charCheck = lineSplit[2].charAt(0);
            String password = lineSplit[lineSplit.length-1];

            if(isPasswordValidOccurance(min, max, charCheck, password))
                validPasswordCount++;
        }

        System.out.println("Number of valid passwords - Day 2 Part 1: " + validPasswordCount);
        return validPasswordCount;
    }

    private boolean isPasswordValidOccurance(int min, int max, char repeat, String password){
        HashMap<Character, Integer> charCount = new HashMap<>();

        for(char cStr : password.toCharArray()){
            charCount.put(cStr, charCount.getOrDefault(cStr, 0) + 1);
        }

        return (charCount.containsKey(repeat) && (charCount.get(repeat) >= min && charCount.get(repeat) <= max));
    }

    public int validPasswordsPart2(){
        int validPasswordCount = 0;

        for(int i = 0; i < lines.size(); i++){
            String[] lineSplit = lines.get(i).split("[: \\- \\s++]");

            int min = Integer.parseInt(lineSplit[0]);
            int max = Integer.parseInt(lineSplit[1]);
            char charCheck = lineSplit[2].charAt(0);
            String password = lineSplit[lineSplit.length-1];

            if((password.charAt(min-1) == charCheck && password.charAt(max-1) != charCheck) ||
                    (password.charAt(min-1) != charCheck && password.charAt(max-1) == charCheck))
                validPasswordCount++;
        }

        System.out.println("Number of valid passwords - Day 2 Part 2: " + validPasswordCount);
        return validPasswordCount;
    }
}
