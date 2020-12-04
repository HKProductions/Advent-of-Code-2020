package com.company;

import java.io.File;
import java.util.*;

public class Day4 {
    ArrayList<String> passports;

    Day4(String fileName){
        passports = new ArrayList<>();

        try{
            File dataReader = new File(fileName);
            Scanner fileReader = new Scanner(dataReader);

            while(fileReader.hasNext()){
                StringBuilder sb = new StringBuilder();
                String nextLine = fileReader.nextLine();

                while(!nextLine.isEmpty()) {
                    sb.append(nextLine + " ");

                    if(fileReader.hasNext())
                        nextLine = fileReader.nextLine();
                    else
                        break;
                }

                if(sb.length() > 0)
                    passports.add(sb.toString());
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public ArrayList<String> validPassportCountPart1(String skipKey){
        int validCount = 0;
        ArrayList<String> res = new ArrayList<>();

        for(String s : passports){
            String[] splitPassport = s.split("[\\s++]");
            Set<String> validKey = new HashSet<>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"));
            validKey.remove(skipKey);

            for(String splitStr : splitPassport){
                String[] strSplit = splitStr.split("[:]");

                if(validKey.contains(strSplit[0]))
                    validKey.remove(strSplit[0]);
            }

            if(validKey.size() == 0) {
                validCount++;
                res.add(s);
            }
        }

        System.out.println("Valid Passport Count - Day 4 Part 1: " + validCount);
        return res;
    }

    public int validPassportCountPart2(String skipKey){
        ArrayList<String> validPassports = validPassportCountPart1(skipKey);
        int validCount = 0;

        for(String s : validPassports){
            String[] splitPassport = s.split("[\\s++]");
            Set<String> validKey = new HashSet<>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"));
            validKey.remove(skipKey);

            for(String splitStr : splitPassport){
                String[] strSplit = splitStr.split("[:]");
                String key = strSplit[0];
                String value = strSplit[1];

                if(validKey.contains(key)){
                    if(key.equals("byr") || key.equals("iyr") || key.equals("eyr")){
                        if(value.length() == 4){

                            StringBuilder sb = new StringBuilder();
                            for(char c : value.toCharArray()){
                                int numChar = c - '0';
                                if(numChar >= 0 && numChar <= 9)
                                    sb.append(numChar);
                                else
                                    break;
                            }

                            int number = (sb.length() > 0) ? Integer.parseInt(sb.toString()) : 0;
                            if(number >= 1920 && number <= 2002 && key.equals("byr"))
                                validKey.remove(key);
                            else if(number >= 2010 && number <= 2020 && key.equals("iyr"))
                                validKey.remove(key);
                            else if(number >= 2020 && number <= 2030 && key.equals("eyr"))
                                validKey.remove(key);
                        }
                    }
                    else if(key.equals("hgt")){
                        String unitsOfMeasure = value.substring(value.length() - 2);
                        StringBuilder sb = new StringBuilder();
                        boolean fullNumConversion = true;

                        for(int i = 0; i < value.length()-2; i++){
                            int numCharConversion = value.charAt(i) - '0';
                            if(numCharConversion >= 0 && numCharConversion <= 9)
                                sb.append(numCharConversion);
                            else {
                                fullNumConversion = false;
                                break;
                            }
                        }

                        int units = (sb.length() > 0 && fullNumConversion) ? Integer.parseInt(sb.toString()) : 0;
                        if(units >= 150 && units <= 193 && unitsOfMeasure.equals("cm"))
                            validKey.remove(key);
                        else if(units >= 59 && units <= 75 && unitsOfMeasure.equals("in"))
                            validKey.remove(key);
                    }
                    else if(key.equals("hcl")){
                        if(value.charAt(0) == '#'){
                            boolean validHair = true;

                            for(int i = 1; i < value.length(); i++){
                                int numCheck = value.charAt(i) - '0';
                                int alphaCheck = value.charAt(i) - 'a';

                                if(numCheck < 0 || numCheck > 9){
                                    if(alphaCheck < 0 || alphaCheck > 5){
                                        validHair = false;
                                        break;
                                    }
                                }
                            }

                            if(validHair)
                                validKey.remove(key);
                        }
                    }
                    else if(key.equals("ecl")){
                        HashSet<String> eyeColorMap = new HashSet<>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));

                        if(eyeColorMap.contains(value))
                            validKey.remove(key);
                    }
                    else if(key.equals("pid")){
                        if(value.length() == 9){

                            boolean fullNumberConversion = true;
                            for(char c : value.toCharArray()){
                                int numChar = c - '0';
                                if(numChar < 0 || numChar > 9){
                                    fullNumberConversion = false;
                                    break;
                                }
                            }

                            if(fullNumberConversion)
                                validKey.remove(key);
                        }
                    }
                    else if(key.equals("cid")){
                        validKey.remove(key);
                        continue;
                    }
                }
            }

            if(validKey.size() == 0)
                validCount++;
        }

        System.out.println("Valid Passport Count - Day 4 Part 2: " + validCount);
        return validCount;
    }
}
