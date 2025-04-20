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

public class Method3 {

    public static final float[][] X_TABLE = {
        {6.6f, 5.0f, 3.8f, 0.0039f, 0.00098f, 0.00016f}, // k=1
        {9.2f, 7.4f, 6.0f, 0.103f, 0.051f, 0.02f}, // k=2
        {11.3f, 9.4f, 7.8f, 0.352f, 0.216f, 0.115f}, // ...
        {13.3f, 11.1f, 9.5f, 0.711f, 0.484f, 0.297f},
        {15.1f, 12.8f, 11.1f, 1.15f, 0.831f, 0.554f},
        {16.8f, 14.4f, 12.6f, 1.64f, 1.24f, 0.872f},
        {18.5f, 16.0f, 14.1f, 2.17f, 1.69f, 1.24f},
        {20.1f, 17.5f, 15.5f, 2.73f, 2.18f, 1.65f},
        {21.7f, 19.0f, 16.9f, 3.33f, 2.7f, 2.09f},
        {23.2f, 20.5f, 18.3f, 3.94f, 3.25f, 2.56f},
        {24.7f, 21.9f, 19.7f, 4.57f, 3.82f, 3.05f},
        {26.2f, 23.3f, 21.0f, 5.23f, 4.4f, 3.57f},
        {27.7f, 24.7f, 22.4f, 5.89f, 5.01f, 4.11f},
        {29.1f, 26.1f, 23.7f, 6.57f, 5.63f, 4.66f},
        {30.6f, 27.5f, 25.0f, 7.26f, 6.26f, 5.23f},
        {32.0f, 28.8f, 26.3f, 7.96f, 6.91f, 5.81f},
        {33.4f, 30.2f, 27.6f, 8.67f, 7.56f, 6.41f},
        {34.8f, 31.5f, 28.9f, 9.39f, 8.23f, 7.01f},
        {36.2f, 32.9f, 30.1f, 10.1f, 8.91f, 7.63f},
        {37.6f, 34.2f, 31.4f, 10.9f, 9.59f, 8.26f},
        {38.9f, 35.5f, 32.7f, 11.6f, 10.3f, 8.9f},
        {40.3f, 36.8f, 33.9f, 12.3f, 11.0f, 9.54f},
        {41.6f, 38.1f, 35.2f, 13.1f, 11.7f, 10.2f},
        {43.0f, 39.4f, 36.4f, 13.8f, 12.4f, 10.9f},
        {44.3f, 40.6f, 37.7f, 14.6f, 13.1f, 11.5f},
        {45.6f, 41.9f, 38.9f, 15.4f, 13.8f, 12.2f},
        {47.0f, 43.2f, 40.1f, 16.2f, 14.6f, 12.9f},
        {48.3f, 44.5f, 41.3f, 16.9f, 15.3f, 13.6f},
        {49.6f, 45.7f, 42.6f, 17.7f, 16.0f, 14.3f},
        {50.9f, 47.0f, 43.8f, 18.5f, 16.8f, 15.0f}
    };
    // a=0,01  a=0,025 a=0,05  a=0,95    a=0,975   a=0,99
    public static final float DEF_A = 0.05f;
    public static final int DEF_A_INDEX = 2;
    public static final String FILENAME = "./method3.txt";

    private List<int[][]> rangs;
    private List<int[][]> rangs1;
    private List<int[][]> repeatsNumber;
    private List<float[][]> rangs2; // нормированные ранги

    // dispersion
    private List<float[]> r;
    private float[] rSums;
    private List<float[]> r2;
    private float[] r2Sums;
    private float[] d;
    private float dMax;
    private float[] w1WithoutRangs;
    private float[] w2WithoutRangs;
    private List<int[][]> repeats;
    private List<int[]> t;
    private int[] tSums;
    private float[] wWithRangs;
    private float[] xWithRangs;
    private float a;
    private float x;

    // entropy
    private List<float[][]> pLog;
    private float hMax;
    private float[] h;
    private float[] w;

    private String result;

    private BufferedReader in;
    private PrintWriter outPut;

    private NumberFormat formatter;

    private void rangsCalculate() {
        float[] mas;
        int[] res;
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            rangs.add(new int[Experts.getSize()][Objects.getSize()]);
            rangs1.add(new int[Experts.getSize()][Objects.getSize()]);
            for (int ex = 0; ex < Experts.getSize(); ex++) {
                mas = new float[Objects.getSize()];
                for (int ob = 0; ob < Objects.getSize(); ob++) {
                    mas[ob] =
                            Marks.getMark(
                                    Experts.findExpert(ex),
                                    Objects.findObj(ob),
                                    Criterions.findCriterion(cr));
                }
                res = rangsRow(mas);
                for (int ob = 0; ob < Objects.getSize(); ob++) {
                    rangs.get(cr)[ex][ob] = res[ob];
                }
                res = rangs1Row(mas);
                for (int ob = 0; ob < Objects.getSize(); ob++) {
                    rangs1.get(cr)[ex][ob] = res[ob];
                }
            }
        }
    }

    private int[] rangsRow(float[] mas) {
        int[] res = new int[mas.length];
        List<Float> list = new ArrayList<Float>();
        for (int i = 0; i < mas.length; i++) {
            list.add(mas[i]);
        }
        float min;
        int rang = 1;
        int rang2 = 1;
        while (list.size() > 0) {
            min = list.get(0);
            for (int j = 0; j < list.size() - 1; j++) {
                if (min > list.get(j + 1)) {
                    min = list.get(j + 1);
                }
            }
            for (int j = 0; j < mas.length; j++) {
                if (mas[j] == min) {
                    res[j] = rang;
                    rang2++;
                }
            }
            rang = rang2;
            while (list.contains(min)) {
                list.remove(min);
            }
        }
        return res;
    }

    private int[] rangs1Row(float[] mas) {
        int[] res = new int[mas.length];
        List<Float> list = new ArrayList<Float>();
        for (int i = 0; i < mas.length; i++) {
            list.add(mas[i]);
        }
        float min;
        int rang = 1;
        while (list.size() > 0) {
            min = list.get(0);
            for (int j = 0; j < list.size() - 1; j++) {
                if (min > list.get(j + 1)) {
                    min = list.get(j + 1);
                }
            }
            for (int j = 0; j < mas.length; j++) {
                if (mas[j] == min) {
                    res[j] = rang;
                    rang++;
                }
            }
            while (list.contains(min)) {
                list.remove(min);
            }
        }
        return res;
    }

    private void repeatsNumberCalculate() {
        float[] mas;
        int[] res;
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            repeatsNumber.add(new int[Experts.getSize()][Objects.getSize()]);
            for (int ex = 0; ex < Experts.getSize(); ex++) {
                mas = new float[Objects.getSize()];
                res = new int[Objects.getSize()];
                for (int ob = 0; ob < Objects.getSize(); ob++) {
                    mas[ob] =
                            Marks.getMark(
                                    Experts.findExpert(ex),
                                    Objects.findObj(ob),
                                    Criterions.findCriterion(cr));
                }
                res = repeatsNumberRow(mas);
                for (int ob = 0; ob < Objects.getSize(); ob++) {
                    repeatsNumber.get(cr)[ex][ob] = res[ob];
                }
            }
        }
    }

    private int[] repeatsNumberRow(float[] mas) {
        int[] res = new int[mas.length];
        List<Float> list = new ArrayList<Float>();
        for (int i = 0; i < mas.length; i++) {
            list.add(mas[i]);
        }
        int number;
        float value;
        while (list.size() > 0) {
            value = list.get(0);
            number = 0;
            while (list.contains(value)) {
                list.remove(value);
                number++;
            }
            for (int i = 0; i < mas.length; i++) {
                if (mas[i] == value) {
                    if (number == 1) {
                        res[i] = 0;
                    } else {
                        res[i] = number;
                    }
                }
            }
        }
        return res;
    }

    private void rangs2Calculate() {
        float[] mas;
        float[] res;

        outPut.println();
        String str = "";
        outPut.println(
                "Расчет нормированных рангов, сумм рангов и оценок математического ожидания");
        outPut.println();

        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            rangs2.add(new float[Experts.getSize()][Objects.getSize()]);
            r.add(new float[Objects.getSize()]);
            outPut.println(
                    "Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            for (int ex = 0; ex < Experts.getSize(); ex++) {
                mas = new float[Objects.getSize()];
                for (int ob = 0; ob < Objects.getSize(); ob++) {
                    mas[ob] =
                            Marks.getMark(
                                    Experts.findExpert(ex),
                                    Objects.findObj(ob),
                                    Criterions.findCriterion(cr));
                }
                res = rangs2Row(mas, cr, ex);
                for (int ob = 0; ob < Objects.getSize(); ob++) {
                    rangs2.get(cr)[ex][ob] = res[ob];
                    r.get(cr)[ob] += rangs2.get(cr)[ex][ob];
                    str = "R" + (ob + 1) + "," + (ex + 1) + " = " + formatter.format(res[ob]);
                    outPut.println(str);
                }
            }
            outPut.println();
            outPut.println("Суммы рангов:");
            for (int ob = 0; ob < Objects.getSize(); ob++) {
                outPut.println("R" + (ob + 1) + " = " + formatter.format(r.get(cr)[ob]));
            }
            outPut.println();
            for (int i = 0; i < Objects.getSize(); i++) {
                rSums[cr] += r.get(cr)[i];
            }
            rSums[cr] = rSums[cr] / Objects.getSize();
            outPut.println("Оценка математического ожидания: R = " + formatter.format(rSums[cr]));
            outPut.println();
        }
        outPut.println();
    }

    private float[] rangs2Row(float[] mas, int cr, int ex) {
        float[] res = new float[mas.length];
        float sum;
        for (int i = 0; i < Objects.getSize(); i++) {
            if (repeatsNumber.get(cr)[ex][i] == 0) {
                res[i] = rangs1.get(cr)[ex][i];
            } else {
                sum = rangs1.get(cr)[ex][i];
                for (int j = 0; j < Objects.getSize(); j++) {
                    if (i != j && mas[i] == mas[j]) {
                        sum += rangs1.get(cr)[ex][j];
                    }
                }
                res[i] = sum / repeatsNumber.get(cr)[ex][i];
            }
        }
        return res;
    }

    private void r2AndDCalculate() {
        outPut.println("Расчет дисперсии");
        outPut.println();
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println(
                    "Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            r2.add(new float[Objects.getSize()]);
            for (int ob = 0; ob < Objects.getSize(); ob++) {
                float val = r.get(cr)[ob] - rSums[cr];
                r2.get(cr)[ob] = val * val;
                r2Sums[cr] += r2.get(cr)[ob];
                outPut.println("(R - R" + (ob + 1) + ")^2 = " + formatter.format(r2.get(cr)[ob]));
            }
            outPut.println("S = " + formatter.format(r2Sums[cr]));
            d[cr] = r2Sums[cr] / (Objects.getSize() - 1);
            outPut.println(
                    "Дисперсия: D = "
                            + r2Sums[cr]
                            + " / ("
                            + Objects.getSize()
                            + " - 1) = "
                            + formatter.format(d[cr]));
            outPut.println();
        }
        outPut.println();
    }

    private void dMaxCalculate() {
        dMax =
                (Experts.getSize()
                        * Experts.getSize()
                        * ((float) Math.pow(Objects.getSize(), 3) - Objects.getSize())
                        / (12 * (Objects.getSize() - 1)));
        outPut.println(
                "Максимальная дисперсия: Dmax = "
                        + Experts.getSize()
                        + "^2 * ("
                        + formatter.format(Math.pow(Objects.getSize(), 3))
                        + " - "
                        + Objects.getSize()
                        + ") / (12 * ("
                        + Objects.getSize()
                        + " - 1)) = "
                        + formatter.format(dMax));
        outPut.println();
    }

    private void withoutRangsCalculate() {
        outPut.println();
        r2AndDCalculate();
        dMaxCalculate();
        outPut.println();
        outPut.println(
                "Расчет коэффициента Спирмена без учета связанных рангов ( формула: W = D / Dmax )");
        outPut.println();
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println(
                    "Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            w1WithoutRangs[cr] =
                    (12 * r2Sums[cr])
                            / ((Experts.getSize() * Experts.getSize())
                                    * ((float) Math.pow(Objects.getSize(), 3) - Objects.getSize()));
            w2WithoutRangs[cr] = d[cr] / dMax;
            outPut.println(
                    "W = "
                            + d[cr]
                            + " / "
                            + formatter.format(dMax)
                            + " = "
                            + formatter.format(w2WithoutRangs[cr]));
            outPut.println();
        }
    }

    private void repeatsCalculate() {
        int value, sum;
        String str = "";
        outPut.println();
        outPut.println(
                "Расчет показателя связанных рангов в j-й ранжировке ( формула: Tj = Σk (hk^3 - hk) )");
        outPut.println();
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println(
                    "Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            repeats.add(new int[Experts.getSize()][Objects.getSize()]);
            t.add(new int[Experts.getSize()]);
            for (int ex = 0; ex < Experts.getSize(); ex++) {
                sum = 0;
                str = "T" + (ex + 1) + " = ";
                for (int ob = 0; ob < Objects.getSize(); ob++) {
                    value = numberDispersion(cr, ex, ob);
                    if (value == 1) {
                        repeats.get(cr)[ex][ob] = 0;
                    } else {
                        repeats.get(cr)[ex][ob] = value;
                    }
                    sum += (float) Math.pow(repeats.get(cr)[ex][ob], 3) - repeats.get(cr)[ex][ob];
                    str =
                            str
                                    + "("
                                    + repeats.get(cr)[ex][ob]
                                    + "^3"
                                    + " - "
                                    + repeats.get(cr)[ex][ob]
                                    + ") + ";
                }
                str = str.substring(0, str.length() - 3) + " = " + sum;
                outPut.println(str);
                t.get(cr)[ex] = sum;
                tSums[cr] += sum;
            }
            outPut.println();
        }
    }

    private int numberDispersion(int cr, int ex, int ob) {
        int number = 0;
        for (int ob2 = 0; ob2 < Objects.getSize(); ob2++) {
            if (rangs.get(cr)[ex][ob2] == ob + 1) {
                number++;
            }
        }
        return number;
    }

    private void withRangsCalculate() {
        repeatsCalculate();
        outPut.println();
        outPut.println(
                "Расчет коэффициента Спирмена (W) и критерия согласования Пирсона (X) с учетом связанных рангов");
        outPut.println();
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println(
                    "Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            wWithRangs[cr] =
                    (12.0f * r2Sums[cr])
                            / (Experts.getSize()
                                            * Experts.getSize()
                                            * ((float) Math.pow(Objects.getSize(), 3)
                                                    - Objects.getSize())
                                    - Experts.getSize() * tSums[cr]);
            xWithRangs[cr] =
                    (12.0f * r2Sums[cr])
                            / ((Experts.getSize() * Objects.getSize() * (Objects.getSize() + 1.0f))
                                    - ((1.0f / (Objects.getSize() - 1.0f)) * tSums[cr]));
            outPut.println("W = " + formatter.format(wWithRangs[cr]));
            outPut.println("X = " + formatter.format(xWithRangs[cr]));
            outPut.println();
        }
    }

    private void dispersionCalculate() {
        withoutRangsCalculate();
        withRangsCalculate();
    }

    private void hMaxCalculate() {
        hMax = (float) (Objects.getSize() * Math.log10(Objects.getSize()));
        outPut.println();
        outPut.println(
                "Максимальная энтропия: Hmax = n * log10(n) = "
                        + Objects.getSize()
                        + " * "
                        + formatter.format(Math.log10(Objects.getSize()))
                        + ") = "
                        + formatter.format(hMax));
    }

    private int numberEntropy(int cr, int ob1, int ob2) {
        int number = 0;
        for (int ex = 0; ex < Experts.getSize(); ex++) {
            if (rangs.get(cr)[ex][ob1] == ob2 + 1) {
                number++;
            }
        }
        return number;
    }

    private void entropyCalculate() {
        outPut.println();
        outPut.println("Расчет энтропии (H) и коэффициентов Кендалла (W)");
        hMaxCalculate();
        float p;
        outPut.println();
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println(
                    "Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            outPut.println();
            outPut.println(
                    "Расчет оценок вероятностей j-го ранга, присваиваемого i-му объекту ( формула: Pij = Mij / m )");
            pLog.add(new float[Objects.getSize()][Objects.getSize()]);
            for (int ob1 = 0; ob1 < Objects.getSize(); ob1++) {
                for (int ob2 = 0; ob2 < Objects.getSize(); ob2++) {
                    p = numberEntropy(cr, ob1, ob2) / (float) Experts.getSize();
                    outPut.println(
                            "P"
                                    + (ob1 + 1)
                                    + ","
                                    + (ob2 + 1)
                                    + " = "
                                    + numberEntropy(cr, ob1, ob2)
                                    + " / "
                                    + Experts.getSize()
                                    + " = "
                                    + formatter.format(p));
                    if (p != 0) {
                        pLog.get(cr)[ob1][ob2] = p * (float) Math.log10(p);
                        h[cr] += pLog.get(cr)[ob1][ob2];
                    } else {
                        pLog.get(cr)[ob1][ob2] = 0.0f;
                    }
                }
            }
            h[cr] *= -1.0f;
            outPut.println("H = - Σi (Σj (Pij * log10(Pij))) = " + formatter.format(h[cr]));
            w[cr] = 1.0f - (h[cr] / hMax);
            outPut.println("W = 1 - H / Hmax = " + formatter.format(w[cr]));
            outPut.println();
        }
    }

    public void resultCalculate() {
        result =
                "Анализ результатов расчетов дисперсионных коэффициентов конкордации\n\n"
                        + "Квантиль распределения хи-квадрат для "
                        + (Objects.getSize() - 1)
                        + " степеней свободы \nи уровне значимости а = "
                        + formatter.format(a)
                        + " равен "
                        + formatter.format(x)
                        + "\n";
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            result += "\nКритерий " + Criterions.findCriterion(cr).getName().toUpperCase();
            result +=
                    "\nРанжировки экспертов согласованны на " + (int) (wWithRangs[cr] * 100) + "% ";
            if (wWithRangs[cr] > 0.3f) {
                if (wWithRangs[cr] < 0.7f) {
                    result += "\n(наблюдается средняя согласованность)\n";
                } else {
                    result += "\n(наблюдается высокая согласованность)\n";
                }
            } else {
                result += "\n(наблюдается слабая согласованность)\n";
            }
            if (x < xWithRangs[cr]) {
                result +=
                        formatter.format(x)
                                + " < "
                                + formatter.format(xWithRangs[cr])
                                + "\nW - величина не случайная. \nГипотеза о согласии экспертов принимается.\n";
            } else {
                result +=
                        formatter.format(x)
                                + " > "
                                + formatter.format(xWithRangs[cr])
                                + "\nW - величина случайная. \nГипотеза о согласии экспертов не принимается.\n";
            }
        }
        result += "\n\nАнализ результатов расчетов энтропийных коэффициентов конкордации\n";
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            result += "\nКритерий " + Criterions.findCriterion(cr).getName().toUpperCase();
            result += "\nРанжировки экспертов согласованны на " + (int) (w[cr] * 100) + "%.";
            if (w[cr] > 0.3f) {
                if (w[cr] < 0.7f) {
                    result += "\n(наблюдается средняя согласованность)\n";
                } else {
                    result += "\n(наблюдается высокая согласованность)\n";
                }
            } else {
                result += "\n(наблюдается слабая согласованность)\n";
            }
            if (isTwoGroupsOfExperts(cr)) {
                result += "Эксперты разделены на 2 группы с противоположными точками \nзрения.\n";
            } else {
                result +=
                        "Разделение экспертов на 2 группы с противоположными точками \nзрения не выявлено.\n";
            }
        }
        outPut.println(result);
    }

    private boolean isTwoGroupsOfExperts(int cr) {
        if (wWithRangs[cr] < 0.1f && w[cr] > 0.7f) {
            return true;
        }
        return false;
    }

    public Method3() {
        formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(3);
        rangs = new ArrayList<int[][]>();
        rangs1 = new ArrayList<int[][]>();
        repeatsNumber = new ArrayList<int[][]>();
        rangs2 = new ArrayList<float[][]>();

        // dispersion
        r = new ArrayList<float[]>();
        r2 = new ArrayList<float[]>();
        rSums = new float[Criterions.getSize()];
        r2Sums = new float[Criterions.getSize()];
        d = new float[Criterions.getSize()];
        w1WithoutRangs = new float[Criterions.getSize()];
        w2WithoutRangs = new float[Criterions.getSize()];
        repeats = new ArrayList<int[][]>();
        t = new ArrayList<int[]>();
        tSums = new int[Criterions.getSize()];
        wWithRangs = new float[Criterions.getSize()];
        xWithRangs = new float[Criterions.getSize()];

        // entropy
        pLog = new ArrayList<float[][]>();
        h = new float[Criterions.getSize()];
        w = new float[Criterions.getSize()];

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

    public void calculate(int aIndex, float a) {
        x = X_TABLE[Objects.getSize() - 2][aIndex];
        this.a = a;
        outPut.println("ОЦЕНКА СОГЛАСОВАННОСТИ МНЕНИЙ ЭКСПЕРТОВ");
        outPut.println();
        outPut.println("Условные обозначения:");
        outPut.println("n - количество объектов");
        outPut.println("m - количество экспертов");
        outPut.println("l - количество показателей");
        outPut.println("i - номер объекта");
        outPut.println("j - номер эксперта");
        outPut.println("h - номер показателя");
        outPut.println();
        rangsCalculate();
        repeatsNumberCalculate();
        rangs2Calculate();
        dispersionCalculate();
        entropyCalculate();
        resultCalculate();
        outPut.close();
    }

    public float getD(int cr) {
        return d[cr];
    }

    public float getW1WithoutRangs(int cr) {
        return w1WithoutRangs[cr];
    }

    public float getWWithRangs(int cr) {
        return wWithRangs[cr];
    }

    public float getXWithRangs(int cr) {
        return xWithRangs[cr];
    }

    public float getH(int cr) {
        return h[cr];
    }

    public float getW(int cr) {
        return w[cr];
    }

    public String getResult() {
        return result;
    }
}
