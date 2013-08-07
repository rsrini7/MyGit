package com.srini.easymock.sample;

import java.util.Map;
public class Cache {
    private Map<Integer, String> underlyingStorage;
    public Cache(Map<Integer, String> underlyingStorage) {
        this.underlyingStorage = underlyingStorage;
    }
    public String get(int key) {
        return underlyingStorage.get(key);
    }
    public void add(int key, String value) {
        underlyingStorage.put(key, value);
    }
    public void remove(int key) {
        underlyingStorage.remove(key);
    }
    public int size() {
        return underlyingStorage.size();
    }
    public void clear() {
        underlyingStorage.clear();
    }
}