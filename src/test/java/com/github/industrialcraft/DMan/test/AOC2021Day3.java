package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class AOC2021Day3 {
    public static void main(String args[]){
        String input = "00100\n11110\n10110\n10111\n10101\n01111\n00111\n11100\n10000\n11001\n00010\n01010";
        String[] parsed = Splitter.splitByDelimeter(input, Pattern.quote("\n"), arg -> arg);

        AtomicReference<String> num1 = new AtomicReference<>("");
        Loop.times(5, i -> {
            String dig = ArrayUtils.combine(parsed, arg -> ""+arg.charAt(i));
            Quantities quantities = new Quantities(dig);
            num1.updateAndGet(v -> v + quantities.mostCommon());
        });

        AtomicReference<String> num2 = new AtomicReference<>("");
        Loop.times(5, i -> {
            String dig = ArrayUtils.combine(parsed, arg -> ""+arg.charAt(i));
            Quantities quantities = new Quantities(dig);
            num2.updateAndGet(v -> v + quantities.leastCommon());
        });

        System.out.println(BaseConvert.convertToBase10(num1.get(), 2) * BaseConvert.convertToBase10(num2.get(), 2));

    }
}
