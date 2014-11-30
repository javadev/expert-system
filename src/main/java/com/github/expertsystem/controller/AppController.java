package com.github.expertsystem.controller;

import com.github.expertsystem.model.FillDefaultValues;
import com.github.expertsystem.model.calculate.*;
import com.github.expertsystem.model.data.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for application (view - MainForm)
 */
public class AppController {

    // models
    private Method1 method1;
    private Method2a method2a;
    private Method2b method2b;
    private Method3 method3;
    private Method4 method4;
    private Method5 method5;

    private NumberFormat formatter;

    // true   -  default marks are random
    // false  -  default marks equal 0
    private boolean isRandom;

    /**
     * Method checks whether input string is float.
     * @param string
     * @return true - string is float; false - isn't
     */
    private boolean checkString(String string) {
        try {
            Float.parseFloat(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public AppController() {
        formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(3);
        // Fill default values in model (except marks)
        FillDefaultValues.fill();
    }

    public void setRandom(boolean isRandom) {
        this.isRandom = isRandom;
    }

    public List<String> getListExpertsDefault() {
        List<String> list = new ArrayList<String>();
        for (Expert expert : Experts.getExperts()) {
            list.add(expert.getName());
        }
        return list;
    }

    public List<String> getListExpertsKDefault() {
        List<String> list = new ArrayList<String>();
        for (Expert expert : Experts.getExperts()) {
            list.add(formatter.format(expert.getCompetence()));
        }
        return list;
    }

    public List<String> getListObjectsDefault() {
        List<String> list = new ArrayList<String>();
        for (Obj obj : Objects.getObjects()) {
            list.add(obj.getName());
        }
        return list;
    }

    public List<String> getListCriterionsDefault() {
        List<String> list = new ArrayList<String>();
        for (Criterion criterion : Criterions.getCriterions()) {
            list.add(criterion.getName());
        }
        return list;
    }


    // Actions

    public void onNext(DefaultListModel listModelExperts,
                       DefaultListModel listModelObjects,
                       DefaultListModel listModelCriterions) {
        if (FillDefaultValues.isChangedDefaultValues == false) {
            FillDefaultValues.fillMarks();
        } else {
            Experts.setExperts(listModelExperts.toArray());
            Objects.setObjects(listModelObjects.toArray());
            Criterions.setCriterions(listModelCriterions.toArray());
            if(isRandom) {
                Marks.fillRandomMap();
            } else {
                Marks.fillNullMap();
            }
        }
    }

    public void onSaveMarks(int indexCriterion, DefaultTableModel tableModel) {
        for(int i = 0; i < Experts.getExperts().size(); i++) {
            for(int j = 0; j < Objects.getObjects().size(); j++) {
                String cellValue = tableModel.getValueAt(i, j + 1).toString().replace(',', '.').trim();
                if(checkString(cellValue)) {
                    float value = Float.parseFloat(tableModel.getValueAt(i, j + 1).toString());
                    if(value >= 0) {
                        Marks.setMark(Experts.findExpert(i), Objects.findObj(j), Criterions.findCriterion(indexCriterion), value);
                    } else{
                        String msg = "Значение оценки должно быть положительным: эксперт " + Experts.findExpert(i).getName() + ", объект " +
                                Objects.findObj(j).getName() + "!";
                        JOptionPane.showMessageDialog(null, msg, "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    String msg = "Неверно введено значение оценки: эксперт " + Experts.findExpert(i).getName() + ", объект " +
                            Objects.findObj(j).getName() + "!";
                    JOptionPane.showMessageDialog(null, msg, "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
    }

    public boolean onAddExpert(String textFieldExperts, String textFieldExpertsK,
                               DefaultListModel listModelExperts, DefaultListModel listModelExpertsK) {
        String field = textFieldExperts.trim();
        String fieldK = textFieldExpertsK.replace(',', '.').trim();
        if(!listModelExperts.contains(field)) {
            if(!field.equals("")) {
                if(checkString(fieldK)) {
                    float val = Float.parseFloat(fieldK);
                    if(val > 0 && val < 1) {
                        listModelExpertsK.addElement(fieldK);
                        for(int i = 0; i < listModelExpertsK.getSize() - 1; i++) {
                            float value = Float.parseFloat(listModelExpertsK.get(i).toString().replace(',', '.')) -
                                    (Float.parseFloat(listModelExpertsK.get(i).toString().replace(',', '.')) * val);
                            listModelExpertsK.setElementAt(formatter.format(value).replace(',', '.'), i);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Коэффициент компетентности эксперта должен быть в пределах от 0 до 1!",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Неверно введен коэффициент компетентности эксперта!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                listModelExperts.addElement(field);
            } else {
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Эксперт с данным именем уже существует!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        FillDefaultValues.isChangedDefaultValues = true;
        return true;
    }

    public void onAddObject(String textFieldObjects, DefaultListModel listModelObjects) {
        String field = textFieldObjects.trim();
        if(!field.equals("") && !listModelObjects.contains(field)) {
            listModelObjects.addElement(field);
        }
        FillDefaultValues.isChangedDefaultValues = true;
    }

    public void onAddCriterion(String textFieldCriterions, DefaultListModel listModelCriterions) {
        String field = textFieldCriterions.trim();
        if(!field.equals("") && !listModelCriterions.contains(field)) {
            listModelCriterions.addElement(field);
        }
        FillDefaultValues.isChangedDefaultValues = true;
    }

    public void onDelExpert(int selectedIndex, DefaultListModel listModelExperts, DefaultListModel listModelExpertsK) {
        if(selectedIndex > -1) {
            listModelExperts.removeElementAt(selectedIndex);
            float delValue = Float.parseFloat(listModelExpertsK.get(selectedIndex).toString().replace(',', '.'));
            listModelExpertsK.removeElementAt(selectedIndex);
            for(int i = 0; i < listModelExpertsK.getSize(); i++) {
                float value = (Float.parseFloat(listModelExpertsK.get(i).toString().replace(',', '.'))
                        * 100.0f) /  ((1.0f - delValue) * 100.0f);
                listModelExpertsK.setElementAt(formatter.format(value).replace(',', '.'), i);
            }
        }
        FillDefaultValues.isChangedDefaultValues = true;
    }

    public void onDelObject(int selectedIndex, DefaultListModel listModelObjects) {
        if(selectedIndex > -1) {
            listModelObjects.removeElementAt(selectedIndex);
        }
        FillDefaultValues.isChangedDefaultValues = true;
    }

    public void onDelCriterion(int selectedIndex, DefaultListModel listModelCriterions) {
        if(selectedIndex > -1) {
            listModelCriterions.removeElementAt(selectedIndex);
        }
        FillDefaultValues.isChangedDefaultValues = true;
    }


    // Fill headers of tables (common)

    public Object[] getObjectHeadersForTable(DefaultListModel listModelObjects) {
        Object[] headers = new Object[Objects.getSize()+1];
        headers[0] = "Объекты:";
        for(int i = 0; i < Objects.getSize(); i++) {
            headers[i+1] = listModelObjects.toArray()[i].toString();
        }
        return headers;
    }

    public Object[] getExpertHeadersForTable(DefaultListModel listModelExperts) {
        Object[] headers = new Object[Experts.getSize()+1];
        headers[0] = "Эксперты:";
        for(int i = 0; i < Experts.getSize(); i++) {
            headers[i+1] = listModelExperts.toArray()[i].toString();
        }
        return headers;
    }

    public Object[] getSumHeadersForTable() {
        Object[] headers = new Object[1];
        headers[0] = "Сумма";
        return headers;
    }

    // Fill table (main panel)

    public Object[][] getDataForMarksTable(int indexOfCriterion, DefaultListModel listModelExperts) {
        Object[][] data = new Object[Experts.getSize()][Objects.getSize()+1];
        for(int i = 0; i < Experts.getSize(); i++) {
            data[i][0] = listModelExperts.toArray()[i].toString();
            for(int j = 1; j < Objects.getSize()+1; j++) {
                data[i][j] = formatter.format(Marks.getMark(Experts.findExpert(i), Objects.findObj(j - 1),
                        Criterions.findCriterion(indexOfCriterion)));
            }
        }
        return data;
    }

    // Fill tables (METHOD 1)

    public Object[] getHeadersTable1Method1(DefaultListModel listModelExperts) {
        Object[] headers = new Object[Experts.getSize()];
        for(int i = 0; i < Experts.getSize(); i++) {
            headers[i] = listModelExperts.toArray()[i].toString();
        }
        return headers;
    }

    public Object[][] getDataTable1Method1() {
        Object[][] data = new Object[1][Experts.getSize()];
        for(int i = 0; i < Experts.getSize(); i++) {
            data[0][i] = formatter.format(method1.getNewCompetence()[i]);
        }
        return data;
    }

    public Object[] getHeadersTable2Method1(DefaultListModel listModelCriterions) {
        Object[] headers = new Object[Criterions.getSize()];
        for(int i = 0; i < Criterions.getSize(); i++) {
            headers[i] = listModelCriterions.toArray()[i].toString();
        }
        return headers;
    }

    public Object[][] getDataTable2Method1() {
        Object[][] data = new Object[1][Criterions.getSize()];
        for(int i = 0; i < Criterions.getSize(); i++) {
            data[0][i] = formatter.format(method1.getNewMidCriterionWeights()[i]);
        }
        return data;
    }

    public Object[][] getDataTable3Method1() {
        Object[][] data = new Object[Objects.getSize()][Criterions.getSize()+1];
        for(int i = 0; i < Objects.getSize(); i++) {
            data[i][0] = Objects.findObj(i).getName();
            for(int j = 1; j < Criterions.getSize()+1; j++) {
                data[i][j] = formatter.format(method1.getNewMidMarks()[i][j-1]);
            }
        }
        return data;
    }

    public Object[][] getDataTable4Method1() {
        Object[][] data = new Object[Objects.getSize()][1];
        for(int i = 0; i < Objects.getSize(); i++) {
            data[i][0] = formatter.format(method1.getNewMidMarksSum()[i]);
        }
        return data;
    }

    public String getResultMethod1() {
        return Objects.findObj(method1.getTheBestObject()) + " (№ " + (method1.getTheBestObject() + 1) + ")";
    }


    // Fill tables (METHOD 2)

    public Object[][] getDataTable1Method2(int indexOfCriterion) {
        Object[][] data = new Object[Objects.getSize()][Experts.getSize()+1];
        for(int i = 0; i < Objects.getSize(); i++) {
            data[i][0] = Objects.findObj(i).getName();
            for(int j = 1; j < Experts.getSize()+1; j++) {
                data[i][j] = formatter.format(method2a.getRangs(indexOfCriterion)[j-1][i]);
            }
        }
        return data;
    }

    public Object[][] getDataTable2Method2(int indexOfCriterion) {
        Object[][] data = new Object[Objects.getSize()][1];
        for(int i = 0; i < Objects.getSize(); i++) {
            data[i][0] = formatter.format(method2a.getRangSums(indexOfCriterion)[i]);
        }
        return data;
    }

    public Object[][] getDataTable3Method2(int indexOfCriterion) {
        Object[][] data = new Object[Objects.getSize()][Objects.getSize()+1];
        for(int i = 0; i < Objects.getSize(); i++) {
            data[i][0] = Objects.findObj(i).getName();
            for(int j = 1; j < Objects.getSize()+1; j++) {
                data[i][j] = formatter.format(method2b.getPairSums(indexOfCriterion)[i][j-1]);
            }
        }
        return data;
    }

    public Object[][] getDataTable4Method2(int indexOfCriterion) {
        Object[][]data = new Object[Objects.getSize()][1];
        for(int i = 0; i < Objects.getSize(); i++) {
            data[i][0] = formatter.format(method2b.getSums(indexOfCriterion)[i]);
        }
    return data;
    }

    public Object[] getRangHeadersForTable() {
        Object[] headers = new Object[1];
        headers[0] = "Ранг";
        return headers;
    }

    public Object[][] getDataTable5Method2(int indexOfCriterion) {
        Object[][] data = new Object[Objects.getSize()][1];
        for(int i = 0; i < Objects.getSize(); i++) {
            data[i][0] = formatter.format(method2b.getRangs(indexOfCriterion)[i]);
        }
        return data;
    }

    public String getResult1Method2(int indexOfCriterion) {
        return Objects.findObj(method2a.getTheBestObject(indexOfCriterion)) +
                " (№ " + (method2a.getTheBestObject(indexOfCriterion) + 1) + ")";
    }

    public String getResult2Method2(int indexOfCriterion) {
        return Objects.findObj(method2b.getTheBestObject(indexOfCriterion)) +
                " (№ " + (method2b.getTheBestObject(indexOfCriterion) + 1) + ")";
    }


    // Fill tables (METHOD 3)

    public Object[] getHeadersTable1Method3() {
        Object[] headers = new Object[3];
        headers[0] = "Критерий";
        headers[1] = "D";
        headers[2] = "W";
        return headers;
    }

    public Object[][] getDataTable1Method3() {
        Object[][] data = new Object[Criterions.getSize()+1][3];
        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            data[cr][0] = Criterions.findCriterion(cr).getName();
            data[cr][1] = formatter.format(method3.getD(cr));
            data[cr][2] = formatter.format(method3.getW1WithoutRangs(cr));
        }
        return data;
    }

    public Object[] getHeadersTable2Method3() {
        Object[] headers = new Object[3];
        headers[1] = "W";
        headers[2] = "X";
        return headers;
    }

    public Object[][] getDataTable2Method3() {
        Object[][] data = new Object[Criterions.getSize()+1][3];
        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            data[cr][0] = Criterions.findCriterion(cr).getName();
            data[cr][1] = formatter.format(method3.getWWithRangs(cr));
            data[cr][2] = formatter.format(method3.getXWithRangs(cr));
        }
        return data;
    }

    public Object[] getHeadersTable3Method3() {
        Object[] headers = new Object[3];
        headers[1] = "H";
        headers[2] = "W";
        return headers;
    }

    public Object[][] getDataTable3Method3() {
        Object[][] data = new Object[Criterions.getSize()+1][3];
        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            data[cr][0] = Criterions.findCriterion(cr).getName();
            data[cr][1] = formatter.format(method3.getH(cr));
            data[cr][2] = formatter.format(method3.getW(cr));
        }
        return data;
    }

    // Fill tables (METHOD 4)

    public Object[][] getDataTable1Method4(int indexOfCriterion) {
        Object[][] data = new Object[Objects.getSize()][Objects.getSize()+1];
        for(int i = 0; i < Objects.getSize(); i++) {
            data[i][0] = Objects.findObj(i).getName();
            for(int j = 1; j < Objects.getSize()+1; j++) {
                data[i][j] = formatter.format(method4.getX(indexOfCriterion)[i][j-1]);
            }
        }
        return data;
    }

    public Object[] getHeadersTable2Method4() {
        Object[] headers = new Object[1];
        headers[0] = "К1";
        return headers;
    }

    public Object[][] getDataTable2Method4(int indexOfCriterion) {
        Object[][] data = new Object[Objects.getSize()][1];
        for(int i = 0; i < Objects.getSize(); i++) {
            data[i][0] = formatter.format(method4.getK1(indexOfCriterion)[i]);
        }
        return data;
    }

    public Object[][] getDataTable3Method4(int indexOfCriterion) {
        Object[][] data = new Object[Objects.getSize()][Objects.getSize()+1];
        for(int i = 0; i < Objects.getSize(); i++) {
            data[i][0] = Objects.findObj(i).getName();
            for(int j = 1; j < Objects.getSize()+1; j++) {
                data[i][j] = formatter.format(method4.getX2(indexOfCriterion)[i][j-1]);
            }
        }
        return data;
    }

    public Object[] getHeadersTable4Method4() {
        Object[] headers = new Object[1];
        headers[0] = "К2";
        return headers;
    }

    public Object[][] getDataTable4Method4(int indexOfCriterion) {
        Object[][] data = new Object[Objects.getSize()][1];
        for(int i = 0; i < Objects.getSize(); i++) {
            data[i][0] = formatter.format(method4.getK2(indexOfCriterion)[i]);
        }
        return data;
    }

    public String getResultMethod4(int indexOfCriterion) {
        return Objects.findObj(method4.getTheBestObject(indexOfCriterion)) +
                " (№ " + (method4.getTheBestObject(indexOfCriterion) + 1) + ")";
    }


    // Fill tables (METHOD 5)

    public Object[][] getDataTable1Method5(int indexOfCriterion) {
        Object[][] data = new Object[Experts.getSize()][Experts.getSize()+1];
        for(int i = 0; i < Experts.getSize(); i++) {
            data[i][0] = Experts.findExpert(i).getName();
            for(int j = 1; j < Experts.getSize()+1; j++) {
                data[i][j] = formatter.format(method5.getSpearman(indexOfCriterion)[i][j-1]);
            }
        }
        return data;
    }

    public Object[][] getDataTable2Method5(int indexOfCriterion) {
        Object[][] data = new Object[Experts.getSize()][Experts.getSize()+1];
        for(int i = 0; i < Experts.getSize(); i++) {
            data[i][0] = Experts.findExpert(i).getName();
            for(int j = 1; j < Experts.getSize()+1; j++) {
                data[i][j] = formatter.format(method5.getKendall(indexOfCriterion)[i][j-1]);
            }
        }
        return data;
    }

    // Fill TextAreas (METHOD 3 and 5)

    public String getResultMethod3() {
        return method3.getResult();
    }

    public String getResult1Method5() {
        String res = "";
        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            res += "Критерий " + Criterions.findCriterion(cr).getName().toUpperCase() + "\n\nНаиболее взаимосвязаны ранжировки \nэкспертов: \n";
            for(int[] pair : method5.getSpearmanResultMax(cr)) {
                res += Experts.findExpert(pair[0]).getName() + " и " + Experts.findExpert(pair[1]).getName() + "\n";
            }
            res += "\nНаименее взаимосвязаны ранжировки \nэкспертов: \n";
            for(int[] pair : method5.getSpearmanResultMin(cr)) {
                res += Experts.findExpert(pair[0]).getName() + " и " + Experts.findExpert(pair[1]).getName() + "\n";
            }
            res += "\n";
        }
        return res;
    }

    public String getResult2Method5() {
        String res = "";
        for(int cr = 0; cr < Criterions.getSize(); cr++) {
            res += "Критерий " + Criterions.findCriterion(cr).getName().toUpperCase() + "\n\nНаиболее взаимосвязаны ранжировки \nэкспертов: \n";
            for(int[] pair : method5.getKendallResultMax(cr)) {
                res += Experts.findExpert(pair[0]).getName() + " и " + Experts.findExpert(pair[1]).getName() + "\n";
            }
            res += "\nНаименее взаимосвязаны ранжировки \nэкспертов: \n";
            for(int[] pair : method5.getKendallResultMin(cr)) {
                res += Experts.findExpert(pair[0]).getName() + " и " + Experts.findExpert(pair[1]).getName() + "\n";
            }
            res += "\n";
        }
        return res;
    }

    public void calculateMethod1() {
        method1 = new Method1();
        method1.calculate();
    }

    public void calculateMethod2() {
        method2a = new Method2a();
        method2a.calculate();
        method2b = new Method2b();
        method2b.calculate();
    }

    public void calculateMethod3(int aIndex, String a) {
        method3 = new Method3();
        method3.calculate(aIndex, Float.parseFloat(a.replace(',', '.')));
    }

    public void calculateMethod4() {
        method4 = new Method4();
        method4.calculate();
    }

    public void calculateMethod5() {
        method5 = new Method5();
        method5.calculate();
    }

}