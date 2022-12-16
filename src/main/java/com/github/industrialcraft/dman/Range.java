package com.github.industrialcraft.dman;

import java.util.Iterator;

public class Range implements Iterable<Integer>{
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
    public static Range containing(int num){
        if(num < 0)
            throw new IllegalArgumentException("range cannot contain less than 0 numbers");
        return new Range(0, num-1);
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
    public boolean within(Range other){
        return (start >= other.start && end <= other.end) || (other.start >= start && other.end <= end);
    }
    public boolean overlaps(Range other){
        return overlap(this, other) || overlap(other, this);
    }
    private static boolean overlap(Range r1, Range r2){
        return (r1.start <= r2.end && r1.end >= r2.start);
    }

    @Override
    public Iterator iterator() {
        return new RangeIterator();
    }
    public class RangeIterator implements Iterator<Integer>{
        public int num;
        public RangeIterator(){
            this.num = start;
        }
        @Override
        public boolean hasNext() {
            return num <= end;
        }

        @Override
        public Integer next() {
            return num++;
        }
    }
}
