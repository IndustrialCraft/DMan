package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.CharCounter;
import com.github.industrialcraft.dman.FileUtils;
import io.vavr.collection.Vector;

public class AOC2022Day6 {
    public static void main(String[] args){
        String line = FileUtils.readLines("inputs/input6.txt").get(0);
        for(int i = 15;i < line.length();i++){
            CharCounter chc = CharCounter.count(line.substring(i-14, i), false);
            if(chc.count(chc.most()) <= 1) {
                System.out.println(i);
                return;
            }
        }
    }
}
