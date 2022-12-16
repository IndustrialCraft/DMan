package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.FileUtils;
import io.vavr.collection.Vector;

public class AOC2022Day10 {
    public static void main(String[] args){
        Vector<String> input = FileUtils.readLines("inputs/input10.txt");
        int num = 1;
        int clock = -1;
        for(String s : input){
            String[] split = s.split(" ");
            String op = split[0];
            int opArg = Integer.MAX_VALUE;
            if(split.length > 1)
                opArg = Integer.parseInt(split[1]);
            switch (op) {
                case "addx" -> {
                    clock++;
                    System.out.print(Math.abs((clock%40)-num)<=1?'#':'.');
                    if(clock%40==39) {
                        System.out.println();
                    }
                    clock++;
                    System.out.print(Math.abs((clock%40)-num)<=1?'#':'.');
                    if(clock%40==39) {
                        System.out.println();
                    }
                    num += opArg;
                }
                case "noop" -> {
                    clock++;
                    System.out.print(Math.abs((clock%40)-num)<=1?'#':'.');
                    if(clock%40==39) {
                        System.out.println();
                    }
                }
            }
        }
    }

    public static boolean isShownCycle(int cycle){
        if(cycle < 20)
            return false;
        return (cycle-20)%40==0;
    }
}
