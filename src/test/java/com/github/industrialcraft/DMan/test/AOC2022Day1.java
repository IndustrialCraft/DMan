package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AOC2022Day1 {
    public static void main(String[] args){
        part1();
    }
    public static void part1(){
        var lines = FileUtils.readLines("inputs/input1.txt").toJavaArray(String[]::new);
        int currentCounter = 0;
        List<Integer> callories = new ArrayList<>();
        for(int i = 0;i < lines.length;i++){
            try{
                int parsed = Integer.parseInt(lines[i]);
                currentCounter += parsed;
            } catch (Exception e){
                callories.add(currentCounter);
                currentCounter = 0;
            }
        }
        callories.add(currentCounter);
        callories.sort(Integer::compareTo);
        Collections.reverse(callories);
        System.out.println(callories.get(0)+callories.get(1)+callories.get(2));
    }
}
