package com.github.industrialcraft.dman.group;

import com.github.industrialcraft.dman.ArrayUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;

public class Groups {
    IGroup groups[];
    GroupList list;
    public Groups(IGroup... groups) {
        this.groups = groups;
        this.list = new GroupList();
    }
    public void process(){
        GroupList newList = new GroupList();
        for(int i = 0;i < groups.length;i++){
            groups[i].process(this.list, newList);
        }
        this.list = newList;
    }
    public void add(String type, BigInteger count){
        list.add(type, count);
    }
    public GroupList getGroupList() {
        return list;
    }

    public BigInteger countAll(){
        BigInteger cnt = BigInteger.ZERO;
        for(Map.Entry<String,BigInteger> e : list.list.entrySet()){
            cnt = cnt.add(e.getValue());
        }
        return cnt;
    }
}
