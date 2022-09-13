package com.github.industrialcraft.dman;

import io.vavr.collection.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grid {
    public static <T> Vector<Vector<T>> rotateRight(Vector<Vector<T>> input){
        Vector<List<T>> output = initialize2D(input.get(0).length(), input.length(), null).map(objects -> (List<T>)objects.toJavaList());
        for(int x = 0;x < input.length();x++){
            for(int y = 0;y < input.get(x).length();y++){
                output.get(y).set(input.length()-x-1, input.get(x).get(y));
            }
        }
        return output.map(ts -> Vector.ofAll(ts));
    }
    public static <T> Vector<Vector<T>> rotateLeft(Vector<Vector<T>> input){
        Vector<List<T>> output = initialize2D(input.get(0).length(), input.length(), null).map(objects -> (List<T>)objects.toJavaList());
        for(int x = 0;x < input.length();x++){
            for(int y = 0;y < input.get(x).length();y++){
                output.get(input.get(x).length()-y-1).set(x, input.get(x).get(y));
            }
        }
        return output.map(ts -> Vector.ofAll(ts));
    }
    public static <T> Vector<Vector<T>> initialize2D(int w, int h, T value){
        ArrayList<ArrayList<T>> list = new ArrayList(w);
        for(int x = 0;x < w;x++){
            ArrayList<T> list2 = new ArrayList(h);
            for(int y = 0;y < h;y++){
                list2.add(value);
            }
            list.add(list2);
        }
        return Vector.ofAll(list).map(ts -> Vector.ofAll(ts));
    }
}
