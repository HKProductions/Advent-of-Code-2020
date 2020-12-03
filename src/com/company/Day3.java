package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {
    private String fileName;
    private char[][] map;

    Day3(String fileName){
        this.fileName = fileName;
        ArrayList<String> lines;lines = new ArrayList<>();

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

        this.map = new char[lines.size()][lines.get(0).length()];
        for(int i = 0; i < lines.size(); i++){
            String line = lines.get(i);
            int strIndex = 0;

            for(int j = 0; j < this.map[0].length; j++){
                if(strIndex >= line.length())
                    strIndex = 0;

                this.map[i][j] = line.charAt(strIndex++);
            }
        }
    }

    public int treeHitsPart1(int right, int down){
        int treeHits = depthFirstSearch(0, 0, right, down, 0);
        System.out.println("Number of Tree Hits - Day 3 Part 1: " + treeHits);

        return treeHits;
    }

    private int depthFirstSearch(int row, int col, int slopeRight, int slopeDown, int treeHits){
        if(row >= map.length)
            return treeHits;

        if(col >= map[0].length)
            col %= map[0].length;

        if(map[row][col] == '#')
            treeHits++;

        row += slopeDown;
        col += slopeRight;

        return depthFirstSearch(row, col, slopeRight, slopeDown, treeHits);
    }
}
