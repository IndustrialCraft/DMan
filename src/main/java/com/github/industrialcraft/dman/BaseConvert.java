package com.github.industrialcraft.dman;

public class BaseConvert {
    public static String convert(String input, int fromBase, int toBase) {
        return Integer.toString(Integer.parseInt(input, fromBase), toBase);
    }
    public static int convertToBase10(String input, int fromBase) {
        return Integer.parseInt(input, fromBase);
    }
}
