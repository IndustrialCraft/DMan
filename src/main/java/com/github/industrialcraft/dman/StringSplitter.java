package com.github.industrialcraft.dman;

import io.vavr.collection.Vector;

public class StringSplitter {
    public static Vector<String> splitString(String input, Regex delimiter1){
        return delimiter1.split(input);
    }
    public static Vector<Vector<String>> splitString(String input, Regex delimiter1, Regex delimiter2){
        return delimiter1.split(input).map(s -> delimiter2.split(s));
    }
    public static Vector<Vector<Vector<String>>> splitString(String input, Regex delimiter1, Regex delimiter2, Regex delimiter3){
        return delimiter1.split(input).map(s2 -> delimiter2.split(s2).map(s3 -> delimiter3.split(s3)));
    }
    public static Vector<Character> splitToChars(String input){
        return Vector.ofAll(input.toCharArray());
    }
}
