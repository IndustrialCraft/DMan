package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.FileUtils;
import com.github.industrialcraft.dman.Range;
import com.github.industrialcraft.dman.StringSplitter;
import io.vavr.collection.HashSet;
import io.vavr.collection.Vector;

import java.util.ArrayList;

public class AOC2022Day9 {
    public static void main(String[] args) {
        Vector<String> input = FileUtils.readLines("inputs/input9.txt");
        HashSet<Position> positions = HashSet.empty();
        Position head = new Position(0, 0);
        ArrayList<Position> tails = new ArrayList<>();
        for(int i : Range.containing(10)){
            tails.add(new Position(0, 0));
        }
        for(String s : input){
            String[] split = s.split(" ");
            char ch = Character.toLowerCase(split[0].charAt(0));
            int times = Integer.parseInt(split[1]);
            for(int i = 0;i < times;i++) {
                switch (ch) {
                    case 'r' -> {
                        head = head.add(1, 0);
                    }
                    case 'l' -> {
                        head = head.add(-1, 0);
                    }
                    case 'u' -> {
                        head = head.add(0, -1);
                    }
                    case 'd' -> {
                        head = head.add(0, 1);
                    }
                }
                tails.set(0, head.calculateSnapping(tails.get(0)));
                for(int j = 1;j < tails.size();j++){
                    if(tails.get(j-1).distance(tails.get(j)) > 1.5) {
                        tails.set(j, tails.get(j - 1).calculateSnapping(tails.get(j)));
                    }
                }
                positions = positions.add(tails.get(tails.size()-1));
            }
        }
        System.out.println(positions.size());
    }
    public record Position(int x, int y){
        public Position add(int x, int y){
            return new Position(this.x+x, this.y+y);
        }
        public double distance(Position other){
            return Math.sqrt(Math.pow(x-other.x, 2) + Math.pow(y-other.y, 2));
        }
        public Position calculateSnapping(Position tail){
            int xOff = 0;
            int yOff = 0;
            if(this.x < tail.x)
                xOff--;
            if(this.x > tail.x)
                xOff++;
            if(this.y < tail.y)
                yOff--;
            if(this.y > tail.y)
                yOff++;
            return tail.add(xOff, yOff);
        }
    }
}