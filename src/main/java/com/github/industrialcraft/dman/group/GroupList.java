package com.github.industrialcraft.dman.group;

import java.math.BigInteger;
import java.util.HashMap;

public class GroupList {
    HashMap<String, BigInteger> list;
    public GroupList() {
        this.list = new HashMap<>();
    }
    public void add(String type, BigInteger count){
        list.put(type, list.getOrDefault(type, BigInteger.ZERO).add(count));
    }
    public BigInteger get(String type){
        return list.getOrDefault(type, BigInteger.ZERO);
    }
}
