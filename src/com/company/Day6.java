package com.company;

import java.io.File;
import java.util.*;

public class Day6 {
    List<List<String>> questionaireGroups;

    Day6(String fileName){
        questionaireGroups = new ArrayList<>();

        try{
            File dataReader = new File(fileName);
            Scanner fileReader = new Scanner(dataReader);

            while(fileReader.hasNext()){
                List<String> groups = new ArrayList<>();
                String nextLine = fileReader.nextLine();

                while(!nextLine.isEmpty()){
                    groups.add(nextLine);

                    if(fileReader.hasNext())
                        nextLine = fileReader.nextLine();
                    else
                        break;
                }
                questionaireGroups.add(groups);
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public int countAnswersPart1(){
        int count = 0;

        for(List<String> s : questionaireGroups){
            int[] respones = new int[26];
            for(int i = 0; i < s.size(); i++) {
                for (char c : s.get(i).toCharArray()) {
                    respones[c-'a']++;
                }
            }

            for(int res : respones)
            count += (res > 0) ? 1 : 0;
        }
        System.out.println("Count of YES respones - Day 6 Part 1: " + count);
        return count;
    }

    public int countAnswersPart2(){
        int count = 0;
        for(List<String> s : questionaireGroups){
            int[] respones = new int[26];
            for(int i = 0; i < s.size(); i++) {
                for (char c : s.get(i).toCharArray()) {
                    respones[c-'a']++;
                }
            }

            for(int res : respones){
                if(res == s.size())
                    count++;
            }
        }
        System.out.println("Count of YES respones - Day 6 Part 2: " + count);
        return count;
    }
}
