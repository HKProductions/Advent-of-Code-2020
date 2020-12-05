package com.company;

import java.io.File;
import java.util.*;

public class Day5 {
    private ArrayList<String> boardingPass;
    private int lower;
    private int upper;

    Day5(String fileName){
        boardingPass = new ArrayList<>();

        try{
            File dataReader = new File(fileName);
            Scanner fileReader = new Scanner(dataReader);

            while(fileReader.hasNext()){
                 boardingPass.add(fileReader.nextLine());
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public PriorityQueue<Integer> maxSeatID(){
        PriorityQueue<Integer> maxID = new PriorityQueue<>((a,b) -> b - a);

        for(String pass : boardingPass){
            lower = 0;
            upper = 127;
            int column = 0;
            int row = 0;

            for(int i = 0; i < 6; i++){
                adjustBounds(pass.charAt(i), 'F');
            }

            if(pass.charAt(6) == 'F')
                column = lower;
            else
                column = upper;

            lower = 0;
            upper = 7;
            for(int i = 7; i < pass.length()-1; i++){
                adjustBounds(pass.charAt(i), 'L');
            }

            if(pass.charAt(pass.length()-1) == 'L')
                row = lower;
            else
                row = upper;

            maxID.add((column * 8) + row);
        }

        System.out.println("Max Seat ID - Day 5 Part 1: " + maxID.peek());
        return maxID;
    }

    private void adjustBounds(char c, char lowerChar){
        int mid = (lower + upper) / 2;
        if(c == lowerChar)
            upper = mid;
        else
            lower = mid+1;
    }

    public void findSeat(){
        PriorityQueue<Integer> seats = maxSeatID();

        int missingSeat = 0;
        while(!seats.isEmpty()){
            int back = seats.poll();
            int front = seats.poll();

            if(back - front > 1) {
                missingSeat = back - 1;
                break;
            }
        }

        System.out.println("Missing seat - Day 5 Part 2: " + missingSeat);
    }
}
