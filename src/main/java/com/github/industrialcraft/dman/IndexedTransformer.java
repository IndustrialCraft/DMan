package com.github.industrialcraft.dman;

import io.vavr.Function2;
import io.vavr.collection.Vector;
import io.vavr.control.Option;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;

public class IndexedTransformer {
    public static <T,U> Vector<U> mapIndexed(Vector<T> toMap, Function2<T,Index<T>,U> mapper){
        AtomicInteger id = new AtomicInteger(0);
        return toMap.map(t -> mapper.apply(t, new Index<>(toMap, id.getAndIncrement())));
    }
    public static <T> Vector<T> filterIndexed(Vector<T> toMap, BiPredicate<T,Index<T>> filter){
        AtomicInteger id = new AtomicInteger(0);
        return toMap.filter(t -> filter.test(t, new Index<>(toMap, id.getAndIncrement())));
    }
    public static class Index<T>{
        private final Vector<T> parent;
        private final int index;
        public Index(Vector<T> parent, int index) {
            this.parent = parent;
            this.index = index;
        }
        public Option<T> getAt(int index){
            return this.parent.getOption(index);
        }
        public T getAtOrThrow(int index){
            return this.parent.get(index);
        }
        public Option<T> offset(int offset){
            return this.parent.getOption(this.index+offset);
        }
        public T offsetOrThrow(int offset){
            return this.parent.get(this.index+offset);
        }
        public int get(){
            return this.index;
        }
    }
}
