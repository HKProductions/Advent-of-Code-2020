package com.company;

import java.io.File;
import java.util.*;

public class Day7 {
    class BagConnection{
        int count;
        String bagType;

        BagConnection(String bagType, String count){
            this.bagType = bagType;
            this.count = Integer.parseInt(count);
        }
    }

    private Map<String, List<String>> bagMapping;
    private Map<String, List<BagConnection>> bagMapping2;

    Day7(String fileName){
        bagMapping = new HashMap<>();
        bagMapping2 = new HashMap<>();

        try{
            File dataReader = new File(fileName);
            Scanner fileReader = new Scanner(dataReader);

            while(fileReader.hasNext()){
                String[] line = fileReader.nextLine().split(" contain ");
                String[] values = line[1].split("[,.]");

                for(String value : values){
                    if(!value.equals("no other bags")) {
                        String valueSubstring = value.substring(2, value.indexOf("bag")-1).trim();
                        List<String> bags = bagMapping.getOrDefault(valueSubstring, new ArrayList<>());

                        bags.add(line[0].substring(0, line[0].indexOf("bag")-1));
                        bagMapping.put(valueSubstring, bags);
                    }
                }

                for(String value: values){
                    if(!value.equals("no other bags")){
                        String valueSubstring = value.substring(2, value.indexOf("bag")-1).trim();
                        List<BagConnection> bags = bagMapping2.getOrDefault(line[0].substring(0, line[0].indexOf("bag")-1), new ArrayList<>());

                        BagConnection bc = new BagConnection(valueSubstring, value.trim().substring(0,1));
                        bags.add(bc);
                        bagMapping2.put(line[0].substring(0, line[0].indexOf("bag")-1), bags);
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public int bagCountPart1(String bag){
        Queue<String> container = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int count = 0;

        container.add(bag);
        while(!container.isEmpty()){
            String currBag = container.poll();
            List<String> bags = bagMapping.get(currBag);

            if(!currBag.equals(bag) && !visited.contains(currBag))
                count++;

            visited.add(currBag);

            if(bags == null)
                continue;

            for(String b : bags) {
                if(!visited.contains(b))
                    container.offer(b);
            }
        }

        System.out.println("Number of external bags for " + bag + " - Day 7 Part 1: " + count);
        return count;
    }

    public int bagCountPart2(String bag, Map<String, Integer> memo){
        int count = 1;
        List<BagConnection> bags = bagMapping2.get(bag);

        if(memo.containsKey(bag))
            return memo.get(bag);

        if(bags == null)
            return 1;

        for(BagConnection bc : bags){
            count += (bc.count * bagCountPart2(bc.bagType, memo));
        }

        memo.put(bag, count);
        return count;
    }
}
