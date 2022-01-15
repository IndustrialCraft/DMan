package com.github.industrialcraft.dman.lambda;

import com.github.industrialcraft.dman.group.Group;
import com.github.industrialcraft.dman.group.GroupList;

import java.math.BigInteger;
import java.util.HashMap;

public interface GroupLambda {
    void process(BigInteger count, GroupList list);
}
