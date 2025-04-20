package com.github.expertsystem.model.data;

import java.util.ArrayList;
import java.util.List;

public class Objects {

    private static List<Obj> objects = new ArrayList<Obj>();

    public static void setObjects(Object[] list) {
        objects.clear();
        for (Object objectName : list) {
            addObj(objectName.toString());
        }
    }

    public static void addObj(String objectName) {
        objects.add(new Obj(objectName));
    }

    public static void addObj(Obj object) {
        objects.add(object);
    }

    public static Obj findObj(int index) {
        return getObjects().get(index);
    }

    public static List<Obj> getObjects() {
        return objects;
    }

    public static int getSize() {
        return objects.size();
    }
}
