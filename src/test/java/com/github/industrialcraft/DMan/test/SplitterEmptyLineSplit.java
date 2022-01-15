package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.Splitter;

import java.util.Arrays;
import java.util.List;

public class SplitterEmptyLineSplit {
    public static void main(String args[]){
        String input = "aaaa\nbbbb\ncc\n\ngnfigdf\n\nfdsongfsd\nfsdmfds";
        List<String> split = Splitter.byEmptyLine(input);
        System.out.println(Arrays.toString(split.toArray()));
    }
}
