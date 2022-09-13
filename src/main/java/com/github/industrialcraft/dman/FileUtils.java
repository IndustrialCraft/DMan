package com.github.industrialcraft.dman;

import io.vavr.collection.Vector;
import io.vavr.control.Try;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileUtils {
    public static String readWhole(File file, String lineDelimiter){
        return Try.of(() -> Files.lines(file.toPath()).collect(Collectors.joining(lineDelimiter)))
                .getOrElseThrow(() -> new RuntimeException("File not found " + file.getName()));
    }
    public static String readWhole(File file){
        return Try.of(() -> Files.lines(file.toPath()).collect(Collectors.joining("\n")))
                .getOrElseThrow(() -> new RuntimeException("File not found " + file.getName()));
    }
    public static Vector<String> readLines(File file){
        return Try.of(() -> Vector.ofAll(Files.lines(file.toPath())))
                .getOrElseThrow(() -> new RuntimeException("File not found " + file.getName()));
    }
    public static String readWhole(String file, String lineDelimiter){
        return Try.of(() -> Files.lines(Path.of(file)).collect(Collectors.joining(lineDelimiter)))
                .getOrElseThrow(() -> new RuntimeException("File not found " + file));
    }
    public static String readWhole(String file){
        return Try.of(() -> Files.lines(Path.of(file)).collect(Collectors.joining("\n")))
                .getOrElseThrow(() -> new RuntimeException("File not found " + file));
    }
    public static Vector<String> readLines(String file){
        return Try.of(() -> Vector.ofAll(Files.lines(Path.of(file))))
                .getOrElseThrow(() -> new RuntimeException("File not found " + file));
    }
}
