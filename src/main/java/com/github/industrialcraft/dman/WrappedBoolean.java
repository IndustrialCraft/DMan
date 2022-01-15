package com.github.industrialcraft.dman;

public class WrappedBoolean {
    private boolean val;
    public WrappedBoolean(boolean val) {
        this.val = val;
    }
    public void set(){
        val = true;
    }
    public void unset(){
        val = false;
    }
    public void set(boolean val){
        this.val = val;
    }
    public boolean is(){
        return this.val;
    }
    public void toggle(){
        this.val = !val;
    }
}
