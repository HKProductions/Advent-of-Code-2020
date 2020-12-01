package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    private List<Integer> expense;

    Day1(String file){
        expense = new ArrayList<>();

        try{
            File dataImport = new File(file);
            Scanner dataReader = new Scanner(dataImport);

            while(dataReader.hasNext()){
                String line = dataReader.nextLine();
                expense.add(Integer.parseInt(line));
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public List<Integer> sum2020Multiple(int total, int input){
        List<Integer> sum2020 = new ArrayList<>();

        find2020Sum(sum2020, total, input, 0);

        System.out.print("Multiple of 2020 - Day 1 Part 1: ");
        int sum = 1;
        for(int i = 0; i < sum2020.size(); i++){
            sum *= sum2020.get(i);
        }
        System.out.println(sum);

        return sum2020;
    }

    private boolean find2020Sum(List<Integer> res, int total, int inputs, int index){
        if(expense.size() == 0 || expense == null)
            return false;

        if(index >= expense.size() || total < 0 || (inputs == 0 && total != 0))
            return false;

        if(total == 0 && inputs == 0)
            return true;

        for(int i = index; i < expense.size(); i++){
            res.add(expense.get(i));

            if(find2020Sum(res, total - expense.get(i), inputs-1, i+1)) {
                return true;
            }
            res.remove(res.size() - 1);
        }

        return false;
    }
}
