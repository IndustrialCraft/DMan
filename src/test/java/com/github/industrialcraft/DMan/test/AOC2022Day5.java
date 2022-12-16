package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.CharBitMap;
import com.github.industrialcraft.dman.FileUtils;
import com.github.industrialcraft.dman.Regex;
import io.vavr.collection.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class AOC2022Day5 {
    public static void main(String[] args){
        int riadky = 9;
        int stlpce = 8;
        List<List<Character>> crates = new ArrayList<>();
        Vector<String> lines = FileUtils.readLines("inputs/input5.txt");

        for(int i = 0;i < riadky;i++)
            crates.add(new ArrayList<>());
        for(int i = 0;i < stlpce;i++){
            String line = lines.get(i);
            int cnt = 0;
            for(int j = 1;j < line.length();j = j+4){
                if(line.charAt(j) != ' ')
                    crates.get(cnt).add(line.charAt(j));
                cnt++;
            }
        }

        for(int i = 0;i < riadky;i++){
            Collections.reverse(crates.get(i));
        }

        for(int i = riadky+1;i < lines.length();i++){
            Vector<Integer> ints = Regex.of("move (\\d+) from (\\d+) to (\\d+)").parse(lines.get(i)).map(Integer::parseInt);
            System.out.println("starting " + i);
            ArrayList<Character> tmp = new ArrayList<>();
            int to = ints.get(2)-1;
            for(int j = 0;j < ints.get(0);j++){
                int from = ints.get(1)-1;

                tmp.add(crates.get(from).get(crates.get(from).size() - 1));
                crates.get(from).remove(crates.get(from).size() - 1);
            }
            Collections.reverse(tmp);
            for(Character ch : tmp){
                crates.get(to).add(ch);
            }
        }
        System.out.println();
        for(int i = 0;i < riadky;i++)
            System.out.print(crates.get(i).get(crates.get(i).size()-1));
        System.out.println();
            /*if(isInstructions.get()){

            } else {
                if(s.isBlank())
                    isInstructions.set(true);
                else {
                    ArrayList<Character> part = new ArrayList<>();
                    for(int i = 1;i < s.length();i = i+4){
                        char ch = s.charAt(i);
                        part.add(ch);
                    }
                    crates.add(part);
                }
            }*/
    }
}
