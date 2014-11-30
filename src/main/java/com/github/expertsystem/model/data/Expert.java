package com.github.expertsystem.model.data;

public class Expert {

    private static final float DEF_COMPETENCE = 0.5f;

    private String name;

    private float competence;

    public Expert(String name, float competence) {
        this.name = name;
        this.competence = competence;
    }

    public Expert(String name) {
        this(name, DEF_COMPETENCE);
    }

    public String getName() {
        return name;
    }

    public float getCompetence() {
        return competence;
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