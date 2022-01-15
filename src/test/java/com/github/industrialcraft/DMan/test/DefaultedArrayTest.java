package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.CombineMode;
import com.github.industrialcraft.dman.DefaultedArray;

import java.util.Arrays;

public class DefaultedArrayTest {
    public static void main(String args[]){
        DefaultedArray<Integer> array = new DefaultedArray(0, Arrays.asList(1, 2, 3));
        array.combinations((first, second) -> System.out.println(first + ":" + second), CombineMode.NOREPEAT_DIFFERENT);
    }
}
