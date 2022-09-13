package com.github.industrialcraft.DMan.test;

import com.github.industrialcraft.dman.*;

import java.io.File;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import com.github.industrialcraft.dman.regex.Regex;
import io.vavr.Tuple2;
import io.vavr.collection.Vector;
import io.vavr.control.Try;

import static com.github.industrialcraft.dman.Printer.*;

public class Test {
    public static void main(String[] main){
        d17();
    }
    public static void d1(){
        println(IndexedTransformer.mapIndexed(FileUtils.readLines(new File("input.txt")).map(Integer::parseInt), (i, index) -> Try.of(() -> i+index.offsetOrThrow(1)+index.offsetOrThrow(2)<(index.offsetOrThrow(1)+index.offsetOrThrow(2)+index.offsetOrThrow(3))?1:0).getOrElse(0)).sum());
    }
    public static void d2(){
        Regex regex = Regex.of("(\\w+) (\\d+)");
        AtomicInteger x = new AtomicInteger();
        AtomicInteger y = new AtomicInteger();
        AtomicInteger aim = new AtomicInteger();
        FileUtils.readLines(new File("input.txt")).forEach(s -> {
            Matcher matcher = regex.matches(s).get();
            int num = Integer.parseInt(matcher.group(2));
            switch (matcher.group(1)){
                case "forward":
                    x.addAndGet(num);
                    y.addAndGet(num*aim.get());
                    break;
                case "down":
                    aim.addAndGet(num);
                    break;
                case "up":
                    aim.addAndGet(-num);
                    break;
            }
        });
        println("x: %s y: %s", x, y);
        println(x.get()*y.get());
    }
    public static void d3(){
        var t = Grid.rotateLeft(FileUtils.readLines("input.txt").map(s -> StringSplitter.splitToChars(s)))
                .map(characters -> characters.reverse()).map(characters -> VectorUtils.join(characters))
                .map(characters -> {
                    CharCounter counter = CharCounter.count(characters);
                    return new Tuple2<Character,Character>(counter.least().get(), counter.most());
                });
        int gamma = Integer.parseInt(StringUtils.reverse(VectorUtils.join(t.map(characterCharacterTuple2 -> characterCharacterTuple2._1))), 2);
        int delta = Integer.parseInt(StringUtils.reverse(VectorUtils.join(t.map(characterCharacterTuple2 -> characterCharacterTuple2._2))), 2);
        println(gamma*delta);
    }
    public static void d3_2(){
        var t = FileUtils.readLines("input.txt");
        var ref = new Object() {
            int index = 0;
        };
        while (t.length() > 1){
            Character most = CharCounter.count(VectorUtils.join(t.map(character -> character.charAt(ref.index)))).least().get();
            t = t.filter(character -> character.charAt(ref.index) == most);
            ref.index++;
        }
        println(Integer.parseInt(t.get(0), 2));
    }
    public static void d17(){
        Range rangeX = Range.of(277, 318);
        Range rangeY = Range.of(-92, -53);
//        Range rangeX = Range.of(20, 30);
//        Range rangeY = Range.of(-10, -5);
        int withinNum = 0;
        for(int x = 0;x < 500;x++){
            for(int y = 500;y > -500;y--){
                int maxY = 0;
                int posX = 0;
                int posY = 0;
                int velX = x;
                int velY = y;
                while(!rangeY.below(posY)){
                    posX += velX;
                    posY += velY;
                    velY -= 1;
                    velX = NumberUtils.subTowardsZero(velX, 1);
                    if(posY > maxY)
                        maxY = posY;
                    if(rangeX.within(posX) && rangeY.within(posY)){
                        //println("x: %s,y: %s, h: %s", x, y, maxY);
                        //return;
                        withinNum++;
                        break;
                    }
                }
            }
        }
        println(withinNum);
    }
}
