package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.FileUtils;
import com.github.industrialcraft.dman.Regex;
import com.github.industrialcraft.dman.StringSplitter;
import com.github.industrialcraft.dman.StringUtils;
import io.vavr.collection.Vector;

public class AOC2022Day11 {
    public static void main(String[] args){
        Vector<String> input = StringSplitter.splitString(FileUtils.readWhole("inputs/input10.txt"), Regex.li("\n\n")).map(s -> s.replace("\n", ""));

    }
}
