package com.github.industrialcraft.dman;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Quantities {
    DefaultedMap<Character,Integer> quantity;
    public Quantities(String input) {
        quantity = new DefaultedMap<>(0);
        for(int i = 0;i < input.length();i++){
            quantity.modify(input.charAt(i), arg -> arg+1);
        }
    }
    public int get(char ch){
        return quantity.get(ch);
    }
    public List<Character> sorted(){
        return quantity.entrySet().stream().sorted((o1, o2) -> Integer.compare(o1.getValue(),o2.getValue())).map(characterIntegerEntry -> characterIntegerEntry.getKey()).toList();
    }
    public char mostCommon(){
        List sort = sorted();
        return (Character) sort.get(sort.size()-1);
    }
    public char leastCommon(){
        List sort = sorted();
        return (Character) sort.get(0);
    }
}
