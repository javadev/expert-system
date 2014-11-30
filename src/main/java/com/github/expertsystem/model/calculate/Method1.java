package com.github.expertsystem.model.calculate;

import com.github.expertsystem.BufferedInputFile;
import com.github.expertsystem.model.data.Criterions;
import com.github.expertsystem.model.data.Experts;
import com.github.expertsystem.model.data.Marks;
import com.github.expertsystem.model.data.Objects;

import javax.swing.*;
import java.io.*;
import java.text.NumberFormat;

public class Method1 {

    public static final String FILENAME = "./method1.txt";

    private float[] midCriterionWeights;
    private float[][] midMarks;
    private float[] midMarksSum;
    private float[][] competence;
    private float[][] newMidMarks;
    private float[] newMidMarksSum;
    private float[] newMidCriterionWeights;
    private float[] newCompetence;
    private int theBestObject;

    private BufferedReader in;
    private PrintWriter outPut;

    private NumberFormat formatter;

    private void midCriterionWeightsCalculate() {
        float res;
        String str = "";
        outPut.println();
        outPut.println("Расчет коэффициентов весов показателей сравнения объектов ( формула: Qh = Σj (Qhj * Kj) )");
        outPut.println();
        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            res = 0.0f;
            str = "Q" + (cr + 1) + " = ";
            for(int ex = 0; ex < Experts.getSize(); ex++) {
                res += Experts.findExpert(ex).getCompetence() * Criterions.findCriterion(cr).getWeight(ex);
                str = str + "(" + formatter.format(Experts.findExpert(ex).getCompetence()) + " * " +
                        formatter.format(Criterions.findCriterion(cr).getWeight(ex)) + ") + ";
            }
            str = str.substring(0, str.length() - 3) + " = " + formatter.format(res);
            outPut.println(str);
            midCriterionWeights[cr] = res;
        }
        outPut.println();
    }

    private void midMarksCalculate() {
        float res;
        String str = "";
        outPut.println();
        outPut.println("Расчет средних значений оценок объектов ( формула: Xhi = Σj (Xhij * Qh * Kj) )");
        outPut.println();
        for(int ob = 0; ob < Objects.getSize(); ob++) {
            for(int cr = 0; cr < Criterions.getSize(); cr++) {
                res = 0.0f;
                str = "X" + (ob + 1) + "," + (cr + 1) + " = ";
                for(int ex = 0; ex < Experts.getSize(); ex++) {
                    res += (Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr)) *
                            Experts.findExpert(ex).getCompetence() * midCriterionWeights[cr]);
                    str = str + "(" + formatter.format(Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr))) + " * " +
                            formatter.format(Experts.findExpert(ex).getCompetence()) + " * " + formatter.format(midCriterionWeights[cr]) + ") + ";
                }
                str = str.substring(0, str.length() - 3) + " = " + formatter.format(res);
                outPut.println(str);
                midMarks[ob][cr] = res;
            }
        }
        outPut.println();
    }

    private void midMarksSumCalculate() {
        float res;
        String str = "";
        outPut.println("( формула: Xi = Σh (Xhi) )");
        outPut.println();
        for(int ob = 0; ob < Objects.getSize(); ob++) {
            res = 0.0f;
            str = "X" + (ob + 1) + " = ";
            for(int cr = 0; cr < Criterions.getSize(); cr++) {
                res += midMarks[ob][cr];
                str = str + formatter.format(midMarks[ob][cr]) + " + ";
            }
            str = str.substring(0, str.length() - 3) + " = " + formatter.format(res);
            outPut.println(str);
            midMarksSum[ob] = res;
        }
        outPut.println();
    }

    private void competenceCalculate() {
        outPut.println();
        outPut.println("Расчет коэффициентов компетентности экспертов");
        outPut.println();
        float t[] = new float[Experts.getSize()];
        float groupMarks[] = new float[Objects.getSize()];
        float competence1[] = new float[Experts.getSize()];
        float res, res2, sum, sum2;
        String str1, str2, str3;

        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            outPut.println("" + (cr + 1) + ") Рассчет по показателю " + Criterions.findCriterion(cr).getName().toUpperCase());
            outPut.println();

            // first approximation

            outPut.println("Первое приближение");
            outPut.println();
            outPut.println("Начальные значения коэффициентов компетентности экспертов ( формула: Koj = 1 / m )");
            outPut.println();

            for(int ex = 0; ex < Experts.getSize(); ex++) {
                t[ex] = 1.0f/Experts.getSize();
                outPut.println("Ko" + (ex + 1) + " = 1 / " + Experts.getSize() + " = " + formatter.format(t[ex]));
            }

            outPut.println();
            outPut.println("Расчет групповых оценок объектов ( формула: X1i = Σj (Xij / m) ) и величины λ1,i ( формула: X1i = Σj (Xij)");
            outPut.println();
            str3 = "λ1 = ";

            sum2 = 0.0f;
            for(int ob = 0; ob < Objects.getSize(); ob++) {
                res = 0.0f;
                sum = 0.0f;

                str1 = "X1," + (ob + 1) + " = ";
                str2 = "λ1," + (ob + 1) + " = (";

                for(int ex = 0; ex < Experts.getSize(); ex++) {
                    res += Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr)) *
                            t[ex];
                    sum += Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr));

                    str1 = str1 + "(" + formatter.format(Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr))) + " * " +
                            formatter.format(t[ex]) + ") + ";
                    str2 = str2 + formatter.format(Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr)))  + " + ";
                }

                str1 = str1.substring(0, str1.length() - 3) + " = " + formatter.format(res);
                outPut.println(str1);

                groupMarks[ob] = res;
                res2 = res * sum;

                str2 = str2.substring(0, str2.length() - 3) + ") * " + formatter.format(res) + " = " + formatter.format(res2);
                outPut.println(str2);
                outPut.println();

                sum2 += res2;

                str3 = str3 + res2  + " + ";
            }

            str3 = str3.substring(0, str3.length() - 3) + " = " + formatter.format(sum2);
            outPut.println(str3);
            outPut.println();
            outPut.println("Расчет коэффициентов компетентности ( формула: K1,j = (Σi (Xij * X1i)) / λ1 )");
            outPut.println();

            for(int ex = 0; ex < Experts.getSize(); ex++) {
                res = 0.0f;
                str1 = "K1," + (ex + 1) + " = ";
                for(int ob = 0; ob < Objects.getSize(); ob++) {
                    res += Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr)) *
                            groupMarks[ob];
                    str1 = str1 + "(" + formatter.format(Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr)))  + " * " +
                            formatter.format(groupMarks[ob]) + ") + ";
                }
                competence1[ex] = res/sum2;

                str1 = str1.substring(0, str1.length() - 3) + " = " + formatter.format(competence1[ex]);
                outPut.println(str1);
            }

            outPut.println();
            outPut.println("Второе приближение");
            outPut.println();
            outPut.println("Начальные значения коэффициентов компетентности экспертов ( формула: Kj = Σi (X2i) / λ2 )");
            outPut.println();

            // second approximation

            for(int ex = 0; ex < Experts.getSize(); ex++) {
                t[ex] = competence1[ex];
                outPut.println("Ko" + (ex + 1) + " = " + formatter.format(t[ex]));
            }
            sum2 = 0.0f;

            outPut.println();
            outPut.println("Расчет групповых оценок объектов ( формула: X2i = Σj (Xij / m) ) и величины λ2i ( формула: X2i = Σj (Xij)");
            outPut.println();
            str3 = "λ2 = ";

            for(int ob = 0; ob < Objects.getSize(); ob++) {
                res = 0.0f;
                sum = 0.0f;

                str1 = "X2," + (ob + 1) + " = ";
                str2 = "λ2," + (ob + 1) + " = ";

                for(int ex = 0; ex < Experts.getSize(); ex++) {
                    res += Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr)) *
                            t[ex];
                    sum += Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr));

                    str1 = str1 + "(" + formatter.format(Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr))) + " * " +
                            formatter.format(t[ex]) + ") + ";
                    str2 = str2 + formatter.format(Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr)))  + " + ";
                }

                str1 = str1.substring(0, str1.length() - 3) + " = " + formatter.format(res);
                outPut.println(str1);

                groupMarks[ob] = res;
                res2 = res * sum;

                str2 = str2.substring(0, str2.length() - 3) + " = " + formatter.format(res2);
                outPut.println(str2);
                outPut.println();

                sum2 += res2;

                str3 = str3 + res2  + " + ";
            }

            str3 = str3.substring(0, str3.length() - 3) + " = " + formatter.format(sum2);
            outPut.println(str3);
            outPut.println();
            outPut.println("Расчет коэффициентов компетентности ( формула: K2j = (Σi (Xij * X2i)) / λ2 )");
            outPut.println();

            for(int ex = 0; ex < Experts.getSize(); ex++) {
                res = 0.0f;
                str1 = "K2," + (ex + 1) + " = (";
                for(int ob = 0; ob < Objects.getSize(); ob++) {
                    res += Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr)) *
                            groupMarks[ob];
                    str1 = str1 + "(" + formatter.format(Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr)))  + " * " +
                            formatter.format(groupMarks[ob]) + ") + ";
                }
                competence[cr][ex] = res/sum2;

                str1 = str1.substring(0, str1.length() - 3) + ") / " + formatter.format(sum2) + " = " + formatter.format(competence1[ex]);
                outPut.println(str1);
            }
            outPut.println();
        }
    }

    private void newCompetenceCalculate() {
        outPut.println();
        outPut.println("Расчет новых общих коэффициентов компетентности экспертов на основе выполненных " +
                "расчетов ( формула: Kj = (Σh (K1hj) / l) )");
        outPut.println();
        float res;
        String str;
        for(int ex = 0; ex < Experts.getSize(); ex++) {
            res = 0.0f;
            str = "K" + (ex + 1) + " = (";
            for(int cr = 0; cr < Criterions.getSize(); cr++) {
                res += competence[cr][ex];
                str = str + formatter.format(competence[cr][ex]) + " + ";
            }
            newCompetence[ex] = res/4;
            str = str.substring(0, str.length() - 3) + ") / 4 = " + formatter.format(newCompetence[ex]);
            outPut.println(str);
        }
        outPut.println();
    }

    private void newMidCriterionWeightsCalculate() {
        outPut.println();
        outPut.println("Расчет новых средних значений коэффициентов весов показателей сравнения объектов " +
                "на основе выполненных расчетов ( формула: Qh = Σj (Qhj * Kj) )");
        outPut.println();
        float res;
        String str;
        for (int cr = 0; cr < Criterions.getSize(); cr++) {
            res = 0.0f;
            str = "Q" + (cr + 1) + " = ";
            for(int ex = 0; ex < Experts.getSize(); ex++) {
                res += competence[cr][ex] * Criterions.findCriterion(cr).getWeight(ex);
                str = str + "(" + formatter.format(Criterions.findCriterion(cr).getWeight(ex)) + " * " + formatter.format(competence[cr][ex]) + ") + ";
            }
            newMidCriterionWeights[cr] = res;
            str = str.substring(0, str.length() - 3) + " = " + formatter.format(res);
            outPut.println(str);
        }
        outPut.println();
    }

    private void newMidMarksCalculate() {
        float res;
        String str = "";
        outPut.println();
        outPut.println("Расчет новых средних значений оценок объектов на основе выполненных расчетов " +
                "( формула: Xhi = Σj (Xhij * Qh * Kj) )");
        outPut.println();
        for(int ob = 0; ob < Objects.getSize(); ob++) {
            for(int cr = 0; cr < Criterions.getSize(); cr++) {
                res = 0.0f;
                str = "X" + (ob + 1) + "," + (cr + 1) + " = ";
                for(int ex = 0; ex < Experts.getSize(); ex++) {
                    res += Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr)) *
                           newCompetence[ex] * newMidCriterionWeights[cr];
                    str = str + "(" + formatter.format(Marks.getMark(Experts.findExpert(ex), Objects.findObj(ob), Criterions.findCriterion(cr))) + " * " +
                            formatter.format(newCompetence[ex]) + " * " + formatter.format(midCriterionWeights[cr]) + ") + ";
                }
                str = str.substring(0, str.length() - 3) + " = " + formatter.format(res);
                outPut.println(str);
                newMidMarks[ob][cr] = res;
            }
        }
        outPut.println();
    }

    private void newMidMarksSumCalculate() {
        float res;
        String str = "";
        outPut.println("( формула: Xi = Σh (Xhi) )");
        outPut.println();
        for(int ob = 0; ob < Objects.getSize(); ob++) {
            res = 0.0f;
            str = "X" + (ob + 1) + " = ";
            for(int cr = 0; cr < Criterions.getSize(); cr++) {
                res += newMidMarks[ob][cr];
                str = str + formatter.format(newMidMarks[ob][cr]) + " + ";
            }
            str = str.substring(0, str.length() - 3) + " = " + formatter.format(res);
            outPut.println(str);
            newMidMarksSum[ob] = res;
        }
        outPut.println();
    }

    private void theBestObjectCalculate() {
        int res = 0;
        float max = newMidMarksSum[0];
        for(int ob = 0; ob < Objects.getSize()-1; ob++) {
            if(max < newMidMarksSum[ob+1])
            {
                max = newMidMarksSum[ob+1];
                res = ob+1;
            }
        }
        theBestObject = res;
        outPut.println();
        outPut.println("max(Xi) = " + formatter.format(max));
        outPut.println("НАИБОЛЕЕ ПРЕДПОЧТИТЕЛЬНЫЙ ОБЪЕКТ: №" + (theBestObject + 1) + ", " +
                Objects.findObj(theBestObject).getName().toUpperCase());
    }

    public Method1() {
        formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(3);
        midCriterionWeights = new float[Criterions.getSize()];
        midMarks = new float[Objects.getSize()][Criterions.getSize()];
        midMarksSum = new float[Objects.getSize()];
        competence = new float[Criterions.getSize()][Experts.getSize()];
        newMidMarks = new float[Objects.getSize()][Criterions.getSize()];
        newMidMarksSum = new float[Objects.getSize()];
        newMidCriterionWeights = new float[Criterions.getSize()];
        newCompetence = new float[Experts.getSize()];

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
        outPut.println("СОСТАВЛЕНИЕ ГРУППОВЫХ ОЦЕНОК ПО МЕТОДУ НЕПОСРЕДСТВЕННОЙ ОЦЕНКИ");
        outPut.println();
        outPut.println("Условные обозначения:");
        outPut.println("n - количество объектов");
        outPut.println("m - количество экспертов");
        outPut.println("l - количество показателей");
        outPut.println("i - номер объекта");
        outPut.println("j - номер эксперта");
        outPut.println("h - номер показателя");
        outPut.println();
        midCriterionWeightsCalculate();
        midMarksCalculate();
        midMarksSumCalculate();
        competenceCalculate();
        newCompetenceCalculate();
        newMidCriterionWeightsCalculate();
        newMidMarksCalculate();
        newMidMarksSumCalculate();
        theBestObjectCalculate();
        outPut.close();
    }

    public float[] getNewCompetence() {
        return newCompetence;
    }

    public float[] getNewMidCriterionWeights() {
        return newMidCriterionWeights;
    }

    public float[][] getNewMidMarks() {
        return newMidMarks;
    }

    public float[] getNewMidMarksSum() {
        return newMidMarksSum;
    }

    public int getTheBestObject () {
        return theBestObject;
    }

}
