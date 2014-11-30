package com.github.expertsystem.model.calculate;

import com.github.expertsystem.BufferedInputFile;
import com.github.expertsystem.model.data.Criterions;
import com.github.expertsystem.model.data.Experts;
import com.github.expertsystem.model.data.Marks;
import com.github.expertsystem.model.data.Objects;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Method2a {

    public static final String FILENAME = "./method2a.txt";

    private List<int[][]> rangs1;
    private List<int[][]> repeatsNumber;
    private List<float[][]> rangs2; // normalized ranks
    private List<float[]> rangs2Sum;
    private int[] theBestObject;

    private BufferedReader in;
    private PrintWriter outPut;

    private NumberFormat formatter;

    private void rangs1Calculate() {
        float[] mas;
        int[] res;
        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            rangs1.add(new int[Experts.getSize()][Objects.getSize()]);
            for(int ex = 0; ex < Experts.getSize(); ex++) {
                mas = new float[Objects.getSize()];
                for(int ob = 0; ob < Objects.getSize(); ob++) {
                    mas[ob] = Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr));
                }
                res = rangs1Row(mas);
                for (int ob = 0; ob < Objects.getSize(); ob++) {
                    rangs1.get(cr)[ex][ob] = res[ob];
                }
            }
        }
    }

    private int[] rangs1Row(float[] mas) {
        int[] res = new int[mas.length];
        List<Float> list = new ArrayList<Float>();
        for(int i = 0; i < mas.length; i++) {
            list.add(mas[i]);
        }
        float min;
        int rang = 1;
        while(list.size() > 0) {
            min = list.get(0);
            for(int j = 0; j < list.size()-1; j++) {
                if(min > list.get(j+1)) {
                    min = list.get(j+1);
                }
            }
            for(int j = 0; j < mas.length; j++) {
                if(mas[j] == min) {
                    res[j] = rang;
                    rang++;
                }
            }
            while(list.contains(min)) {
                list.remove(min);
            }
        }
        return res;
    }

    private void repeatsNumberCalculate() {
        float[] mas;
        int[] res;
        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            repeatsNumber.add(new int[Experts.getSize()][Objects.getSize()]);
            for(int ex = 0; ex < Experts.getSize(); ex++) {
                mas = new float[Objects.getSize()];
                for(int ob = 0; ob < Objects.getSize(); ob++) {
                    mas[ob] = Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr));
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
        for(int i = 0; i < mas.length; i++) {
            list.add(mas[i]);
        }
        int number;
        float value;
        while (list.size() > 0) {
            value = list.get(0);
            number = 0;
            while(list.contains(value)) {
                list.remove(value);
                number++;
            }
            for (int i = 0; i < mas.length; i++) {
                if(mas[i] == value) {
                    if(number == 1) {
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
        outPut.println("Расчет нормированных рангов с учетом коэффициентов компетентности " +
                "и коэффициентов весов показателей ( формула: Rij = rang * Kj * Qhj )");
        outPut.println();

        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            rangs2.add(new float[Experts.getSize()][Objects.getSize()]);
            outPut.println("Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            for(int ex = 0; ex < Experts.getSize(); ex++) {
                mas = new float[Objects.getSize()];
                for(int ob = 0; ob < Objects.getSize(); ob++) {
                    mas[ob] = Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr));
                }
                res = rangs2Row(mas, cr, ex);
                for (int ob = 0; ob < Objects.getSize(); ob++) {
                    rangs2.get(cr)[ex][ob] = res[ob] * Experts.findExpert(ex).getCompetence() *
                            Criterions.findCriterion(cr).getWeight(ex);
                    str = "R" + (ob + 1) + "," + (ex + 1) + " = " + formatter.format(res[ob]) + " * " +
                            formatter.format(Experts.findExpert(ex).getCompetence()) + " * " +
                            formatter.format(Criterions.findCriterion(cr).getWeight(ex)) + " = " +
                            formatter.format(rangs2.get(cr)[ex][ob]);
                    outPut.println(str);
                }
            }
            outPut.println();
        }
        outPut.println();
    }

    private float[] rangs2Row(float[] mas, int cr, int ex) {
        float[] res = new float[mas.length];
        float sum;
        for(int i = 0; i < Objects.getSize(); i++) {
            if(repeatsNumber.get(cr)[ex][i] == 0) {
                res[i] = rangs1.get(cr)[ex][i];
            } else {
                sum = rangs1.get(cr)[ex][i];
                for(int j = 0; j < Objects.getSize(); j++) {
                    if(i != j && mas[i] == mas[j]) {
                        sum += rangs1.get(cr)[ex][j];
                    }
                }
                res[i] = sum / repeatsNumber.get(cr)[ex][i];
            }
        }
        return res;
    }

    private void rangs2SumCalculate() {
        float res;
        String str = "";
        outPut.println("Расчет сумм нормированных рангов ( формула: Ri = Σj (Rij) )");
        outPut.println();
        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            rangs2Sum.add(new float[Objects.getSize()]);
            outPut.println("Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            for(int ob = 0; ob < Objects.getSize(); ob++) {
                res = 0.0f;
                str = "R" + (ob + 1) + " = ";
                for(int ex = 0; ex < Experts.getSize(); ex++) {
                    res += rangs2.get(cr)[ex][ob];
                    str = str + formatter.format(rangs2.get(cr)[ex][ob]) + " + ";
                }
                str = str.substring(0, str.length() - 3) + " = " + formatter.format(res);
                outPut.println(str);
                rangs2Sum.get(cr)[ob] = res;
            }
            outPut.println();
        }
    }

    private void theBestObjectCalculate() {
        int res;
        float min;
        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            res = 0;
            min = rangs2Sum.get(cr)[0];
            for(int ob = 0; ob < Objects.getSize()-1; ob++) {
                if(min > rangs2Sum.get(cr)[ob+1])
                {
                    min = rangs2Sum.get(cr)[ob+1];
                    res = ob+1;
                }
            }
            theBestObject[cr] = res;

            outPut.println();
            outPut.println("min(Ri) = " + formatter.format(min));
            outPut.println("НАИБОЛЕЕ ПРЕДПОЧТИТЕЛЬНЫЙ ОБЪЕКТ ПО ПОКАЗАТЕЛЮ " +
                    Criterions.findCriterion(cr).getName().toUpperCase() + ": №" + (theBestObject[cr] + 1) +
                    ", " + Objects.findObj(theBestObject[cr]).getName().toUpperCase());
        }
    }

    public Method2a() {
        formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(3);
        rangs1 = new ArrayList<int[][]>();
        repeatsNumber = new ArrayList<int[][]>();
        rangs2 = new ArrayList<float[][]>();
        rangs2Sum = new ArrayList<float[]>();
        theBestObject = new int[Criterions.getSize()];

        try{
            in = new BufferedReader(
                    new StringReader(
                            new BufferedInputFile().read(FILENAME)
                    )
            );
        } catch (IOException e) {
        }
        try {
            outPut = new PrintWriter(FILENAME);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error with file " + FILENAME + "!", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void calculate() {
        outPut.println("ОБОБЩЕННАЯ РАНЖИРОВКА НА ОСНОВЕ РАНГОВОЙ КОРРЕЛЯЦИИ");
        outPut.println();
        outPut.println("Условные обозначения:");
        outPut.println("n - количество объектов");
        outPut.println("m - количество экспертов");
        outPut.println("l - количество показателей");
        outPut.println("i - номер объекта");
        outPut.println("j - номер эксперта");
        outPut.println("h - номер показателя");
        outPut.println();
        rangs1Calculate();
        repeatsNumberCalculate();
        rangs2Calculate();
        rangs2SumCalculate();
        theBestObjectCalculate();
        outPut.close();
    }

    public float[][] getRangs(int cr) {
        return rangs2.get(cr);
    }

    public float[] getRangSums(int cr) {
        return rangs2Sum.get(cr);
    }

    public int getTheBestObject(int cr) {
        return theBestObject[cr];
    }

}