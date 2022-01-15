package com.github.industrialcraft.dman;

import com.github.industrialcraft.dman.lambda.LambdaRet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DefaultedMap<K,V> extends HashMap<K,V> {
    V defaultValue;
    public DefaultedMap(V defaultValue, int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
        this.defaultValue = defaultValue;
    }
    public DefaultedMap(V defaultValue, int initialCapacity) {
        super(initialCapacity);
        this.defaultValue = defaultValue;
    }
    public DefaultedMap(V defaultValue) {
        this.defaultValue = defaultValue;
    }
    public DefaultedMap(V defaultValue, Map<? extends K, ? extends V> m) {
        super(m);
        this.defaultValue = defaultValue;
    }
    public void insert(Collection<K> keys, LambdaRet<V,K> lambda){
        for(K key : keys){
            put(key, lambda.process(key));
        }
    }
    public void modify(K key, LambdaRet<V,V> lambda){
        put(key, lambda.process(get(key)));
    }
    @Override
    public V get(Object key) {
        return getOrDefault(key, defaultValue);
    }
}
