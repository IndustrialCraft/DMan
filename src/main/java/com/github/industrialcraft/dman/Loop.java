package com.github.industrialcraft.dman;

import com.github.industrialcraft.dman.lambda.DoWhileLambda;
import com.github.industrialcraft.dman.lambda.IndexLambda;

public class Loop {
    public static void times(int amount, IndexLambda lambda){
        if(amount < 0)
            return;
        for(int i = 0;i < amount;i++){
            lambda.process(i);
        }
    }
    public static void dowhile(DoWhileLambda lambda){
        WrappedBoolean wrappedBoolean = new WrappedBoolean(false);
        do{
            wrappedBoolean.unset();
            lambda.process(wrappedBoolean);
        } while (wrappedBoolean.is());
    }
}
