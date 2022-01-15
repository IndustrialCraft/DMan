package com.github.industrialcraft.dman.lambda.grid;

public interface LambdaElementPos<T> {
    void process(T element, int x, int y);
}
