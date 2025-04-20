package com.github.expertsystem.model;

import com.github.expertsystem.model.data.*;

/** Static class, that fills default values in model */
public class FillDefaultValues {

    public static boolean isChangedDefaultValues = false;

    public static void fill() {
        Experts.addExpert(new Expert("Alex", 0.1f));
        Experts.addExpert(new Expert("Max", 0.3f));
        Experts.addExpert(new Expert("Simon", 0.2f));
        Experts.addExpert(new Expert("Nick", 0.2f));
        Experts.addExpert(new Expert("Tom", 0.1f));
        Experts.addExpert(new Expert("Zach", 0.1f));

        Objects.addObj(new Obj("Nokia"));
        Objects.addObj(new Obj("Panosonic"));
        Objects.addObj(new Obj("Siemens"));
        Objects.addObj(new Obj("Sony"));
        Objects.addObj(new Obj("Philips"));
        Objects.addObj(new Obj("Saturn"));
        Objects.addObj(new Obj("Samsung"));

        Criterions.addCriterion(
                new Criterion("style", new float[] {0.3f, 0.2f, 0.3f, 0.1f, 0.2f, 0.1f}));
        Criterions.addCriterion(
                new Criterion("sound", new float[] {0.4f, 0.2f, 0.3f, 0.4f, 0.3f, 0.4f}));
        Criterions.addCriterion(
                new Criterion("quality", new float[] {0.2f, 0.5f, 0.2f, 0.3f, 0.3f, 0.2f}));
        Criterions.addCriterion(
                new Criterion("colors", new float[] {0.1f, 0.1f, 0.2f, 0.2f, 0.2f, 0.3f}));
    }

    public static void fillMarks() {
        Marks.setMark(Experts.findExpert(0), Objects.findObj(0), Criterions.findCriterion(0), 2);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(0), Criterions.findCriterion(0), 3);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(0), Criterions.findCriterion(0), 2);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(0), Criterions.findCriterion(0), 1);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(0), Criterions.findCriterion(0), 4);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(0), Criterions.findCriterion(0), 5);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(1), Criterions.findCriterion(0), 1);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(1), Criterions.findCriterion(0), 5);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(1), Criterions.findCriterion(0), 4);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(1), Criterions.findCriterion(0), 5);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(1), Criterions.findCriterion(0), 6);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(1), Criterions.findCriterion(0), 7);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(2), Criterions.findCriterion(0), 2);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(2), Criterions.findCriterion(0), 3);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(2), Criterions.findCriterion(0), 7);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(2), Criterions.findCriterion(0), 3);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(2), Criterions.findCriterion(0), 5);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(2), Criterions.findCriterion(0), 4);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(3), Criterions.findCriterion(0), 9);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(3), Criterions.findCriterion(0), 4);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(3), Criterions.findCriterion(0), 4);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(3), Criterions.findCriterion(0), 5);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(3), Criterions.findCriterion(0), 4);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(3), Criterions.findCriterion(0), 7);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(4), Criterions.findCriterion(0), 9);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(4), Criterions.findCriterion(0), 6);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(4), Criterions.findCriterion(0), 3);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(4), Criterions.findCriterion(0), 9);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(4), Criterions.findCriterion(0), 6);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(4), Criterions.findCriterion(0), 6);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(5), Criterions.findCriterion(0), 4);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(5), Criterions.findCriterion(0), 8);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(5), Criterions.findCriterion(0), 7);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(5), Criterions.findCriterion(0), 6);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(5), Criterions.findCriterion(0), 5);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(5), Criterions.findCriterion(0), 4);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(6), Criterions.findCriterion(0), 4);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(6), Criterions.findCriterion(0), 9);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(6), Criterions.findCriterion(0), 10);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(6), Criterions.findCriterion(0), 10);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(6), Criterions.findCriterion(0), 7);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(6), Criterions.findCriterion(0), 9);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(0), Criterions.findCriterion(1), 5);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(0), Criterions.findCriterion(1), 10);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(0), Criterions.findCriterion(1), 8);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(0), Criterions.findCriterion(1), 6);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(0), Criterions.findCriterion(1), 9);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(0), Criterions.findCriterion(1), 2);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(1), Criterions.findCriterion(1), 7);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(1), Criterions.findCriterion(1), 9);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(1), Criterions.findCriterion(1), 6);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(1), Criterions.findCriterion(1), 4);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(1), Criterions.findCriterion(1), 2);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(1), Criterions.findCriterion(1), 3);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(2), Criterions.findCriterion(1), 3);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(2), Criterions.findCriterion(1), 7);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(2), Criterions.findCriterion(1), 7);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(2), Criterions.findCriterion(1), 2);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(2), Criterions.findCriterion(1), 5);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(2), Criterions.findCriterion(1), 4);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(3), Criterions.findCriterion(1), 5);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(3), Criterions.findCriterion(1), 1);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(3), Criterions.findCriterion(1), 3);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(3), Criterions.findCriterion(1), 7);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(3), Criterions.findCriterion(1), 7);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(3), Criterions.findCriterion(1), 5);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(4), Criterions.findCriterion(1), 7);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(4), Criterions.findCriterion(1), 2);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(4), Criterions.findCriterion(1), 5);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(4), Criterions.findCriterion(1), 6);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(4), Criterions.findCriterion(1), 2);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(4), Criterions.findCriterion(1), 2);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(5), Criterions.findCriterion(1), 9);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(5), Criterions.findCriterion(1), 5);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(5), Criterions.findCriterion(1), 6);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(5), Criterions.findCriterion(1), 5);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(5), Criterions.findCriterion(1), 6);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(5), Criterions.findCriterion(1), 2);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(6), Criterions.findCriterion(1), 3);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(6), Criterions.findCriterion(1), 4);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(6), Criterions.findCriterion(1), 6);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(6), Criterions.findCriterion(1), 4);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(6), Criterions.findCriterion(1), 8);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(6), Criterions.findCriterion(1), 6);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(0), Criterions.findCriterion(2), 8);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(0), Criterions.findCriterion(2), 9);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(0), Criterions.findCriterion(2), 10);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(0), Criterions.findCriterion(2), 7);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(0), Criterions.findCriterion(2), 10);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(0), Criterions.findCriterion(2), 7);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(1), Criterions.findCriterion(2), 5);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(1), Criterions.findCriterion(2), 4);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(1), Criterions.findCriterion(2), 4);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(1), Criterions.findCriterion(2), 7);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(1), Criterions.findCriterion(2), 8);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(1), Criterions.findCriterion(2), 5);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(2), Criterions.findCriterion(2), 3);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(2), Criterions.findCriterion(2), 5);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(2), Criterions.findCriterion(2), 3);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(2), Criterions.findCriterion(2), 5);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(2), Criterions.findCriterion(2), 5);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(2), Criterions.findCriterion(2), 6);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(3), Criterions.findCriterion(2), 5);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(3), Criterions.findCriterion(2), 3);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(3), Criterions.findCriterion(2), 4);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(3), Criterions.findCriterion(2), 5);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(3), Criterions.findCriterion(2), 6);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(3), Criterions.findCriterion(2), 3);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(4), Criterions.findCriterion(2), 2);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(4), Criterions.findCriterion(2), 1);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(4), Criterions.findCriterion(2), 4);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(4), Criterions.findCriterion(2), 4);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(4), Criterions.findCriterion(2), 3);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(4), Criterions.findCriterion(2), 7);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(5), Criterions.findCriterion(2), 3);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(5), Criterions.findCriterion(2), 6);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(5), Criterions.findCriterion(2), 7);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(5), Criterions.findCriterion(2), 6);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(5), Criterions.findCriterion(2), 5);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(5), Criterions.findCriterion(2), 4);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(6), Criterions.findCriterion(2), 7);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(6), Criterions.findCriterion(2), 5);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(6), Criterions.findCriterion(2), 8);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(6), Criterions.findCriterion(2), 9);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(6), Criterions.findCriterion(2), 5);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(6), Criterions.findCriterion(2), 6);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(0), Criterions.findCriterion(3), 4);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(0), Criterions.findCriterion(3), 3);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(0), Criterions.findCriterion(3), 3);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(0), Criterions.findCriterion(3), 2);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(0), Criterions.findCriterion(3), 6);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(0), Criterions.findCriterion(3), 5);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(1), Criterions.findCriterion(3), 3);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(1), Criterions.findCriterion(3), 5);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(1), Criterions.findCriterion(3), 4);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(1), Criterions.findCriterion(3), 4);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(1), Criterions.findCriterion(3), 7);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(1), Criterions.findCriterion(3), 8);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(2), Criterions.findCriterion(3), 5);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(2), Criterions.findCriterion(3), 2);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(2), Criterions.findCriterion(3), 4);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(2), Criterions.findCriterion(3), 3);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(2), Criterions.findCriterion(3), 5);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(2), Criterions.findCriterion(3), 6);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(3), Criterions.findCriterion(3), 2);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(3), Criterions.findCriterion(3), 1);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(3), Criterions.findCriterion(3), 3);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(3), Criterions.findCriterion(3), 3);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(3), Criterions.findCriterion(3), 4);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(3), Criterions.findCriterion(3), 5);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(4), Criterions.findCriterion(3), 6);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(4), Criterions.findCriterion(3), 8);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(4), Criterions.findCriterion(3), 7);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(4), Criterions.findCriterion(3), 8);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(4), Criterions.findCriterion(3), 9);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(4), Criterions.findCriterion(3), 8);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(5), Criterions.findCriterion(3), 8);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(5), Criterions.findCriterion(3), 8);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(5), Criterions.findCriterion(3), 9);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(5), Criterions.findCriterion(3), 7);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(5), Criterions.findCriterion(3), 7);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(5), Criterions.findCriterion(3), 9);

        Marks.setMark(Experts.findExpert(0), Objects.findObj(6), Criterions.findCriterion(3), 10);
        Marks.setMark(Experts.findExpert(1), Objects.findObj(6), Criterions.findCriterion(3), 9);
        Marks.setMark(Experts.findExpert(2), Objects.findObj(6), Criterions.findCriterion(3), 7);
        Marks.setMark(Experts.findExpert(3), Objects.findObj(6), Criterions.findCriterion(3), 9);
        Marks.setMark(Experts.findExpert(4), Objects.findObj(6), Criterions.findCriterion(3), 8);
        Marks.setMark(Experts.findExpert(5), Objects.findObj(6), Criterions.findCriterion(3), 10);
    }
}
