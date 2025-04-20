package com.github.expertsystem.model.data;

import java.util.ArrayList;
import java.util.List;

public class Criterions {

    private static List<Criterion> criterions = new ArrayList<Criterion>();

    public static void setCriterions(Object[] list) {
        criterions.clear();
        for (Object criterionName : list) {
            addCriterion(criterionName.toString(), new float[] {});
        }
    }

    public static void addCriterion(String criterionName, float[] w) {
        criterions.add(new Criterion(criterionName, w));
    }

    public static void addCriterion(Criterion criterion) {
        criterions.add(criterion);
    }

    public static Criterion findCriterion(int index) {
        return getCriterions().get(index);
    }

    public static List<Criterion> getCriterions() {
        return criterions;
    }

    public static int getSize() {
        return criterions.size();
    }
}
