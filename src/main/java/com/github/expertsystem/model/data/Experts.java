package com.github.expertsystem.model.data;

import java.util.ArrayList;
import java.util.List;

public class Experts {

    private static List<Expert> experts = new ArrayList<Expert>();

    public static void setExperts(Object[] list) {
        experts.clear();
        for (Object expertName : list) {
            addExpert(expertName.toString());
        }
    }

    public static void addExpert(String expertName) {
        experts.add(new Expert(expertName));
    }

    public static void addExpert(Expert expert) {
        experts.add(expert);
    }

    public static Expert findExpert(int index) {
        return getExperts().get(index);
    }

    public static List<Expert> getExperts() {
        return experts;
    }

    public static int getSize() {
        return experts.size();
    }
}
