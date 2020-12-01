package com.company;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Day1 d1 = new Day1("/Users/hk/Desktop/Advent-of-Code-2020/data/day1Part1.txt"); //<- Day 1
        d1.sum2020Multiple(2020, 2);
        d1.sum2020Multiple(2020, 3);

        System.out.println("\n");
    }
}
