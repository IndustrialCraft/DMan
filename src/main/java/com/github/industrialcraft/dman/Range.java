package com.github.industrialcraft.dman;

public class Range {
    public final int start;
    public final int end;
    private Range(int start, int end) {
        this.start = start;
        this.end = end;
    }
    public static Range of(int start, int end){
        if(start > end)
            throw new IllegalArgumentException("start(" + start + ") greater than end(" + end + ")");
        return new Range(start, end);
    }
    public boolean within(int number){
        return number >= start && number <= end;
    }
    public boolean below(int number){
        return number < start;
    }
    public boolean above(int number){
        return number > end;
    }
}
