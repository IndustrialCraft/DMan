package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.*;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AOC2021Day11 {
    public static void main(String[] args){
        String input = "5483143223274585471152645561736141336146635738547841675246452176841721688288113448468485545283751526";
        String[] splitted = Splitter.split(input, arg -> ""+arg.charAt(0));
        Grid<Integer> grid = new Grid<>(10, 10, ArrayUtils.process(splitted, arg -> Integer.parseInt(arg)));
        AtomicInteger flashes = new AtomicInteger();
        Loop.times(100, (i) -> {
            grid.forEachSet((first) -> first+1);
            Loop.dowhile((cond) -> {
                grid.forEachSet((element, x, y) -> {
                    if(element > 9){
                        grid.forEachNeighborSet(x, y, (element1, x1, y1) -> (element1 == -1) ? -1 : element1+1, NeighborMode.ALL);
                        cond.set();
                        flashes.getAndIncrement();
                        return -1;
                    }
                    return element;
                });
            });
            grid.forEachSet((element, x, y) -> (element == -1) ? 0 : element);
        });
        System.out.println(flashes.get());
    }
}
