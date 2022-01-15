package com.github.industrialcraft.dman.lambda;

public interface LambdaTripletRet<R,A,B,C> {
    R process(A first, B second, C third);
}
