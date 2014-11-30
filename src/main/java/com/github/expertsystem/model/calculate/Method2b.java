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

public class Method2b {

    public static final String FILENAME = "./method2b.txt";

    private List<List<int[][]>> pairs;
    private List<int[][]> pairSums;
    private List<int[]> sums;
    private List<int[]> rangs;
    private int[] theBestObject;

    private BufferedReader in;
    private PrintWriter outPut;

    private NumberFormat formatter;

    private void pairsCalculate() {
        String str = "";
        outPut.println();
        outPut.println("Расчет матриц парных сравнений ( если Ok > Ot => Akt = 1; если Ok < Ot => Akt = -1; если Ok = Ot => Akt = 0 )");
        outPut.println();
        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println("Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            outPut.println();
            for(int ex = 0; ex < Experts.getSize(); ex++) {
                pairs.get(cr).add(new int[Objects.getSize()][Objects.getSize()]);
                outPut.println("Эксперт " + Experts.findExpert(ex).getName().toUpperCase());
                for(int ob1 = 0; ob1 < Objects.getSize(); ob1++) {
                    for(int ob2 = 0; ob2 < Objects.getSize(); ob2++) {
                        if(Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob1), Criterions.findCriterion(cr)) ==
                                Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob2), Criterions.findCriterion(cr))) {
                            pairs.get(cr).get(ex)[ob1][ob2] = 0;
                            str = "O" + (ob1 + 1) + " = O" + (ob2 + 1) + " : ";
                        } else if(Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob1), Criterions.findCriterion(cr)) <
                                Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob2), Criterions.findCriterion(cr))) {
                            pairs.get(cr).get(ex)[ob1][ob2] = 1;
                            str = "O" + (ob1 + 1) + " > O" + (ob2 + 1) + " : ";
                        } else {
                            pairs.get(cr).get(ex)[ob1][ob2] = -1;
                            str = "O" + (ob1 + 1) + " < O" + (ob2 + 1) + " : ";
                        }
                        str = str + "A" + (ob1 + 1) + "," + (ob2 + 1) + " = " + formatter.format(pairs.get(cr).get(ex)[ob1][ob2]);
                        outPut.println(str);
                    }
                }
                outPut.println();
            }
            outPut.println();
        }
    }

    private void sumsCalculate() {
        int sum, sum2;
        String str = "";
        outPut.println("Расчет суммарных матриц парных сравнений ( формула: Аkt = Σj (Ajkt) )");
        outPut.println();
        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println("Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            pairSums.add(new int[Objects.getSize()][Objects.getSize()]);
            sums.add(new int[Objects.getSize()]);
            for(int ob1 = 0; ob1 < Objects.getSize(); ob1++) {
                sum2 = 0;
                for(int ob2 = 0; ob2 < Objects.getSize(); ob2++) {
                    sum = 0;
                    str = "A" + (ob1 + 1) + "," + (ob2 + 1) + " = ";
                    for(int ex = 0; ex < Experts.getSize(); ex++) {
                        sum += pairs.get(cr).get(ex)[ob1][ob2];
                        str = str + formatter.format(pairs.get(cr).get(ex)[ob1][ob2]) + " + ";

                    }
                    str = str.substring(0, str.length() - 3) + " = " + formatter.format(sum);
                    outPut.println(str);
                    pairSums.get(cr)[ob1][ob2] = sum;
                    sum2 += sum;
                }
                sums.get(cr)[ob1] = sum2;
                outPut.println("A" + (ob1 + 1) + " = " + formatter.format(sum2));
            }
            outPut.println();
        }
        outPut.println();
    }

    private void rangsCalculate() {
        outPut.println("Расчет рангов");
        outPut.println();
        List<Integer> list;
        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println("Расчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            rangs.add(new int[Objects.getSize()]);
            list = new ArrayList<Integer>();
            for(int i = 0; i < Objects.getSize(); i++) {
                list.add(sums.get(cr)[i]);
            }
            int max;
            int rang = 1;
            int rang2 = 1;
            while(list.size() > 0) {
                max = list.get(0);
                for(int j = 0; j < list.size()-1; j++) {
                    if(max < list.get(j+1)) {
                        max = list.get(j+1);
                    }
                }
                for(int j = 0; j < Objects.getSize(); j++) {
                    if(sums.get(cr)[j] == max) {
                        rangs.get(cr)[j] = rang;
                        rang2++;
                    }
                }
                rang = rang2;
                while(list.contains(max)) {
                    list.remove((Object)max);
                }
            }
            for(int j = 0; j < Objects.getSize(); j++) {
                outPut.println("R" + (j + 1) + " = " + formatter.format(rangs.get(cr)[j]));
            }
            outPut.println();
        }
    }

    private void theBestObjectCalculate() {
        int res;
        float min;
        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            res = 0;
            min = rangs.get(cr)[0];
            for(int ob = 0; ob < Objects.getSize()-1; ob++) {
                if(min > rangs.get(cr)[ob+1])
                {
                    min = rangs.get(cr)[ob+1];
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

    public Method2b() {
        formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(3);
        pairs = new ArrayList<List<int[][]>>();
        for(int i = 0; i < Criterions.getSize(); i++) {
            pairs.add(new ArrayList<int[][]>());
        }
        pairSums = new ArrayList<int[][]>();
        sums = new ArrayList<int[]>();
        rangs = new ArrayList<int[]>();
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
        outPut.println("ОБОБЩЕННАЯ РАНЖИРОВКА НА ОСНОВЕ ПАРНЫХ СРАВНЕНИЙ");
        outPut.println();
        outPut.println("Условные обозначения:");
        outPut.println("n - количество объектов");
        outPut.println("m - количество экспертов");
        outPut.println("l - количество показателей");
        outPut.println("i,k,t - номера объектов");
        outPut.println("j - номер эксперта");
        outPut.println("h - номер показателя");
        outPut.println();
        pairsCalculate();
        sumsCalculate();
        rangsCalculate();
        theBestObjectCalculate();
        outPut.close();
    }

    public int[][] getPairSums(int cr) {
        return pairSums.get(cr);
    }

    public int[] getSums(int cr) {
        return sums.get(cr);
    }

    public int[] getRangs(int cr) {
        return rangs.get(cr);
    }

    public int getTheBestObject(int cr) {
        return theBestObject[cr];
    }
}
