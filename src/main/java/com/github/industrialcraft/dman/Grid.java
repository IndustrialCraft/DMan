package com.github.industrialcraft.dman;

import com.github.industrialcraft.dman.lambda.grid.LambdaElementPos;
import com.github.industrialcraft.dman.lambda.grid.LambdaElementPosRet;
import com.github.industrialcraft.dman.lambda.grid.LambdaElementRet;
import com.github.industrialcraft.dman.lambda.grid.LambdaPosRet;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;

public class Grid<T> {
    private final T grid[][];
    public Grid(int width, int height, Class clazz) {
        if(width <= 0)
            throw new IllegalArgumentException("width must be greater than zero, was: " + width);
        if(height <= 0)
            throw new IllegalArgumentException("height must be greater than zero, was: " + height);
        this.grid = (T[][]) Array.newInstance(clazz, width, height);
        forEachSet((first, second, third) -> null);
    }
    public Grid(int width, int height, LambdaPosRet<T> supplier) {
        if(width <= 0)
            throw new IllegalArgumentException("width must be greater than zero, was: " + width);
        if(height <= 0)
            throw new IllegalArgumentException("height must be greater than zero, was: " + height);

        this.grid = (T[][]) Array.newInstance(supplier.process(0, 0).getClass(), width, height);
        forEachSet(supplier);
    }
    public Grid(int width, int height, T values[]) {
        if(width <= 0)
            throw new IllegalArgumentException("width must be greater than zero, was: " + width);
        if(height <= 0)
            throw new IllegalArgumentException("height must be greater than zero, was: " + height);

        if(values.length != width*height)
            throw new IllegalArgumentException("values array length mismatch, expected " + width*height + " got " + values.length);

        this.grid = (T[][]) Array.newInstance(values[0].getClass(), width, height);

        forEachSet((first, second, third) -> (T) ((Object[])values)[(second*width)+third]);

    }

    public void forEach(LambdaElementPos<T> lambda){
        for(int x = 0;x < grid.length;x++){
            for(int y = 0;y < grid[x].length;y++){
                lambda.process((T) grid[x][y],x,y);
            }
        }
    }
    public void forEachSet(LambdaElementPosRet<T> lambda){
        for(int x = 0;x < grid.length;x++){
            for(int y = 0;y < grid[x].length;y++){
                grid[x][y] = lambda.process((T) grid[x][y],x,y);
            }
        }
    }
    public void forEachSet(LambdaElementRet<T> lambda){
        for(int x = 0;x < grid.length;x++){
            for(int y = 0;y < grid[x].length;y++){
                grid[x][y] = lambda.process((T) grid[x][y]);
            }
        }
    }
    public void forEachSet(LambdaPosRet<T> lambda){
        for(int x = 0;x < grid.length;x++){
            for(int y = 0;y < grid[x].length;y++){
                grid[x][y] = lambda.process(x,y);
            }
        }
    }
    public boolean forEachNeighborSet(int x, int y, LambdaElementPosRet<T> lambda, NeighborMode mode){
        if(x < 0 || x >= getWidth())
            return false;
        if(y < 0 || y >= getHeight())
            return false;

        if(mode == NeighborMode.ALL || mode == NeighborMode.NEXT){
            tryProcessSet(x-1, y, lambda);
            tryProcessSet(x+1, y, lambda);
            tryProcessSet(x, y-1, lambda);
            tryProcessSet(x, y+1, lambda);
        }
        if(mode == NeighborMode.ALL || mode == NeighborMode.DIAGONAL){
            tryProcessSet(x-1, y-1, lambda);
            tryProcessSet(x-1, y+1, lambda);
            tryProcessSet(x+1, y-1, lambda);
            tryProcessSet(x+1, y+1, lambda);
        }
        return true;
    }
    public boolean forEachNeighbor(int x, int y, LambdaElementPos<T> lambda, NeighborMode mode){
        if(x < 0 || x >= getWidth())
            return false;
        if(y < 0 || y >= getHeight())
            return false;

        if(mode == NeighborMode.ALL || mode == NeighborMode.NEXT){
            tryProcess(x-1, y, lambda);
            tryProcess(x+1, y, lambda);
            tryProcess(x, y-1, lambda);
            tryProcess(x, y+1, lambda);
        }
        if(mode == NeighborMode.ALL || mode == NeighborMode.DIAGONAL){
            tryProcess(x-1, y-1, lambda);
            tryProcess(x-1, y+1, lambda);
            tryProcess(x+1, y-1, lambda);
            tryProcess(x+1, y+1, lambda);
        }
        return true;
    }
    public int getWidth() {
        return grid.length;
    }
    public int getHeight() {
        return grid[0].length;
    }
    public boolean tryProcessSet(int x, int y, LambdaElementPosRet<T> lambda){
        if(x < 0 || x >= getWidth())
            return false;
        if(y < 0 || y >= getHeight())
            return false;
        grid[x][y] = lambda.process(grid[x][y], x, y);
        return true;
    }
    public boolean tryProcess(int x, int y, LambdaElementPos<T> lambda){
        if(x < 0 || x >= getWidth())
            return false;
        if(y < 0 || y >= getHeight())
            return false;
        lambda.process(grid[x][y], x, y);
        return true;
    }
    public void setE(int x, int y, T value){
        grid[x][y] = value;
    }
    public boolean set(int x, int y, T value){
        try {
            grid[x][y] = value;
        } catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
        return true;
    }
    public T getE(int x, int y, T value){
        return (T) grid[x][y];
    }
    public T get(int x, int y){
        try {
            return (T) grid[x][y];
        } catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
    public Object[][] getGrid() {
        return grid;
    }

    public boolean isSquare(Grid grid){
        return ArrayUtils.isSquare(getGrid());
    }
    public boolean isSquare(Grid grid, int size){
        return ArrayUtils.isSquare(getGrid(), size);
    }
    public boolean isRect(Grid grid){
        return ArrayUtils.isRect(getGrid());
    }
    public boolean isRect(Grid grid, int width, int height){
        return ArrayUtils.isRect(getGrid(), width, height);
    }

    @Override
    public String toString() {
        String out = "";
        for(int x = 0;x < getWidth();x++){
            for(int y = 0;y < getHeight();y++){
                out += grid[x][y];
            }
            out += "\n";
        }
        return out;
    }
}
