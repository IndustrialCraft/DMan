package com.github.industrialcraft.dman;

import com.github.industrialcraft.dman.lambda.LambdaRet;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayUtils {
    public static boolean isSquare(Object[][] grid){
        if(grid.length <= 0)
            throw new IllegalArgumentException("grid width should be greater than 0, was: " + grid.length);
        if(grid[0].length <= 0)
            throw new IllegalArgumentException("grid height should be greater than 0, was: " + grid[0].length);

        for(int i = 0;i < grid.length;i++){
            if(grid.length != grid[i].length)
                return false;
        }
        return true;
    }
    public static boolean isSquare(Object[][] grid, int size){
        if(grid.length <= 0)
            throw new IllegalArgumentException("grid width should be greater than 0, was: " + grid.length);
        if(grid[0].length <= 0)
            throw new IllegalArgumentException("grid height should be greater than 0, was: " + grid[0].length);

        if(grid.length != size)
            return false;
        for(int i = 0;i < grid.length;i++){
            if(grid.length != grid[i].length)
                return false;
        }
        return true;
    }

    public static boolean isSquare(Grid grid){
        return isSquare(grid.getGrid());
    }
    public static boolean isSquare(Grid grid, int size){
        return isSquare(grid.getGrid(), size);
    }


    public static boolean isRect(Object[][] grid){
        if(grid.length <= 0)
            throw new IllegalArgumentException("grid width should be greater than 0, was: " + grid.length);
        if(grid[0].length <= 0)
            throw new IllegalArgumentException("grid height should be greater than 0, was: " + grid[0].length);

        int height = grid[0].length;
        for(int i = 0;i < grid.length;i++){
            if(height != grid[i].length)
                return false;
        }
        return true;
    }
    public static boolean isRect(Object[][] grid, int width, int height){
        if(grid.length <= 0)
            throw new IllegalArgumentException("grid width should be greater than 0, was: " + grid.length);
        if(grid[0].length <= 0)
            throw new IllegalArgumentException("grid height should be greater than 0, was: " + grid[0].length);
        if(grid.length != width)
            return false;
        for(int i = 0;i < grid.length;i++){
            if(height != grid[i].length)
                return false;
        }
        return true;
    }

    public static boolean isRect(Grid grid){
        return isRect(grid.getGrid());
    }
    public static boolean isRect(Grid grid, int width, int height){
        return isRect(grid.getGrid(), width, height);
    }


    public static <T> T[] flatten(T[][] input){
        int size = 0;
        for(int i = 0;i < input.length;i++){
            size += input[i].length;
        }
        T[] out = (T[]) Array.newInstance(input[0][0].getClass(), size);

        int index = 0;
        for(int x = 0;x < input.length;x++){
            for(int y = 0;y < input[x].length;y++){
                out[index] = input[x][y];
                index++;
            }
        }
        return out;
    }
    public static <I,O> O[] process(I[] input, LambdaRet<O,I> lambda){
        if(input.length <= 0)
            return null;
        O[] output = (O[]) Array.newInstance(lambda.process(input[0]).getClass(), input.length);
        for(int i = 0;i < input.length;i++)
            output[i] = lambda.process(input[i]);
        return output;
    }
    public static <T> String combine(T[] input, LambdaRet<String,T> lambdaRet){
        String str = "";
        for(int i = 0;i < input.length;i++){
            str += lambdaRet.process(input[i]);
        }
        return str;
    }
}
