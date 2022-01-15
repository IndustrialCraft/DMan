package com.github.industrialcraft.dman.group;

import com.github.industrialcraft.dman.lambda.GroupLambda;

public class Group implements IGroup{
    String type;
    GroupLambda lambda;
    public Group(String type, GroupLambda lambda) {
        this.type = type;
        this.lambda = lambda;
    }
    @Override
    public void process(GroupList oldList, GroupList newList){
        this.lambda.process(oldList.get(type), newList);
    }
}
