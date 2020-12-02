package com.company;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Day1 d1 = new Day1("/Advent-of-Code-2020/data/day1Part1.txt"); //<- Day 1
        d1.sum2020Multiple(2020, 2);
        d1.sum2020Multiple(2020, 3);

        System.out.println("\n");

        Day2 d2 = new Day2("/Advent-of-Code-2020/data/day2Part1.txt"); //<- Day 2
        d2.validPasswordsPart1();
        d2.validPasswordsPart2();

        System.out.println("\n");
    }
}
