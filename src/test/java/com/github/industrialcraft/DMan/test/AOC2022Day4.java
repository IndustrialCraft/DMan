package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.FileUtils;
import com.github.industrialcraft.dman.Range;
import com.github.industrialcraft.dman.Regex;
import io.vavr.collection.Vector;

import java.util.concurrent.atomic.AtomicInteger;

public class AOC2022Day4 {
    public static void main(String[] args){
        AtomicInteger count = new AtomicInteger();
        FileUtils.readLines("inputs/input4.txt").forEach(s -> {
            Vector<Integer> ints = Regex.of("(\\d+)-(\\d+),(\\d+)-(\\d+)").parse(s).map(Integer::parseInt);
            Range r1 = Range.of(ints.get(0), ints.get(1));
            Range r2 = Range.of(ints.get(2), ints.get(3));
            if(r1.overlaps(r2))
                count.getAndIncrement();
        });
        System.out.println(count.get());
    }
}
