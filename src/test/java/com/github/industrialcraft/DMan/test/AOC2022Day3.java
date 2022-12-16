package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.Alphabets;
import com.github.industrialcraft.dman.CharBitMap;
import com.github.industrialcraft.dman.FileUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AOC2022Day3 {
    public static void main(String[] args){
        /*AtomicInteger count = new AtomicInteger();
        FileUtils.readLines("inputs/input3.txt").forEach(s -> {
            String first = s.substring(0, s.length()/2);
            String second = s.substring(s.length()/2);
            int index = new CharBitMap(first, Alphabets.LOWERCASE_UPPERCASE).intersect(new CharBitMap(second, Alphabets.LOWERCASE_UPPERCASE)).getFirstChar();
            count.addAndGet(index+1);
        });
        System.out.println(count.get());*/
        AtomicInteger count = new AtomicInteger();
        AtomicInteger cnt = new AtomicInteger();
        final AtomicReference<CharBitMap> cbm = new AtomicReference<>(new CharBitMap(-1));
        FileUtils.readLines("inputs/input3.txt").forEach(s -> {
            cbm.set(cbm.get().intersect(new CharBitMap(s, Alphabets.LOWERCASE_UPPERCASE)));
            cnt.getAndIncrement();
            if(cnt.get() == 3){
                count.addAndGet(cbm.get().getChars().next() + 1);
                cnt.set(0);
                cbm.set(new CharBitMap(-1));
            }
        });
        System.out.println(count.get());
    }
}
