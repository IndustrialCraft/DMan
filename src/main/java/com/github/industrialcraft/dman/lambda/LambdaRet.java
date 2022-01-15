package com.github.industrialcraft.dman.lambda;

public interface LambdaRet<R,A> {
    R process(A arg);
}
