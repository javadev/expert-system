package com.github.expertsystem.model.data;

import java.util.LinkedHashMap;
import java.util.Random;

public class Marks {

    private static final float NULL_MARK = 0.0f;

    // This map contains inner maps as values because of uniqueness of sets "expert + object +
    // criterion",
    // which corresponds to marks.
    private static LinkedHashMap<Expert, LinkedHashMap<Object, LinkedHashMap<Criterion, Float>>>
            marks =
                    new LinkedHashMap<
                            Expert, LinkedHashMap<Object, LinkedHashMap<Criterion, Float>>>();

    static {
        fillNullMap();
    }

    public static void setMark(Expert expert, Obj object, Criterion criterion, float mark) {
        marks.get(expert).get(object).put(criterion, mark);
    }

    public static float getMark(Expert expert, Obj object, Criterion criterion) {
        return marks.get(expert).get(object).get(criterion);
    }

    public static void fillNullMap() {
        marks.clear();
        for (Expert expert : Experts.getExperts()) {
            marks.put(expert, new LinkedHashMap<Object, LinkedHashMap<Criterion, Float>>());
            for (Obj object : Objects.getObjects()) {
                marks.get(expert).put(object, new LinkedHashMap<Criterion, Float>());
                for (Criterion criterion : Criterions.getCriterions()) {
                    marks.get(expert).get(object).put(criterion, NULL_MARK);
                }
            }
        }
    }

    public static void fillRandomMap() {
        marks.clear();
        Random rand = new Random();
        for (Expert expert : Experts.getExperts()) {
            marks.put(expert, new LinkedHashMap<Object, LinkedHashMap<Criterion, Float>>());
            for (Obj object : Objects.getObjects()) {
                marks.get(expert).put(object, new LinkedHashMap<Criterion, Float>());
                for (Criterion criterion : Criterions.getCriterions()) {
                    marks.get(expert).get(object).put(criterion, (float) rand.nextInt(10));
                }
            }
        }
    }
}
