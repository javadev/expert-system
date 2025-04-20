package com.github.expertsystem.model.calculate;

import com.github.expertsystem.BufferedInputFile;
import com.github.expertsystem.model.data.Criterions;
import com.github.expertsystem.model.data.Experts;
import com.github.expertsystem.model.data.Marks;
import com.github.expertsystem.model.data.Objects;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Method5 {

    public static final String FILENAME = "./method5.txt";

    private List<List<int[][]>> pairs;
    private List<List<int[]>> pairSums;
    private List<List<int[]>> pairRangs;
    private List<float[][]> spearman;
    private List<float[][]> kendall;
    private List<List<int[]>> spearmanResultMax;
    private List<List<int[]>> kendallResultMax;
    private List<List<int[]>> spearmanResultMin;
    private List<List<int[]>> kendallResultMin;

    private BufferedReader in;
    private PrintWriter outPut;

    private NumberFormat formatter;

    private void pairsCalculate() {
        int sum;
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            for (int ex = 0; ex < Experts.getSize(); ex++) {
                pairs.get(cr).add(new int[Objects.getSize()][Objects.getSize()]);
                pairSums.get(cr).add(new int[Objects.getSize()]);
                for (int ob1 = 0; ob1 < Objects.getSize(); ob1++) {
                    sum = 0;
                    for (int ob2 = 0; ob2 < Objects.getSize(); ob2++) {
                        if (Marks.getMark(
                                        Experts.findExpert(ex),
                                        Objects.findObj(ob1),
                                        Criterions.findCriterion(cr))
                                == Marks.getMark(
                                        Experts.findExpert(ex),
                                        Objects.findObj(ob2),
                                        Criterions.findCriterion(cr))) {
                            pairs.get(cr).get(ex)[ob1][ob2] = 0;
                        } else if (Marks.getMark(
                                        Experts.findExpert(ex),
                                        Objects.findObj(ob1),
                                        Criterions.findCriterion(cr))
                                < Marks.getMark(
                                        Experts.findExpert(ex),
                                        Objects.findObj(ob2),
                                        Criterions.findCriterion(cr))) {
                            pairs.get(cr).get(ex)[ob1][ob2] = 1;
                        } else {
                            pairs.get(cr).get(ex)[ob1][ob2] = -1;
                        }
                        sum += pairs.get(cr).get(ex)[ob1][ob2];
                    }
                    pairSums.get(cr).get(ex)[ob1] = sum;
                }
            }
        }
    }

    private void pairRangsCalculate() {
        outPut.println();
        outPut.println("Расчет рангов");
        outPut.println();
        List<Integer> list;
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println(
                    "Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            outPut.println();
            for (int ex = 0; ex < Experts.getSize(); ex++) {
                outPut.println("Эксперт " + Experts.findExpert(ex).getName().toUpperCase());
                pairRangs.get(cr).add(new int[Objects.getSize()]);
                list = new ArrayList<Integer>();
                for (int i = 0; i < Objects.getSize(); i++) {
                    list.add(pairSums.get(cr).get(ex)[i]);
                }
                int max;
                int rang = 1;
                while (list.size() > 0) {
                    max = list.get(0);
                    for (int j = 0; j < list.size() - 1; j++) {
                        if (max < list.get(j + 1)) {
                            max = list.get(j + 1);
                        }
                    }
                    for (int j = 0; j < Objects.getSize(); j++) {
                        if (pairSums.get(cr).get(ex)[j] == max) {
                            pairRangs.get(cr).get(ex)[j] = rang;
                            rang++;
                        }
                    }
                    while (list.contains(max)) {
                        list.remove((Object) max);
                    }
                }
                for (int j = 0; j < Objects.getSize(); j++) {
                    outPut.println(
                            "R" + (j + 1) + " = " + formatter.format(pairRangs.get(cr).get(ex)[j]));
                }
                outPut.println();
            }
            outPut.println();
        }
    }

    private void spearmanCalculate() {
        String str = "";
        outPut.println(
                "Расчет коэффициентов корреляции Спирмена ( формула: ρkt = 1 - (6 / (n^3 - n)) * Σj ((Rkj - Rtj)^2) )");
        outPut.println();
        int sum;
        float max, min;
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            max = -100;
            min = 100;
            outPut.println(
                    "Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            spearman.add(new float[Experts.getSize()][Experts.getSize()]);
            for (int ex1 = 0; ex1 < Experts.getSize(); ex1++) {
                for (int ex2 = 0; ex2 < Experts.getSize(); ex2++) {
                    sum = 0;
                    if (ex1 != ex2) {
                        str = "ρ" + (ex1 + 1) + "," + (ex2 + 1) + " = ";
                        for (int ob = 0; ob < Objects.getSize(); ob++) {
                            sum +=
                                    (Math.pow(
                                            (pairRangs.get(cr).get(ex1)[ob]
                                                    - pairRangs.get(cr).get(ex2)[ob]),
                                            2));
                        }
                        spearman.get(cr)[ex1][ex2] =
                                (float)
                                        (1.0f
                                                - ((6.0f
                                                                / (Math.pow(Objects.getSize(), 3)
                                                                        - Objects.getSize()))
                                                        * sum));
                        str =
                                str
                                        + "1 - (6 / "
                                        + formatter.format(Math.pow(Objects.getSize(), 3))
                                        + " - "
                                        + Objects.getSize()
                                        + ") * "
                                        + formatter.format(sum)
                                        + " = "
                                        + formatter.format(spearman.get(cr)[ex1][ex2]);
                        outPut.println(str);
                        if (max < spearman.get(cr)[ex1][ex2]) {
                            max = spearman.get(cr)[ex1][ex2];
                        }
                        if (min > spearman.get(cr)[ex1][ex2]) {
                            min = spearman.get(cr)[ex1][ex2];
                        }
                    }
                }
            }
            for (int ex1 = 0; ex1 < Experts.getSize(); ex1++) {
                for (int ex2 = 0; ex2 < Experts.getSize(); ex2++) {
                    if (ex1 < ex2) {
                        if (spearman.get(cr)[ex1][ex2] == max) {
                            spearmanResultMax.get(cr).add(new int[] {ex1, ex2});
                        }
                        if (spearman.get(cr)[ex1][ex2] == min) {
                            spearmanResultMin.get(cr).add(new int[] {ex1, ex2});
                        }
                    }
                }
            }
            outPut.println();
        }
        outPut.println();
    }

    private void kendallCalculate() {
        String str = "";
        outPut.println(
                "Расчет коэффициентов корреляции Кендалла "
                        + "( формула: τkt = (2 / (n * (n - 1)) * Σij (sign[(Rki - Rkj) * (Rti - Rtj)]) )");
        outPut.println();
        int sign;
        int sum;
        float max, min;
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            max = -100;
            min = 100;
            outPut.println(
                    "Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            kendall.add(new float[Experts.getSize()][Experts.getSize()]);
            for (int ex1 = 0; ex1 < Experts.getSize(); ex1++) {
                for (int ex2 = 0; ex2 < Experts.getSize(); ex2++) {
                    sum = 0;
                    if (ex1 != ex2) {
                        str = "τ" + (ex1 + 1) + "," + (ex2 + 1) + " = ";
                        for (int ob = 0; ob < Objects.getSize(); ob++) {
                            sign =
                                    sign(
                                                    pairRangs.get(cr).get(ex1)[ob]
                                                            - pairRangs.get(cr)
                                                                    .get(ex1)[
                                                                    Objects.getSize() - 1])
                                            * sign(
                                                    pairRangs.get(cr).get(ex2)[ob]
                                                            - pairRangs.get(cr)
                                                                    .get(ex2)[
                                                                    Objects.getSize() - 1]);
                            sum += sign;
                        }
                        kendall.get(cr)[ex1][ex2] =
                                (2.0f / (Objects.getSize() * (Objects.getSize() - 1.0f))) * sum;
                        str =
                                str
                                        + "2 / ("
                                        + Objects.getSize()
                                        + " * ("
                                        + Objects.getSize()
                                        + " - 1)) * "
                                        + formatter.format(sum)
                                        + " = "
                                        + formatter.format(kendall.get(cr)[ex1][ex2]);
                        outPut.println(str);
                        if (max < kendall.get(cr)[ex1][ex2]) {
                            max = kendall.get(cr)[ex1][ex2];
                        }
                        if (min > kendall.get(cr)[ex1][ex2]) {
                            min = kendall.get(cr)[ex1][ex2];
                        }
                    }
                }
            }
            for (int ex1 = 0; ex1 < Experts.getSize(); ex1++) {
                for (int ex2 = 0; ex2 < Experts.getSize(); ex2++) {
                    if (ex1 < ex2) {
                        if (kendall.get(cr)[ex1][ex2] == max) {
                            kendallResultMax.get(cr).add(new int[] {ex1, ex2});
                        }
                        if (kendall.get(cr)[ex1][ex2] == min) {
                            kendallResultMin.get(cr).add(new int[] {ex1, ex2});
                        }
                    }
                }
            }
            outPut.println();
        }
        outPut.println();
    }

    private int sign(int value) {
        if (value > 0) return 1;
        if (value < 0) return -1;
        return 0;
    }

    public Method5() {
        formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(3);
        pairs = new ArrayList<List<int[][]>>();
        for (int i = 0; i < Criterions.getSize(); i++) {
            pairs.add(new ArrayList<int[][]>());
        }
        pairSums = new ArrayList<List<int[]>>();
        for (int i = 0; i < Criterions.getSize(); i++) {
            pairSums.add(new ArrayList<int[]>());
        }
        pairRangs = new ArrayList<List<int[]>>();
        for (int i = 0; i < Criterions.getSize(); i++) {
            pairRangs.add(new ArrayList<int[]>());
        }
        spearman = new ArrayList<float[][]>();
        kendall = new ArrayList<float[][]>();
        spearmanResultMax = new ArrayList<List<int[]>>();
        for (int i = 0; i < Criterions.getSize(); i++) {
            spearmanResultMax.add(new ArrayList<int[]>());
        }
        kendallResultMax = new ArrayList<List<int[]>>();
        for (int i = 0; i < Criterions.getSize(); i++) {
            kendallResultMax.add(new ArrayList<int[]>());
        }
        spearmanResultMin = new ArrayList<List<int[]>>();
        for (int i = 0; i < Criterions.getSize(); i++) {
            spearmanResultMin.add(new ArrayList<int[]>());
        }
        kendallResultMin = new ArrayList<List<int[]>>();
        for (int i = 0; i < Criterions.getSize(); i++) {
            kendallResultMin.add(new ArrayList<int[]>());
        }

        try {
            in = new BufferedReader(new StringReader(new BufferedInputFile().read(FILENAME)));
        } catch (IOException e) {
        }
        try {
            outPut = new PrintWriter(FILENAME);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null, "Error with file " + FILENAME + "!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void calculate() {
        outPut.println("ОПРЕДЕЛЕНИЕ ВЗАИМОСВЯЗИ РАНЖИРОВОК");
        outPut.println();
        outPut.println("Условные обозначения:");
        outPut.println("n - количество объектов");
        outPut.println("m - количество экспертов");
        outPut.println("l - количество показателей");
        outPut.println("i - номер объекта");
        outPut.println("j, k, t - номера экспертов");
        outPut.println("h - номер показателя");
        outPut.println();
        pairsCalculate();
        pairRangsCalculate();
        spearmanCalculate();
        kendallCalculate();
        outPut.close();
    }

    public float[][] getSpearman(int cr) {
        return spearman.get(cr);
    }

    public float[][] getKendall(int cr) {
        return kendall.get(cr);
    }

    public List<int[]> getSpearmanResultMax(int cr) {
        return spearmanResultMax.get(cr);
    }

    public List<int[]> getKendallResultMax(int cr) {
        return kendallResultMax.get(cr);
    }

    public List<int[]> getSpearmanResultMin(int cr) {
        return spearmanResultMin.get(cr);
    }

    public List<int[]> getKendallResultMin(int cr) {
        return kendallResultMin.get(cr);
    }
}
