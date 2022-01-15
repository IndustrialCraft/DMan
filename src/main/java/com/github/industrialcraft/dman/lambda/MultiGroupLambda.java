package com.github.industrialcraft.dman.lambda;

import com.github.industrialcraft.dman.group.GroupList;

import java.math.BigInteger;

public interface MultiGroupLambda {
    void process(int index, BigInteger count, GroupList list);
}
