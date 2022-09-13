package com.github.industrialcraft.dman;

public class Printer {
    public static void print(Object input, Object... data){
        System.out.print(String.format(input.toString(), data));
    }
    public static void println(Object input, Object... data){
        System.out.println(String.format(input.toString(), data));
    }
}
