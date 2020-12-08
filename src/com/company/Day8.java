package com.company;

import java.io.File;
import java.util.*;

public class Day8 {
    class InstructionCount{
        int steps;
        String type;

        InstructionCount(String type, String steps){
            this.type = type;
            this.steps = Integer.parseInt(steps);
        }
    }

    ArrayList<InstructionCount> instructions;

    Day8(String fileName){
        instructions = new ArrayList<>();

        try{
            File dataReader = new File(fileName);
            Scanner fileReader = new Scanner(dataReader);

            while(fileReader.hasNext()) {
                String[] line = fileReader.nextLine().split("[+ \\s++]");
                instructions.add(new InstructionCount(line[0], line[line.length - 1]));
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public int findLoopPart1(){
        int accumulator = 0;
        Set<InstructionCount> visited = new HashSet<>();

        for(int currIndex = 0; currIndex < instructions.size(); currIndex++){
            InstructionCount currInstruction = instructions.get(currIndex);

            if(visited.contains(currInstruction))
                break;

            if(currInstruction.type.equals("acc"))
                accumulator += currInstruction.steps;

            if(currInstruction.type.equals("jmp"))
                currIndex += (currInstruction.steps - 1);

            visited.add(currInstruction);
        }


        return accumulator;
    }

    public int fixLoopPart2(){
        for(int currIndex = 0; currIndex < instructions.size(); currIndex++){
            InstructionCount currInstruction = instructions.get(currIndex);

            if(currInstruction.type.equals("jmp") || currInstruction.type.equals("nop")) {
                if (currInstruction.type.equals("jmp"))
                    currInstruction.type = "nop";
                else if (currInstruction.type.equals("nop"))
                    currInstruction.type = "jmp";

                if (canReachEnd())
                    return findLoopPart1();

                if (currInstruction.type.equals("jmp"))
                    currInstruction.type = "nop";
                else if (currInstruction.type.equals("nop"))
                    currInstruction.type = "jmp";
            }
        }

       return 0;
    }

    private boolean canReachEnd(){
        Set<InstructionCount> visited = new HashSet<>();

        for(int currIndex = 0; currIndex < instructions.size(); currIndex++){
            InstructionCount currInstruction = instructions.get(currIndex);

            if(visited.contains(currInstruction))
                return false;

            if(currInstruction.type.equals("jmp"))
                currIndex += (currInstruction.steps - 1);

            visited.add(currInstruction);
        }

        return true;
    }
}
