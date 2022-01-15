package com.github.industrialcraft.dman.lambda;

public interface LambdaTriplet<A,B,C> {
    void process(A first, B second, C third);
}
