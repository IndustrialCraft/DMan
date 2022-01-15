package com.github.industrialcraft.dman.group;

import com.github.industrialcraft.dman.Loop;
import com.github.industrialcraft.dman.lambda.GroupLambda;
import com.github.industrialcraft.dman.lambda.MultiGroupLambda;

public class MultiGroup implements IGroup{
    String type;
    MultiGroupLambda lambda;
    int amt;
    public MultiGroup(String type, int amt, MultiGroupLambda lambda) {
        this.type = type;
        this.lambda = lambda;
        this.amt = amt;
    }
    @Override
    public void process(GroupList oldList, GroupList newList){
        Loop.times(amt, i -> this.lambda.process(i, oldList.get(type + i), newList));
    }
}
