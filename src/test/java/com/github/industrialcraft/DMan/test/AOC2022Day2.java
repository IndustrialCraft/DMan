package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.FileUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class AOC2022Day2 {
    public enum GameType{
        WON,
        LOST,
        DRAW;
        public static GameType fromChar(char ch){
            if(ch == 'A' || ch == 'X')
                return LOST;
            if(ch == 'B' || ch == 'Y')
                return DRAW;
            if(ch == 'C' || ch == 'Z')
                return WON;
            throw new IllegalStateException();
        }
    }
    public enum Type{
        ROCK(1),
        PAPER(2),
        SCISSORS(3);
        public final int points;
        Type(int points) {
            this.points = points;
        }
        public static Type fromChar(char ch){
            if(ch == 'A' || ch == 'X')
                return ROCK;
            if(ch == 'B' || ch == 'Y')
                return PAPER;
            if(ch == 'C' || ch == 'Z')
                return SCISSORS;
            throw new IllegalStateException();
        }
        public int test(Type other){
            if(this == ROCK){
                if(other == ROCK)
                    return 3;
                if(other == PAPER)
                    return 0;
                if(other == SCISSORS)
                    return 6;
            }
            if(this == PAPER){
                if(other == ROCK)
                    return 6;
                if(other == PAPER)
                    return 3;
                if(other == SCISSORS)
                    return 0;
            }
            if(this == SCISSORS){
                if(other == ROCK)
                    return 0;
                if(other == PAPER)
                    return 6;
                if(other == SCISSORS)
                    return 3;
            }
            throw new IllegalStateException();
        }
        public Type getNeeded(GameType other){
            if(this == ROCK){
                if(other == GameType.WON)
                    return PAPER;
                if(other == GameType.LOST)
                    return SCISSORS;
                if(other == GameType.DRAW)
                    return ROCK;
            }
            if(this == PAPER){
                if(other == GameType.WON)
                    return SCISSORS;
                if(other == GameType.LOST)
                    return ROCK;
                if(other == GameType.DRAW)
                    return PAPER;
            }
            if(this == SCISSORS){
                if(other == GameType.WON)
                    return ROCK;
                if(other == GameType.LOST)
                    return PAPER;
                if(other == GameType.DRAW)
                    return SCISSORS;
            }
            throw new IllegalStateException();
        }
    }
    public static void main(String[] args){
        AtomicInteger totalPoints = new AtomicInteger();
        FileUtils.readLines("inputs/input2.txt").forEach(s -> {
            String[] split = s.split(" ");
            Type t1 = Type.fromChar(split[0].charAt(0));
            GameType t2 = GameType.fromChar(split[1].charAt(0));
            Type t3 = t1.getNeeded(t2);
            totalPoints.addAndGet(t3.test(t1) + t3.points);
        });
        System.out.println(totalPoints.get());
    }
}
