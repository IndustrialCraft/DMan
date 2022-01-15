package com.github.industrialcraft.dman.lambda.grid;

public interface LambdaElementPosRet<T> {
    T process(T element, int x, int y);
}
