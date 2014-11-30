package com.github.expertsystem.model.data;

public class Criterion {

    private String name;

    private float[] weights = new float[Experts.getSize()];

    public Criterion(String name, float[] weights) {
        this.name = name;
        for(int i = 0; i < weights.length; i++) {
            this.weights[i] = weights[i];
        }
    }

    public String getName() {
        return name;
    }

    public float getWeight(int index) {
        return weights[index];
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