package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.ArrayUtils;
import com.github.industrialcraft.dman.Loop;
import com.github.industrialcraft.dman.Splitter;
import com.github.industrialcraft.dman.group.Groups;
import com.github.industrialcraft.dman.group.MultiGroup;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class AOC2021Day6 {
    public static void main(String args[]){
        String input = "3,4,3,1,2";
        String[] splitted = Splitter.splitByDelimeter(input, Pattern.quote(","), arg -> arg);
        Integer[] ints = ArrayUtils.process(splitted, arg -> Integer.parseInt(arg));

        Groups groups = new Groups(new MultiGroup("g", 9, (index, count, list) -> {
            if(index == 0){
                list.add("g6", count);
                list.add("g8", count);
            } else {
                list.add("g" + (index-1), count);
            }
        }));
        for(Integer i : ints){
            groups.add("g"+i, BigInteger.ONE);
        }
        Loop.times(80, i -> groups.process());

        System.out.println(groups.countAll());

        ArrayList<String> m = Splitter.byRegex("forward 8", "([a-z]+) ([0-9]+)");
        System.out.println();
    }
}
