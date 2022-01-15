package com.github.industrialcraft.dman.lambda;

public interface LambdaPair<A,B> {
    void process(A first, B second);
}
