package com.github.industrialcraft.dman;

import com.github.industrialcraft.dman.lambda.LambdaRet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Splitter {
    public static <T> T[] splitByDelimeter(String input, String regex, LambdaRet<T,String> lambda){
        if(input.isEmpty())
            throw new IllegalArgumentException("input cannot be empty");

        String[] splitted = input.split(regex);
        T[] output = (T[]) Array.newInstance(lambda.process(splitted[0]).getClass(), splitted.length);
        for(int i = 0;i < splitted.length;i++){
            output[i] = lambda.process(splitted[i]);
        }
        return output;
    }
    public static String[] split(String input, LambdaRet<String,String> lambda){
        String inp = input;
        ArrayList<String> out = new ArrayList<>();
        while(!inp.isEmpty()){
            String rem = lambda.process(inp);
            if(rem == null || rem.isEmpty())
                throw new IllegalArgumentException("lambda couldnt parse");
            inp = inp.replaceFirst(Pattern.quote(rem), "");
            out.add(rem);
        }
        return out.toArray(new String[0]);
    }
    public static ArrayList<String> byRegex(String input, String regex){
        Matcher matcher = Pattern.compile(regex).matcher(input);

        ArrayList<String> matches = new ArrayList<>();
        if (matcher.matches()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                matches.add(matcher.group(i));
            }
        }
        return matches;
    }
    public static ArrayList<String> byEmptyLine(String input){
        ArrayList<String> output = new ArrayList<>();
        String[] split = input.split("\n");
        String p = "";
        for(int i = 0;i < split.length;i++){
            if(split[i].isEmpty()){
                if(!p.isEmpty()) {
                    output.add(p);
                    p = "";
                }
            } else {
                p += split[i];
            }
        }
        if(!p.isEmpty())
            output.add(p);
        return output;
    }
}
