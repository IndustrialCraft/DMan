package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.FileUtils;
import com.github.industrialcraft.dman.StringSplitter;
import io.vavr.collection.Vector;

public class AOC2022Day8 {
    public static void main(String[] args){
        Vector<Vector<Tree>> trees = FileUtils.readLines("inputs/input8.txt").map(str -> StringSplitter.splitToChars(str).map(character -> new Tree(Integer.parseInt(""+character))));
        /*for(int i = 0;i < trees.size();i++)
            markVisibleX(trees, 0, i, 1);
        for(int i = 0;i < trees.size();i++)
            markVisibleX(trees, trees.size()-1, i, -1);
        for(int i = 0;i < trees.size();i++)
            markVisibleY(trees, i, 0, 1);
        for(int i = 0;i < trees.size();i++)
            markVisibleY(trees, i, trees.size()-1, -1);
        int total = 0;
        for(int x = 0;x < trees.size();x++){
            for(int y = 0;y < trees.size();y++){
                total += trees.get(x).get(y).isVisible?1:0;
            }
        }
        System.out.println(total);*/
        int highest = 0;
        for(int x = 0;x < trees.size();x++){
            for(int y = 0;y < trees.size();y++){
                int score = getTreeCount(trees, x, y, 1, 0)*getTreeCount(trees, x, y, 0, 1)*getTreeCount(trees, x, y, -1, 0)*getTreeCount(trees, x, y, 0, -1);
                if(score > highest)
                    highest = score;
                System.out.println(x+":"+y+"->"+score);
            }
        }
        System.out.println(highest);
    }
    public static int getTreeCount(Vector<Vector<Tree>> trees, int x, int y, int xOff, int yOff){
        int height = trees.get(x).get(y).height;
        int minHeight = 0;
        x += xOff;
        y += yOff;
        int cnt = 1;
        while (!(x < 0 || y < 0 || x >= trees.size() || y >= trees.size())){
            if(trees.get(x).get(y).height <= height && trees.get(x).get(y).height >= minHeight) {
                cnt++;
                minHeight = trees.get(x).get(y).height;
            } else
                return cnt;
            x += xOff;
            y += yOff;
        }
        return cnt;
    }
    public static void markVisibleX(Vector<Vector<Tree>> trees, int x, int y, int xOff){
        int height = -1;
        while(x >= 0 && x < trees.size()){
            Tree tree = trees.get(x).get(y);
            if(tree.height > height){
                tree.isVisible = true;
                height = tree.height;
            }
            x += xOff;
        }
    }
    public static void markVisibleY(Vector<Vector<Tree>> trees, int x, int y, int yOff){
        int height = -1;
        while(y >= 0 && y < trees.size()){
            Tree tree = trees.get(x).get(y);
            if(tree.height > height){
                tree.isVisible = true;
                height = tree.height;
            }
            y += yOff;
        }
    }
    public static class Tree{
        public final int height;
        public boolean isVisible;
        public Tree(int height) {
            this.height = height;
            this.isVisible = false;
        }
    }
}
