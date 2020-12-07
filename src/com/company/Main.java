package com.company;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Day1 d1 = new Day1("/Advent-of-Code-2020/data/day1Data.txt"); //<- Day 1
        d1.sum2020Multiple(2020, 2);
        d1.sum2020Multiple(2020, 3);

        System.out.println("\n");
//----------------------------------------------------------------------------------------------------------------------
        Day2 d2 = new Day2("/Advent-of-Code-2020/data/day2Data.txt"); //<- Day 2
        d2.validPasswordsPart1();
        d2.validPasswordsPart2();

        System.out.println("\n");
//----------------------------------------------------------------------------------------------------------------------
        Day3 d3 = new Day3("/Advent-of-Code-2020/data/day3Data.txt"); //<- Day 3
        long s1 = d3.treeHitsPart1(1, 1);
        long s2 = d3.treeHitsPart1(3, 1);
        long s3 = d3.treeHitsPart1(5, 1);
        long s4 = d3.treeHitsPart1(7,1);
        long s5 = d3.treeHitsPart1(1, 2);
        long treeHitMultiple = s1 * s2 * s3 * s4 * s5;
        System.out.println("Number of Tree Hits - Day 3 Part 2: " + treeHitMultiple);

        System.out.println("\n");
//----------------------------------------------------------------------------------------------------------------------
        Day4 d4 = new Day4("/Advent-of-Code-2020/data/day4Data.txt"); //<- Day 4
        d4.validPassportCountPart2("cid");

        System.out.println("\n");
//----------------------------------------------------------------------------------------------------------------------
        Day5 d5 = new Day5("/Advent-of-Code-2020/data/day5Data.txt"); //<- Day 5
        d5.findSeat();

        System.out.println("\n");
//----------------------------------------------------------------------------------------------------------------------
        Day6 d6 = new Day6("/Advent-of-Code-2020/data/day6Data.txt"); //<- Day 6
        d6.countAnswersPart1();
        d6.countAnswersPart2();

        System.out.println("\n");
//----------------------------------------------------------------------------------------------------------------------
        Day7 d7 = new Day7("/Advent-of-Code-2020/data/day7Data.txt"); //<- Day 7
        d7.bagCountPart1("shiny gold");
        System.out.print("Number of bags to bring - Day 7 Part 2: " + (d7.bagCountPart2("shiny gold", new HashMap<>()) - 1));

        System.out.println("\n");
//----------------------------------------------------------------------------------------------------------------------
    }
}
