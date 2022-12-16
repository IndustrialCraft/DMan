package com.github.industrialcraft.dman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CharBitMap {
    long bitmap;
    public CharBitMap(String str, String alphabet) {
        this.bitmap = 0;
        for(char ch : str.toCharArray()){
            int indexOf = alphabet.indexOf(ch);
            if(indexOf == -1)
                throw new IllegalArgumentException();
            bitmap |= 1L << indexOf;
        }
    }
    public CharBitMap(long bitmap) {
        this.bitmap = bitmap;
    }
    public CharBitMap intersect(CharBitMap other){
        return new CharBitMap(bitmap&other.bitmap);
    }
    public long getValue() {
        return bitmap;
    }
    public boolean isEmpty(){
        return bitmap == 0;
    }
    public Iterator<Integer> getChars(){
        if(isEmpty())
            return Collections.emptyIterator();
        List<Integer> indexes = new ArrayList<>();
        for(int i = 0;i < Long.SIZE;i++){
            long index = (1L <<(Long.SIZE-i-1));
            if((bitmap&index) != 0){
                indexes.add((Long.SIZE-i-1));
            }
        }
        return indexes.listIterator();
    }
}
