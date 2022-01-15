package com.github.industrialcraft.dman;

import com.github.industrialcraft.dman.lambda.LambdaPair;
import com.github.industrialcraft.dman.lambda.LambdaRet;

import java.util.ArrayList;
import java.util.Collection;

public class DefaultedArray<T> extends ArrayList<T> {
    T defaultValue;
    public DefaultedArray(T defaultValue, int initialCapacity) {
        super(initialCapacity);
        this.defaultValue = defaultValue;
    }

    public DefaultedArray(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public DefaultedArray(T defaultValue, Collection<? extends T> c) {
        super(c);
        this.defaultValue = defaultValue;
    }

    @Override
    public T get(int index) {
        try {
            return super.get(index);
        } catch (ArrayIndexOutOfBoundsException e){
            return this.defaultValue;
        }
    }
    public void forEachSet(LambdaRet<T,T> lambda){
        for(int i = 0;i < size();i++){
            set(i, lambda.process(get(i)));
        }
    }
    public void combinations(LambdaPair<T,T> lambda, CombineMode mode){
        if(mode == CombineMode.ALL){
            for(int i = 0;i < size();i++){
                for(int j = 0;j < size();j++){
                    lambda.process(get(i), get(j));
                }
            }
            return;
        }
        if(mode == CombineMode.DIFFERENT){
            for(int i = 0;i < size();i++){
                for(int j = 0;j < size();j++){
                    if(i != j)
                        lambda.process(get(i), get(j));
                }
            }
            return;
        }
        if(mode == CombineMode.NOREPEAT){
            for(int i = 0;i < size();i++){
                for(int j = i;j < size();j++){
                    lambda.process(get(i), get(j));
                }
            }
            return;
        }
        if(mode == CombineMode.NOREPEAT_DIFFERENT){
            for(int i = 0;i < size();i++){
                for(int j = i+1;j < size();j++){
                    lambda.process(get(i), get(j));
                }
            }
            return;
        }
    }
}
