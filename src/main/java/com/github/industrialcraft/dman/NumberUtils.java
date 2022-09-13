package com.github.industrialcraft.dman;

import java.math.BigInteger;

public class NumberUtils {
    public static int subTowardsZero(int num, int toSub){
        if(Math.abs(num) <= toSub)
            return 0;
        if(num < 0)
            num += toSub;
        else
            num -= toSub;
        return num;
    }
    public static BigInteger factorial(int num) {
        BigInteger res = BigInteger.ONE;
        for (int i = 2; i <= num; i++)
            res = res.multiply(BigInteger.valueOf(i));
        return res;
    }
}
