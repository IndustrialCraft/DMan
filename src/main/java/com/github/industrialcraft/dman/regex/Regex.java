package com.github.industrialcraft.dman.regex;

import io.vavr.collection.Vector;
import io.vavr.control.Option;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    private Pattern pattern;
    private Regex(String input) {
        this.pattern = Pattern.compile(input);
    }
    public Option<Matcher> matches(String input){
        Matcher matcher = this.pattern.matcher(input);
        if(matcher.matches())
            return Option.some(matcher);
        return Option.none();
    }
    public Matcher matcher(String input){
        Matcher matcher = this.pattern.matcher(input);
        if(matcher.matches())
            return matcher;
        return null;
    }
    public Vector<String> split(String input){
        return Vector.of(this.pattern.split(input));
    }

    private static HashMap<String,Regex> cache = new HashMap<>();
    public static Regex of(String input){
        Regex cached = cache.get(input);
        if(cached != null)
            return cached;
        cached = new Regex(input);
        cache.put(input, cached);
        return cached;
    }
    public static Regex lit(String input){
        input = Pattern.quote(input);
        Regex cached = cache.get(input);
        if(cached != null)
            return cached;
        cached = new Regex(input);
        cache.put(input, cached);
        return cached;
    }
}
