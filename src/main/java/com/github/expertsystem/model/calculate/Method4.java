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

public class Method4 {

    public static final String FILENAME = "./method4.txt";

    private List<List<float[][]>> pairs;
    private List<float[][]> X;
    private List<float[][]> X2;
    private List<float[]> K1;
    private List<float[]> K2;
    private int[] theBestObject;

    private BufferedReader in;
    private PrintWriter outPut;

    private NumberFormat formatter;

    private void pairsCalculate() {
        String str = "";
        outPut.println();
        outPut.println(
                "Расчет матриц парных сравнений ( если Ok > Ot => Rkt = 1; если Ok < Ot => Rkt = 0; если Ok = Ot => Rkt = 0,5 )");
        outPut.println();
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println(
                    "Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            outPut.println();
            for (int ex = 0; ex < Experts.getSize(); ex++) {
                pairs.get(cr).add(new float[Objects.getSize()][Objects.getSize()]);
                outPut.println("Эксперт " + Experts.findExpert(ex).getName().toUpperCase());
                for (int ob1 = 0; ob1 < Objects.getSize(); ob1++) {
                    for (int ob2 = 0; ob2 < Objects.getSize(); ob2++) {
                        if (Marks.getMark(
                                        Experts.findExpert(ex),
                                        Objects.findObj(ob1),
                                        Criterions.findCriterion(cr))
                                == Marks.getMark(
                                        Experts.findExpert(ex),
                                        Objects.findObj(ob2),
                                        Criterions.findCriterion(cr))) {
                            pairs.get(cr).get(ex)[ob1][ob2] = 0.5f;
                            str = "O" + (ob1 + 1) + " = O" + (ob2 + 1) + " : ";
                        } else if (Marks.getMark(
                                        Experts.findExpert(ex),
                                        Objects.findObj(ob1),
                                        Criterions.findCriterion(cr))
                                < Marks.getMark(
                                        Experts.findExpert(ex),
                                        Objects.findObj(ob2),
                                        Criterions.findCriterion(cr))) {
                            pairs.get(cr).get(ex)[ob1][ob2] = 1.0f;
                            str = "O" + (ob1 + 1) + " > O" + (ob2 + 1) + " : ";
                        } else {
                            pairs.get(cr).get(ex)[ob1][ob2] = 0.0f;
                            str = "O" + (ob1 + 1) + " < O" + (ob2 + 1) + " : ";
                        }
                        str =
                                str
                                        + "R"
                                        + (ob1 + 1)
                                        + ","
                                        + (ob2 + 1)
                                        + " = "
                                        + formatter.format(pairs.get(cr).get(ex)[ob1][ob2]);
                        outPut.println(str);
                    }
                }
                outPut.println();
            }
            outPut.println();
        }
    }

    private void xCalculate() {
        int sum1, sum2;
        outPut.println(
                "Расчет оценок математического ожидания случайной величины Rkt ( формула: Xkt = 0,5 + (Mk - Mt) / (2 * m) )");
        outPut.println();
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println(
                    "Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            outPut.println();
            X.add(new float[Objects.getSize()][Objects.getSize()]);
            for (int ob1 = 0; ob1 < Objects.getSize(); ob1++) {
                for (int ob2 = 0; ob2 < Objects.getSize(); ob2++) {
                    sum1 = 0;
                    sum2 = 0;
                    for (int ex = 0; ex < Experts.getSize(); ex++) {
                        if (pairs.get(cr).get(ex)[ob1][ob2] == 1.0f) {
                            sum1++;
                        }
                        if (pairs.get(cr).get(ex)[ob1][ob2] == 0) {
                            sum2++;
                        }
                    }
                    outPut.println(
                            "В пользу объекта "
                                    + (ob2 + 1)
                                    + " высказались "
                                    + sum1
                                    + " экспертов");
                    outPut.println(
                            "В пользу объекта "
                                    + (ob1 + 1)
                                    + " высказались "
                                    + sum2
                                    + " экспертов");
                    X.get(cr)[ob1][ob2] = 0.5f + (float) (sum1 - sum2) / (2.0f * Experts.getSize());
                    outPut.println(
                            "X"
                                    + (ob1 + 1)
                                    + ","
                                    + (ob2 + 1)
                                    + " = 0,5 + ("
                                    + formatter.format(sum1)
                                    + " - "
                                    + formatter.format(sum2)
                                    + ") / (2 * "
                                    + Experts.getSize()
                                    + ") = "
                                    + formatter.format(X.get(cr)[ob1][ob2]));
                }
                outPut.println();
            }
            outPut.println();
        }
    }

    private void x2Calculate() {
        outPut.println(
                "Расчет квадрата матрицы оценок математического ожидания случайной величины X2kt ( формула: |X2kt| = |Xkt|^2 )");
        outPut.println();
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println(
                    "Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            outPut.println();
            X2.add(new float[Objects.getSize()][Objects.getSize()]);
            for (int ob1 = 0; ob1 < Objects.getSize(); ob1++) {
                for (int ob2 = 0; ob2 < Objects.getSize(); ob2++) {
                    for (int ob3 = 0; ob3 < Objects.getSize(); ob3++) {
                        X2.get(cr)[ob2][ob1] += X.get(cr)[ob3][ob1] * X.get(cr)[ob2][ob3];
                    }
                }
            }
            for (int ob1 = 0; ob1 < Objects.getSize(); ob1++) {
                for (int ob2 = 0; ob2 < Objects.getSize(); ob2++) {
                    outPut.println(
                            "X2,"
                                    + (ob1 + 1)
                                    + ","
                                    + (ob2 + 2)
                                    + " = "
                                    + formatter.format(X2.get(cr)[ob2][ob1]));
                }
            }
            outPut.println();
        }
        outPut.println();
    }

    private void k1Calculate() {
        float sum;
        String str = "";
        outPut.println("Расчет коэффициентов относительной важности объектов первого порядка ");
        outPut.println();
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println(
                    "Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            outPut.println();
            K1.add(new float[Objects.getSize()]);
            for (int ob1 = 0; ob1 < Objects.getSize(); ob1++) {
                sum = 0;
                str = "K1," + (ob1 + 1) + " = ";
                for (int ob2 = 0; ob2 < Objects.getSize(); ob2++) {
                    sum += X.get(cr)[ob2][ob1];
                    str = str + formatter.format(X.get(cr)[ob2][ob1]) + " + ";
                }
                str = str.substring(0, str.length() - 3) + " = " + formatter.format(sum);
                outPut.println(str);
                K1.get(cr)[ob1] = sum;
            }
            outPut.println();
        }
        outPut.println();
    }

    private void k2Calculate() {
        float sumK1, sumX2, sumColX2;
        outPut.println("Расчет коэффициентов относительной важности объектов второго порядка ");
        outPut.println();
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println(
                    "Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            outPut.println();
            K2.add(new float[Objects.getSize()]);
            sumK1 = 0;
            for (int ob = 0; ob < Objects.getSize(); ob++) {
                sumK1 += K1.get(cr)[ob];
            }
            sumX2 = 0;
            for (int ob1 = 0; ob1 < Objects.getSize(); ob1++) {
                for (int ob2 = 0; ob2 < Objects.getSize(); ob2++) {
                    sumX2 += X2.get(cr)[ob1][ob2];
                }
            }
            for (int ob1 = 0; ob1 < Objects.getSize(); ob1++) {
                sumColX2 = 0;
                for (int ob2 = 0; ob2 < Objects.getSize(); ob2++) {
                    sumColX2 += X2.get(cr)[ob2][ob1];
                }
                K2.get(cr)[ob1] = (sumColX2 * sumK1) / (sumX2 * sumK1);
                outPut.println("K2," + (ob1 + 1) + " = " + formatter.format(K2.get(cr)[ob1]));
            }
            outPut.println();
        }
    }

    private void theBestObjectCalculate() {
        int res;
        float min;
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            res = 0;
            min = K2.get(cr)[0];
            for (int ob = 0; ob < Objects.getSize() - 1; ob++) {
                if (min > K2.get(cr)[ob + 1]) {
                    min = K2.get(cr)[ob + 1];
                    res = ob + 1;
                }
            }
            theBestObject[cr] = res;

            outPut.println();
            outPut.println("min(Ri) = " + formatter.format(min));
            outPut.println(
                    "НАИБОЛЕЕ ПРЕДПОЧТИТЕЛЬНЫЙ ОБЪЕКТ ПО ПОКАЗАТЕЛЮ "
                            + Criterions.findCriterion(cr).getName().toUpperCase()
                            + ": №"
                            + (theBestObject[cr] + 1)
                            + ", "
                            + Objects.findObj(theBestObject[cr]).getName().toUpperCase());
        }
    }

    public Method4() {
        formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(3);
        pairs = new ArrayList<List<float[][]>>();
        for (int i = 0; i < Criterions.getSize(); i++) {
            pairs.add(new ArrayList<float[][]>());
        }
        X = new ArrayList<float[][]>();
        X2 = new ArrayList<float[][]>();
        K1 = new ArrayList<float[]>();
        K2 = new ArrayList<float[]>();
        theBestObject = new int[Criterions.getSize()];

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
        outPut.println("ОБРАБОТКА ПАРНЫХ СРАВНЕНИЙ ОБЪЕКТОВ");
        outPut.println();
        outPut.println("Условные обозначения:");
        outPut.println("n - количество объектов");
        outPut.println("m - количество экспертов");
        outPut.println("l - количество показателей");
        outPut.println("i, k, t - номер объекта");
        outPut.println("j - номер эксперта");
        outPut.println("h - номер показателя");
        outPut.println();
        pairsCalculate();
        xCalculate();
        k1Calculate();
        x2Calculate();
        k2Calculate();
        theBestObjectCalculate();
        outPut.close();
    }

    public float[][] getX(int cr) {
        return X.get(cr);
    }

    public float[][] getX2(int cr) {
        return X2.get(cr);
    }

    public float[] getK1(int cr) {
        return K1.get(cr);
    }

    public float[] getK2(int cr) {
        return K2.get(cr);
    }

    public int getTheBestObject(int cr) {
        return theBestObject[cr];
    }
}
