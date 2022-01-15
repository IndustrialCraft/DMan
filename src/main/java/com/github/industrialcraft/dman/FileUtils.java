package com.github.industrialcraft.dman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FileUtils {
    public static String readFile(String fileName){
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    fileName));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            return null;
        }
        return lines.stream().collect(Collectors.joining("\n"));
    }
}
