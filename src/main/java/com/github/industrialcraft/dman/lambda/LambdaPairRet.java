package com.github.industrialcraft.dman.lambda;

public interface LambdaPairRet<R,A,B> {
    R process(A first, B second);
}
