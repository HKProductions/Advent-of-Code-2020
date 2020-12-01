package com.company;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        sum2020Multiple(); //<- Day 1
    }

    public static void sum2020Multiple(){
        List<Integer> expense = new ArrayList<>();
        List<Integer> sum2020 = new ArrayList<>();

        try{
            File dataImport = new File("/Users/hk/Desktop/Advent-of-Code-2020/data/day1Part1.txt");
            Scanner dataReader = new Scanner(dataImport);

            while(dataReader.hasNext()){
                String line = dataReader.nextLine();
                expense.add(Integer.parseInt(line));
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }

        find2020Sum(expense, sum2020, 2020, 2, 0);
        System.out.print("Multiple of 2020 - Day 1 Part 1: ");
        int sum = 1;
        for(int i = 0; i < sum2020.size(); i++){
            sum *= sum2020.get(i);
        }
        System.out.println(sum);

        sum2020 = new ArrayList<>();
        find2020Sum(expense, sum2020, 2020, 3, 0);
        System.out.print("Multiple of 2020 - Day 1 Part 2: ");
        sum = 1;
        for(int i = 0; i < sum2020.size(); i++){
            sum *= sum2020.get(i);
        }
        System.out.println(sum);
    }

    private static boolean find2020Sum(List<Integer> expense, List<Integer> res, int total, int inputs, int index){
        if(expense.size() == 0 || expense == null)
            return false;

        if(index >= expense.size() || (inputs == 0 && total != 0))
            return false;

        if(total == 0 && inputs == 0)
            return true;

        for(int i = index; i < expense.size(); i++){
            res.add(expense.get(i));

            if(find2020Sum(expense, res, total - expense.get(i), inputs-1, i+1)) {
                return true;
            }
            res.remove(res.size() - 1);
        }

        return false;
    }
}
