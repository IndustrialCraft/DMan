package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.FileUtils;
import io.vavr.collection.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AOC2022Day7 {
    public static void main(String[] args){
        Vector<String> lines = FileUtils.readLines("inputs/input7.txt");
        ArrayList<String> path = new ArrayList<>();
        HashMap<String,Integer> dirs = new HashMap<>();
        for(String line : lines){
            if(line.equals("$ cd /")){
                path = new ArrayList<>();
                continue;
            }
            if(line.equals("$ cd ..")){
                path.remove(path.size()-1);
                continue;
            }
            if(line.startsWith("$ cd ")){
                path.add(line.replaceFirst(Pattern.quote("$ cd "), ""));
                continue;
            }
            String pathString = path.stream().collect(Collectors.joining("/"));
            if(line.equals("$ ls")) {
                dirs.put(pathString, 0);
                continue;
            }
            if(line.startsWith("dir"))
                continue;
            int count = Integer.parseInt(line.split(" ")[0]);
            dirs.put(pathString, dirs.get(pathString)+count);
        }
        int least = Integer.MAX_VALUE;
        int current = dirs.values().stream().reduce(0, Integer::sum);
        System.out.println(current);
        for(String dir : dirs.keySet()){
            int cnt = 0;
            for(String dir2 : dirs.keySet()){
                if(dir2.startsWith(dir))
                    cnt += dirs.get(dir2);
            }
            if(current-cnt < 40000000 && cnt < least)
                least = cnt;
        }
        System.out.println(least);
    }
}
