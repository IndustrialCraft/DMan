package com.github.industrialcraft.dman;


import io.vavr.collection.Vector;

import java.util.stream.Collectors;

public class VectorUtils {
    public static <T> String join(Vector<T> vector, String joiner){
        return vector.toJavaStream().map(t -> t.toString()).collect(Collectors.joining(joiner));
    }
    public static <T> String join(Vector<T> vector){
        return vector.toJavaStream().map(t -> t.toString()).collect(Collectors.joining());
    }
}
