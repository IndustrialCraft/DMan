package com.github.industrialcraft.dman;

import java.util.*;
import java.util.stream.Collectors;

public class CharCounter {
    private final HashMap<Character,Integer> counts;
    private final List<Character> sorted;
    private final int total;
    private CharCounter(String input) {
        this.counts = new HashMap<>();
        this.total = input.length();
        for(char ch : input.toCharArray()){
            counts.put(ch, counts.getOrDefault(ch, 0)+1);
        }
        this.sorted = counts.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue)).map(characterIntegerEntry -> characterIntegerEntry.getKey()).collect(Collectors.toUnmodifiableList());
    }
    public int count(char ch){
        return this.counts.getOrDefault(ch, 0);
    }
    public float ratio(char ch){
        return ((float)this.counts.getOrDefault(ch, 0))/((float)total);
    }
    public Optional<Character> least(){
        return sorted.stream().findFirst();
    }
    public char most(){
        return sorted.get(sorted.size()-1);
    }


    public static CharCounter count(String input, boolean ignoreCase){
        return new CharCounter(ignoreCase?input.toLowerCase():input);
    }
}
