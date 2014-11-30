package com.github.expertsystem.model.data;

public class Obj {

    private String name;

    public Obj(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}