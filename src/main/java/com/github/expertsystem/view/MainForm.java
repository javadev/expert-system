package com.github.expertsystem.view;

import com.github.expertsystem.controller.AppController;
import com.github.expertsystem.model.calculate.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainForm extends JFrame {

    private AppController appController = null;

    private static int prevComboBoxValue = -1,
            prevComboBoxValue1Method2 = -1,
            prevComboBoxValue2Method2 = -1,
            prevComboBoxValueMethod3 = -1,
            prevComboBoxValueMethod4 = -1,
            prevComboBoxValueMethod5 = -1;
    private JPanel setPrimaryDataPanel,
            calculatePanel,
            panelMethod3,
            panelMethod1,
            panelMethod5,
            panelMethod4,
            panelMethod2;
    private JLabel labelChooseCriterion,
            labelExperts,
            labelObjects,
            labelCriterions,
            labelBackToEdit,
            labelMethodName1,
            labelMethodName2,
            labelMethodName3,
            labelMethodName4,
            labelMethodName5,
            labelDiscriptionMethod1,
            labelDiscriptionMethod2,
            labelDiscriptionMethod3,
            labelDiscriptionMethod4,
            labelDiscriptionMethod5,
            labelBackToChoose,
            labelBestObjValue1Method2,
            labelBestObjValue2Method2,
            labelResMethod3,
            labelBestObjValueMethod4,
            labelSpearmanResult,
            labelKendallResult;
    private DefaultListModel listModelExperts,
            listModelExpertsK,
            listModelObjects,
            listModelCriterions;
    private JList listExperts, listExpertsK, listObjects, listCriterions;

    private JCheckBox areDefaultValuesRandom;

    private JTextField textFieldExperts, textFieldExpertsK, textFieldObjects, textFieldCriterions;
    private JTextArea textAreaMethod3, textArea1Method5, textArea2Method5;
    private JScrollPane scrollPaneExpertsList,
            scrollPaneExpertsKList,
            scrollPaneObjectsList,
            scrollPaneCriterionsList,
            scrollPaneTable,
            scrollPaneTable1Method1,
            scrollPaneTable2Method1,
            scrollPaneTable3Method1,
            scrollPaneTable4Method1,
            scrollPaneTable1Method2,
            scrollPaneTable2Method2,
            scrollPaneTable3Method2,
            scrollPaneTable4Method2,
            scrollPaneTable5Method2,
            scrollPaneTable1Method3,
            scrollPaneTable2Method3,
            scrollPaneTable3Method3,
            scrollPaneTextAreaMethod3,
            scrollPaneTable1Method4,
            scrollPaneTable2Method4,
            scrollPaneTable3Method4,
            scrollPaneTable4Method4,
            scrollPaneTable1Method5,
            scrollPaneTable2Method5,
            scrollPaneTextArea1Method5,
            scrollPaneTextArea2Method5;
    private JComboBox comboBoxCriterions,
            comboBoxCriterions1Method2,
            comboBoxCriterions2Method2,
            comboBoxMethod3,
            comboBoxCriterionsMethod4,
            comboBoxCriterionsMethod5;
    private JButton buttonAddExpert,
            buttonAddObject,
            buttonAddCriterion,
            buttonDelExpert,
            buttonDelObject,
            buttonDelCriterion,
            buttonNext,
            buttonSaveMarks,
            buttonPrev,
            buttonGoToCalculate,
            buttonGoToMethod1,
            buttonGoToMethod2,
            buttonGoToMethod3,
            buttonGoToMethod4,
            buttonGoToMethod5,
            showResultsMethod1,
            showResultsMethod2,
            showResultsMethod3,
            showResultsMethod4,
            showResultsMethod5;
    private JTable tableMarks,
            table1Method1,
            table2Method1,
            table3Method1,
            table4Method1,
            table1Method2,
            table2Method2,
            table3Method2,
            table4Method2,
            table5Method2,
            table1Method3,
            table2Method3,
            table3Method3,
            table1Method4,
            table2Method4,
            table3Method4,
            table4Method4,
            table1Method5,
            table2Method5;
    private DefaultTableModel tableModel,
            tableModel1Method1,
            tableModel2Method1,
            tableModel3Method1,
            tableModel4Method1,
            tableModel1Method2,
            tableModel2Method2,
            tableModel3Method2,
            tableModel4Method2,
            tableModel5Method2,
            tableModel1Method3,
            tableModel2Method3,
            tableModel3Method3,
            tableModel1Method4,
            tableModel2Method4,
            tableModel3Method4,
            tableModel4Method4,
            tableModel1Method5,
            tableModel2Method5;

    public MainForm(String title, AppController appController) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.appController = appController;

        addPanels();
        addLabels();
        addLists();
        addTextFields();
        addCheckBoxes();
        addScrollPanes();
        addComboBoxes();
        addButtons();
        addTables();

        getContentPane().add(setPrimaryDataPanel);
        pack();
        setBounds(0, 0, 900, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addPanels() {
        setPrimaryDataPanel = new JPanel();
        setPrimaryDataPanel.setLayout(null);
    }

    private void addLabels() {
        labelExperts = new JLabel("Эксперты:");
        labelExperts.setBounds(20, 10, 100, 15);
        setPrimaryDataPanel.add(labelExperts);

        labelObjects = new JLabel("Объекты:");
        labelObjects.setBounds(295, 10, 100, 15);
        setPrimaryDataPanel.add(labelObjects);

        labelCriterions = new JLabel("Критерии:");
        labelCriterions.setBounds(495, 10, 100, 15);
        setPrimaryDataPanel.add(labelCriterions);

        labelChooseCriterion = new JLabel("Выберите критерий из списка:");
        labelChooseCriterion.setBounds(20, 335, 200, 15);
        setPrimaryDataPanel.add(labelChooseCriterion);
    }

    private void addLists() {
        listModelExperts = new DefaultListModel();
        for (String expert : appController.getListExpertsDefault()) {
            listModelExperts.addElement(expert);
        }
        listExperts = new JList(listModelExperts);
        listExperts.setLayoutOrientation(JList.VERTICAL);
        listExperts.setVisibleRowCount(0);
        listExperts.setBounds(20, 30, 180, 200);

        listModelExpertsK = new DefaultListModel();
        for (String expertK : appController.getListExpertsKDefault()) {
            listModelExpertsK.addElement(expertK);
        }
        listExpertsK = new JList(listModelExpertsK);
        listExpertsK.setLayoutOrientation(JList.VERTICAL);
        listExpertsK.setVisibleRowCount(0);
        listExpertsK.setBounds(210, 30, 75, 200);

        listModelObjects = new DefaultListModel();
        for (String obj : appController.getListObjectsDefault()) {
            listModelObjects.addElement(obj);
        }
        listObjects = new JList(listModelObjects);
        listObjects.setLayoutOrientation(JList.VERTICAL);
        listObjects.setVisibleRowCount(0);
        listObjects.setBounds(295, 30, 180, 200);

        listModelCriterions = new DefaultListModel();
        for (String criterion : appController.getListCriterionsDefault()) {
            listModelCriterions.addElement(criterion);
        }
        listCriterions = new JList(listModelCriterions);
        listCriterions.setLayoutOrientation(JList.VERTICAL);
        listCriterions.setVisibleRowCount(0);
        listCriterions.setBounds(495, 30, 180, 200);
    }

    private void addTextFields() {
        textFieldExperts = new JTextField();
        textFieldExperts.setBounds(20, 240, 180, 20);
        setPrimaryDataPanel.add(textFieldExperts);

        textFieldExpertsK = new JTextField();
        textFieldExpertsK.setBounds(210, 240, 75, 20);
        setPrimaryDataPanel.add(textFieldExpertsK);

        textFieldObjects = new JTextField();
        textFieldObjects.setBounds(295, 240, 180, 20);
        setPrimaryDataPanel.add(textFieldObjects);

        textFieldCriterions = new JTextField();
        textFieldCriterions.setBounds(495, 240, 180, 20);
        setPrimaryDataPanel.add(textFieldCriterions);
    }

    private void addScrollPanes() {
        scrollPaneExpertsList = new JScrollPane(listExperts);
        scrollPaneExpertsList.setBounds(20, 30, 180, 200);
        scrollPaneExpertsList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setPrimaryDataPanel.add(scrollPaneExpertsList);

        scrollPaneExpertsKList = new JScrollPane(listExpertsK);
        scrollPaneExpertsKList.setBounds(210, 30, 75, 200);
        scrollPaneExpertsKList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setPrimaryDataPanel.add(scrollPaneExpertsKList);

        scrollPaneObjectsList = new JScrollPane(listObjects);
        scrollPaneObjectsList.setBounds(295, 30, 180, 200);
        scrollPaneObjectsList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setPrimaryDataPanel.add(scrollPaneObjectsList);

        scrollPaneCriterionsList = new JScrollPane(listCriterions);
        scrollPaneCriterionsList.setBounds(495, 30, 180, 200);
        scrollPaneCriterionsList.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setPrimaryDataPanel.add(scrollPaneCriterionsList);
    }

    private void addCheckBoxes() {
        areDefaultValuesRandom = new JCheckBox("Заполнить таблицы оценок случайными числами");
        areDefaultValuesRandom.setBounds(390, 335, 360, 15);
        areDefaultValuesRandom.setSelected(true);
        setPrimaryDataPanel.add(areDefaultValuesRandom);
    }

    private void addComboBoxes() {
        comboBoxCriterions = new JComboBox(listModelCriterions.toArray());
        comboBoxCriterions.setBounds(220, 335, 150, 20);
        setPrimaryDataPanel.add(comboBoxCriterions);
        comboBoxCriterions.setVisible(true);

        prevComboBoxValue = -1;
        comboBoxCriterions.setEnabled(false);
        comboBoxCriterions.removeAllItems();
        for (Object criterion : listModelCriterions.toArray()) {
            comboBoxCriterions.addItem(criterion.toString());
        }
        prevComboBoxValue = 0;
        comboBoxCriterions.addActionListener(
                e1 -> {
                    if (prevComboBoxValue > -1
                            && prevComboBoxValue != comboBoxCriterions.getSelectedIndex()) {
                        tableModel.setDataVector(
                                appController.getDataForMarksTable(
                                        comboBoxCriterions.getSelectedIndex(), listModelExperts),
                                appController.getObjectHeadersForTable(listModelObjects));
                    }
                    prevComboBoxValue = comboBoxCriterions.getSelectedIndex();
                });
    }

    private void addButtons() {
        buttonAddExpert = new JButton("Добавить");
        buttonAddExpert.setBounds(20, 270, 90, 30);
        setPrimaryDataPanel.add(buttonAddExpert);
        buttonAddExpert.addActionListener(
                e -> {
                    boolean isOk =
                            appController.onAddExpert(
                                    textFieldExperts.getText(),
                                    textFieldExpertsK.getText(),
                                    listModelExperts,
                                    listModelExpertsK);
                    if (isOk) {
                        textFieldExperts.setText("");
                        textFieldExpertsK.setText("");
                    }
                });

        buttonAddObject = new JButton("Добавить");
        buttonAddObject.setBounds(295, 270, 90, 30);
        setPrimaryDataPanel.add(buttonAddObject);
        buttonAddObject.addActionListener(
                e -> {
                    appController.onAddObject(textFieldObjects.getText(), listModelObjects);
                    textFieldObjects.setText("");
                });

        buttonAddCriterion = new JButton("Добавить");
        buttonAddCriterion.setBounds(495, 270, 90, 30);
        setPrimaryDataPanel.add(buttonAddCriterion);
        buttonAddCriterion.addActionListener(
                e -> {
                    appController.onAddCriterion(
                            textFieldCriterions.getText(), listModelCriterions);
                    textFieldCriterions.setText("");
                });

        buttonDelExpert = new JButton("Удалить");
        buttonDelExpert.setBounds(110, 270, 90, 30);
        setPrimaryDataPanel.add(buttonDelExpert);
        buttonDelExpert.addActionListener(
                e -> {
                    appController.onDelExpert(
                            listExperts.getSelectedIndex(), listModelExperts, listModelExpertsK);
                });

        buttonDelObject = new JButton("Удалить");
        buttonDelObject.setBounds(385, 270, 90, 30);
        setPrimaryDataPanel.add(buttonDelObject);
        buttonDelObject.addActionListener(
                e -> {
                    appController.onDelObject(listObjects.getSelectedIndex(), listModelObjects);
                });

        buttonDelCriterion = new JButton("Удалить");
        buttonDelCriterion.setBounds(585, 270, 90, 30);
        setPrimaryDataPanel.add(buttonDelCriterion);
        buttonDelCriterion.addActionListener(
                e -> {
                    appController.onDelCriterion(
                            listCriterions.getSelectedIndex(), listModelCriterions);
                });

        buttonNext = new JButton("Далее");
        buttonNext.setBounds(730, 50, 100, 40);
        setPrimaryDataPanel.add(buttonNext);
        buttonNext.addActionListener(
                e -> {
                    if (listModelExperts.getSize() == 0) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Список экспертов пуст!",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (listModelObjects.getSize() == 0) {
                        JOptionPane.showMessageDialog(
                                null, "Список объектов пуст!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (listModelCriterions.getSize() == 0) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Список критериев пуст!",
                                "Ошибка",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    appController.setRandom(areDefaultValuesRandom.isSelected());
                    appController.onNext(listModelExperts, listModelObjects, listModelCriterions);
                    tableModel.setDataVector(
                            appController.getDataForMarksTable(0, listModelExperts),
                            appController.getObjectHeadersForTable(listModelObjects));
                    prevComboBoxValue = -1;
                    comboBoxCriterions.removeAllItems();
                    for (Object criterion : listModelCriterions.toArray()) {
                        comboBoxCriterions.addItem(criterion.toString());
                    }
                    setEnabledStepOne(false);
                    setEnabledStepTwo(true);
                });

        buttonSaveMarks = new JButton("Сохранить");
        buttonSaveMarks.setBounds(730, 400, 100, 40);
        setPrimaryDataPanel.add(buttonSaveMarks);
        buttonSaveMarks.setEnabled(false);
        buttonSaveMarks.addActionListener(
                e1 -> {
                    appController.onSaveMarks(comboBoxCriterions.getSelectedIndex(), tableModel);
                });

        buttonGoToCalculate = new JButton("Перейти к расчетам");
        buttonGoToCalculate.setBounds(700, 470, 160, 40);
        setPrimaryDataPanel.add(buttonGoToCalculate);
        buttonGoToCalculate.setEnabled(false);
        buttonGoToCalculate.addActionListener(
                e1 -> {
                    if (calculatePanel == null) {
                        buildCalculatePanel();
                    }
                    setPrimaryDataPanel.setVisible(false);
                    calculatePanel.setVisible(true);
                });

        buttonPrev = new JButton("Назад");
        buttonPrev.setBounds(730, 120, 100, 40);
        setPrimaryDataPanel.add(buttonPrev);
        buttonPrev.setEnabled(false);
        buttonPrev.addActionListener(
                e1 -> {
                    setEnabledStepOne(true);
                    setEnabledStepTwo(false);
                });
    }

    private void addTables() {
        tableModel = new DefaultTableModel();
        tableMarks = new JTable(tableModel);
        tableMarks.setBounds(20, 370, 655, 250);

        scrollPaneTable = new JScrollPane(tableMarks);
        scrollPaneTable.setBounds(20, 370, 655, 250);
        scrollPaneTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setPrimaryDataPanel.add(scrollPaneTable);

        scrollPaneTable.setEnabled(false);
    }

    private void buildCalculatePanel() {
        calculatePanel = new JPanel();
        calculatePanel.setLayout(null);

        // Labels
        labelMethodName1 =
                new JLabel("1. Составление групповых оценок по методу непосредственной оценки");
        labelMethodName1.setBounds(10, 20, 500, 15);
        calculatePanel.add(labelMethodName1);

        labelMethodName2 = new JLabel("2. Составление групповых оценок по методу ранжирования");
        labelMethodName2.setBounds(10, 145, 500, 15);
        calculatePanel.add(labelMethodName2);

        labelMethodName3 = new JLabel("3. Оценка согласованности мнений экспертов");
        labelMethodName3.setBounds(10, 270, 500, 15);
        calculatePanel.add(labelMethodName3);

        labelMethodName4 = new JLabel("4. Обработка сравнений объектов");
        labelMethodName4.setBounds(10, 395, 500, 15);
        calculatePanel.add(labelMethodName4);

        labelMethodName5 = new JLabel("5. Определение взаимосвязи ранжировок");
        labelMethodName5.setBounds(10, 520, 500, 15);
        calculatePanel.add(labelMethodName5);

        labelBackToEdit = new JLabel("Вернуться к редактированию исходных данных");
        labelBackToEdit.setBounds(10, 640, 300, 15);
        calculatePanel.add(labelBackToEdit);
        labelBackToEdit.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        calculatePanel.setVisible(false);
                        setPrimaryDataPanel.setVisible(true);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        labelBackToEdit.setForeground(Color.BLUE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        labelBackToEdit.setForeground(Color.BLACK);
                    }
                });

        labelDiscriptionMethod1 =
                new JLabel(
                        "<html><font color=\"696969\"><i>Результаты оценки представляют собой числа "
                                + "из некоторого отрезка числовой оси, или баллы. Для получения групповой оценки используется среднее "
                                + "значение оценки для каждого объекта. Коэффициенты компетентности экспертов вычисляются по результатам "
                                + "оценки объектов. Коэффициенты весов показателей определяются экспертным путем.</i></font></html>");
        labelDiscriptionMethod1.setBounds(20, 40, 860, 50);
        labelDiscriptionMethod1.setFont(
                new Font(labelDiscriptionMethod1.getFont().getName(), Font.PLAIN, 12));
        calculatePanel.add(labelDiscriptionMethod1);

        labelDiscriptionMethod2 =
                new JLabel(
                        "<html><font color=\"696969\"><i>По результатам экспертной процедуры "
                                + "устанавливается упорядоченная последовательность альтернатив, в которой их расположение отвечает их "
                                + "предпочтительности. По результатам обработки мнений экспертов должна быть определена единственная "
                                + "перестановка. Выделяют такие способы построения обобщенной ранжировки, как корреляция рангов и парные "
                                + "сравнения.</i></font></html>");
        labelDiscriptionMethod2.setBounds(20, 165, 860, 50);
        labelDiscriptionMethod2.setFont(
                new Font(labelDiscriptionMethod2.getFont().getName(), Font.PLAIN, 12));
        calculatePanel.add(labelDiscriptionMethod2);

        labelDiscriptionMethod3 =
                new JLabel(
                        "<html><font color=\"696969\"><i>При ранжировании объектов эксперты обычно "
                                + "расходятся во мнениях по решаемой проблеме. В связи с этим возникает необходимость количественной оценки "
                                + "степени согласия экспертов. В настоящее время известны две меры согласованности мнений группы экспертов: "
                                + "дисперсионный и энтропийный коэффициенты конкордации.</i></font></html>");
        labelDiscriptionMethod3.setBounds(20, 290, 860, 50);
        labelDiscriptionMethod3.setFont(
                new Font(labelDiscriptionMethod3.getFont().getName(), Font.PLAIN, 12));
        calculatePanel.add(labelDiscriptionMethod3);

        labelDiscriptionMethod4 =
                new JLabel(
                        "<html><font color=\"696969\"><i>При решении задачи оценки большого числа "
                                + "объектов возникают трудности психологического характера, обусловленные восприятием экспертами множества "
                                + "свойств объектов. Эксперты сравнительно легко решают задачу парного сравнения объектов. "
                                + "Ранжировка объектов осушествляется с помощью коэффициентов относительной важности объектов.</i></font></html>");
        labelDiscriptionMethod4.setBounds(20, 415, 860, 50);
        labelDiscriptionMethod4.setFont(
                new Font(labelDiscriptionMethod4.getFont().getName(), Font.PLAIN, 12));
        calculatePanel.add(labelDiscriptionMethod4);

        labelDiscriptionMethod5 =
                new JLabel(
                        "<html><font color=\"696969\"><i>При обработке результатов ранжирования "
                                + "могут возникнуть задачи определения зависимости между ранжировками двух экспертов. "
                                + "В этих случаях мерой взаимосвязи может служить коэффициент ранговой корреляции. Характеристикой "
                                + "взаимосвязи множества ранжировок или целей будет являться матрица коэффициентов ранговой корреляции. "
                                + "Известны коэффициенты ранговой корреляции Спирмена и Кендалла.</i></font></html>");
        labelDiscriptionMethod5.setBounds(20, 540, 860, 50);
        labelDiscriptionMethod5.setFont(
                new Font(labelDiscriptionMethod5.getFont().getName(), Font.PLAIN, 12));
        calculatePanel.add(labelDiscriptionMethod5);

        // Buttons
        buttonGoToMethod1 = new JButton("Перейти к расчетам");
        buttonGoToMethod1.setBounds(20, 100, 160, 30);
        calculatePanel.add(buttonGoToMethod1);
        buttonGoToMethod1.addActionListener(
                e -> {
                    calculatePanel.setVisible(false);
                    if (panelMethod1 == null) {
                        appController.calculateMethod1();
                        buildPanelMethod1();
                    } else {
                        panelMethod1.setVisible(true);
                    }
                    panelMethod1.add(labelBackToChoose);
                });

        buttonGoToMethod2 = new JButton("Перейти к расчетам");
        buttonGoToMethod2.setBounds(20, 225, 160, 30);
        calculatePanel.add(buttonGoToMethod2);
        buttonGoToMethod2.addActionListener(
                e -> {
                    calculatePanel.setVisible(false);
                    if (panelMethod2 == null) {
                        appController.calculateMethod2();
                        buildPanelMethod2();
                    } else {
                        panelMethod2.setVisible(true);
                    }
                    panelMethod2.add(labelBackToChoose);
                });

        buttonGoToMethod3 = new JButton("Перейти к расчетам");
        buttonGoToMethod3.setBounds(20, 350, 160, 30);
        calculatePanel.add(buttonGoToMethod3);
        buttonGoToMethod3.addActionListener(
                e -> {
                    calculatePanel.setVisible(false);
                    if (panelMethod3 == null) {
                        appController.calculateMethod3(
                                Method3.DEF_A_INDEX, String.valueOf(Method3.DEF_A));
                        buildPanelMethod3();
                    } else {
                        panelMethod3.setVisible(true);
                    }
                    panelMethod3.add(labelBackToChoose);
                });

        buttonGoToMethod4 = new JButton("Перейти к расчетам");
        buttonGoToMethod4.setBounds(20, 475, 160, 30);
        calculatePanel.add(buttonGoToMethod4);
        buttonGoToMethod4.addActionListener(
                e -> {
                    calculatePanel.setVisible(false);
                    if (panelMethod4 == null) {
                        appController.calculateMethod4();
                        buildPanelMethod4();
                    } else {
                        panelMethod4.setVisible(true);
                    }
                    panelMethod4.add(labelBackToChoose);
                });

        buttonGoToMethod5 = new JButton("Перейти к расчетам");
        buttonGoToMethod5.setBounds(20, 600, 160, 30);
        calculatePanel.add(buttonGoToMethod5);
        buttonGoToMethod5.addActionListener(
                e -> {
                    calculatePanel.setVisible(false);
                    if (panelMethod5 == null) {
                        appController.calculateMethod5();
                        buildPanelMethod5();
                    } else {
                        panelMethod5.setVisible(true);
                    }
                    panelMethod5.add(labelBackToChoose);
                });
        getContentPane().add(calculatePanel);

        labelBackToChoose = new JLabel("Вернуться к выбору вида расчетов");
        labelBackToChoose.setBounds(10, 640, 300, 15);
        labelBackToChoose.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (panelMethod1 != null && panelMethod1.isVisible()) {
                            panelMethod1.setVisible(false);
                        }
                        if (panelMethod2 != null && panelMethod2.isVisible()) {
                            panelMethod2.setVisible(false);
                        }
                        if (panelMethod3 != null && panelMethod3.isVisible()) {
                            panelMethod3.setVisible(false);
                        }
                        if (panelMethod4 != null && panelMethod4.isVisible()) {
                            panelMethod4.setVisible(false);
                        }
                        if (panelMethod5 != null && panelMethod5.isVisible()) {
                            panelMethod5.setVisible(false);
                        }
                        calculatePanel.setVisible(true);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        labelBackToChoose.setForeground(Color.BLUE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        labelBackToChoose.setForeground(Color.BLACK);
                    }
                });
    }

    private void buildPanelMethod1() {
        panelMethod1 = new JPanel();
        panelMethod1.setLayout(null);

        // Labels
        JLabel labelTitle =
                new JLabel("Составление групповых оценок по методу непосредственной оценки");
        labelTitle.setBounds(200, 20, 500, 20);
        labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.PLAIN, 16));
        panelMethod1.add(labelTitle);

        JLabel labelCompetence = new JLabel("Коэффициенты компетентности экспертов");
        labelCompetence.setBounds(20, 70, 300, 15);
        panelMethod1.add(labelCompetence);

        JLabel labelMidMarks =
                new JLabel("Средние значения значений коэффициентов весов показателей объектов");
        labelMidMarks.setBounds(20, 180, 500, 15);
        panelMethod1.add(labelMidMarks);

        JLabel labelMidCriterionWeights = new JLabel("Средние значения оценок объектов");
        labelMidCriterionWeights.setBounds(20, 285, 300, 15);
        panelMethod1.add(labelMidCriterionWeights);

        JLabel labelBestObj =
                new JLabel("Наиболее предпочтительный объект: " + appController.getResultMethod1());
        labelBestObj.setBounds(30, 560, 500, 20);
        labelBestObj.setFont(new Font(labelBestObj.getFont().getName(), Font.PLAIN, 16));
        panelMethod1.add(labelBestObj);

        // Tables
        tableModel1Method1 =
                new DefaultTableModel(
                        appController.getDataTable1Method1(),
                        appController.getHeadersTable1Method1(listModelExperts));
        table1Method1 = new JTable(tableModel1Method1);
        table1Method1.setBounds(20, 95, 655, 55);
        scrollPaneTable1Method1 = new JScrollPane(table1Method1);
        scrollPaneTable1Method1.setBounds(20, 95, 655, 55);
        scrollPaneTable1Method1.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelMethod1.add(scrollPaneTable1Method1);

        tableModel2Method1 =
                new DefaultTableModel(
                        appController.getDataTable2Method1(),
                        appController.getHeadersTable2Method1(listModelCriterions));
        table2Method1 = new JTable(tableModel2Method1);
        table2Method1.setBounds(20, 205, 655, 55);
        scrollPaneTable2Method1 = new JScrollPane(table2Method1);
        scrollPaneTable2Method1.setBounds(20, 205, 655, 55);
        scrollPaneTable2Method1.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelMethod1.add(scrollPaneTable2Method1);

        tableModel3Method1 =
                new DefaultTableModel(
                        appController.getDataTable3Method1(),
                        appController.getObjectHeadersForTable(listModelObjects));
        table3Method1 = new JTable(tableModel3Method1);
        table3Method1.setBounds(20, 310, 655, 200);
        scrollPaneTable3Method1 = new JScrollPane(table3Method1);
        scrollPaneTable3Method1.setBounds(20, 310, 655, 200);
        scrollPaneTable3Method1.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTable3Method1.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod1.add(scrollPaneTable3Method1);

        tableModel4Method1 =
                new DefaultTableModel(
                        appController.getDataTable4Method1(),
                        appController.getSumHeadersForTable());
        table4Method1 = new JTable(tableModel4Method1);
        table4Method1.setBounds(685, 310, 150, 200);
        scrollPaneTable4Method1 = new JScrollPane(table4Method1);
        scrollPaneTable4Method1.setBounds(685, 310, 150, 200);
        scrollPaneTable4Method1.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod1.add(scrollPaneTable4Method1);

        // Buttons
        showResultsMethod1 = new JButton("Показать ход решения");
        showResultsMethod1.setBounds(640, 620, 200, 30);
        panelMethod1.add(showResultsMethod1);
        showResultsMethod1.addActionListener(
                e1 -> {
                    new ResultForm(
                            "Ход составления групповых оценок по методу непосредственной оценки",
                            Method1.FILENAME);
                });

        getContentPane().add(panelMethod1);
    }

    private void buildPanelMethod2() {
        panelMethod2 = new JPanel();
        panelMethod2.setLayout(null);

        // Labels
        JLabel labelTitle = new JLabel("Составление групповых оценок по методу ранжирования");
        labelTitle.setBounds(200, 20, 500, 20);
        labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.PLAIN, 16));
        panelMethod2.add(labelTitle);

        JLabel labelRang = new JLabel("1. Обобщенная ранжировка на основе ранговой корреляции");
        labelRang.setBounds(20, 60, 500, 15);
        panelMethod2.add(labelRang);

        JLabel labelCriterion1 = new JLabel("Выберите критерий из списка: ");
        labelCriterion1.setBounds(33, 85, 200, 15);
        panelMethod2.add(labelCriterion1);

        JLabel labelBestObj1 = new JLabel("Наиболее предпочтительный объект:");
        labelBestObj1.setBounds(30, 305, 300, 20);
        labelBestObj1.setFont(new Font(labelBestObj1.getFont().getName(), Font.PLAIN, 16));
        panelMethod2.add(labelBestObj1);

        labelBestObjValue1Method2 = new JLabel(appController.getResult1Method2(0));
        labelBestObjValue1Method2.setBounds(320, 305, 300, 20);
        labelBestObjValue1Method2.setFont(
                new Font(labelBestObjValue1Method2.getFont().getName(), Font.PLAIN, 16));
        panelMethod2.add(labelBestObjValue1Method2);

        JLabel labelPairs = new JLabel("2. Обобщенная ранжировка на основе парных сравнений");
        labelPairs.setBounds(20, 345, 500, 15);
        panelMethod2.add(labelPairs);

        JLabel labelCriterion2 = new JLabel("Выберите критерий из списка: ");
        labelCriterion2.setBounds(33, 370, 200, 15);
        panelMethod2.add(labelCriterion2);

        JLabel labelBestObj2 = new JLabel("Наиболее предпочтительный объект:");
        labelBestObj2.setBounds(30, 590, 300, 20);
        labelBestObj2.setFont(new Font(labelBestObj2.getFont().getName(), Font.PLAIN, 16));
        panelMethod2.add(labelBestObj2);

        labelBestObjValue2Method2 = new JLabel(appController.getResult2Method2(0));
        labelBestObjValue2Method2.setBounds(320, 590, 300, 20);
        labelBestObjValue2Method2.setFont(
                new Font(labelBestObjValue2Method2.getFont().getName(), Font.PLAIN, 16));
        panelMethod2.add(labelBestObjValue2Method2);

        // Tables
        tableModel1Method2 =
                new DefaultTableModel(
                        appController.getDataTable1Method2(0),
                        appController.getObjectHeadersForTable(listModelObjects));
        table1Method2 = new JTable(tableModel1Method2);
        table1Method2.setBounds(20, 110, 655, 180);
        scrollPaneTable1Method2 = new JScrollPane(table1Method2);
        scrollPaneTable1Method2.setBounds(20, 110, 655, 180);
        scrollPaneTable1Method2.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTable1Method2.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod2.add(scrollPaneTable1Method2);

        tableModel2Method2 =
                new DefaultTableModel(
                        appController.getDataTable2Method2(0),
                        appController.getSumHeadersForTable());
        table2Method2 = new JTable(tableModel2Method2);
        table2Method2.setBounds(685, 110, 150, 180);
        scrollPaneTable2Method2 = new JScrollPane(table2Method2);
        scrollPaneTable2Method2.setBounds(685, 110, 150, 180);
        scrollPaneTable2Method2.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod2.add(scrollPaneTable2Method2);

        tableModel3Method2 =
                new DefaultTableModel(
                        appController.getDataTable3Method2(0),
                        appController.getObjectHeadersForTable(listModelObjects));
        table3Method2 = new JTable(tableModel3Method2);
        table3Method2.setBounds(20, 395, 655, 180);
        scrollPaneTable3Method2 = new JScrollPane(table3Method2);
        scrollPaneTable3Method2.setBounds(20, 395, 655, 180);
        scrollPaneTable3Method2.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTable3Method2.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod2.add(scrollPaneTable3Method2);

        tableModel4Method2 =
                new DefaultTableModel(
                        appController.getDataTable4Method2(0),
                        appController.getSumHeadersForTable());
        table4Method2 = new JTable(tableModel4Method2);
        table4Method2.setBounds(685, 395, 70, 180);
        scrollPaneTable4Method2 = new JScrollPane(table4Method2);
        scrollPaneTable4Method2.setBounds(685, 395, 70, 180);
        scrollPaneTable4Method2.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod2.add(scrollPaneTable4Method2);

        tableModel5Method2 =
                new DefaultTableModel(
                        appController.getDataTable5Method2(0),
                        appController.getRangHeadersForTable());
        table5Method2 = new JTable(tableModel5Method2);
        table5Method2.setBounds(765, 395, 70, 180);
        scrollPaneTable5Method2 = new JScrollPane(table5Method2);
        scrollPaneTable5Method2.setBounds(765, 395, 70, 180);
        scrollPaneTable5Method2.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod2.add(scrollPaneTable5Method2);

        // ComboBoxes
        comboBoxCriterions1Method2 = new JComboBox(listModelCriterions.toArray());
        comboBoxCriterions1Method2.setBounds(220, 82, 150, 20);
        panelMethod2.add(comboBoxCriterions1Method2);
        comboBoxCriterions1Method2.setVisible(false);
        prevComboBoxValue1Method2 = -1;
        comboBoxCriterions1Method2.setVisible(true);
        comboBoxCriterions1Method2.removeAllItems();
        for (Object criterion : listModelCriterions.toArray()) {
            comboBoxCriterions1Method2.addItem(criterion.toString());
        }
        prevComboBoxValue1Method2 = 0;
        comboBoxCriterions1Method2.addActionListener(
                e1 -> {
                    int cr = comboBoxCriterions1Method2.getSelectedIndex();
                    if (prevComboBoxValue1Method2 > -1 && prevComboBoxValue1Method2 != cr) {
                        tableModel1Method2.setDataVector(
                                appController.getDataTable1Method2(cr),
                                appController.getObjectHeadersForTable(listModelObjects));
                        tableModel2Method2.setDataVector(
                                appController.getDataTable2Method2(cr),
                                appController.getSumHeadersForTable());
                        labelBestObjValue1Method2.setText(appController.getResult1Method2(cr));
                    }
                    prevComboBoxValue1Method2 = cr;
                });

        comboBoxCriterions2Method2 = new JComboBox(listModelCriterions.toArray());
        comboBoxCriterions2Method2.setBounds(220, 367, 150, 20);
        panelMethod2.add(comboBoxCriterions2Method2);
        comboBoxCriterions2Method2.setVisible(false);
        prevComboBoxValue2Method2 = -1;
        comboBoxCriterions2Method2.setVisible(true);
        comboBoxCriterions2Method2.removeAllItems();
        for (Object criterion : listModelCriterions.toArray()) {
            comboBoxCriterions2Method2.addItem(criterion.toString());
        }
        prevComboBoxValue2Method2 = 0;
        comboBoxCriterions2Method2.addActionListener(
                e1 -> {
                    int cr = comboBoxCriterions2Method2.getSelectedIndex();
                    if (prevComboBoxValue2Method2 > -1 && prevComboBoxValue2Method2 != cr) {
                        tableModel3Method2.setDataVector(
                                appController.getDataTable3Method2(cr),
                                appController.getObjectHeadersForTable(listModelObjects));
                        tableModel4Method2.setDataVector(
                                appController.getDataTable4Method2(cr),
                                appController.getSumHeadersForTable());
                        tableModel5Method2.setDataVector(
                                appController.getDataTable5Method2(cr),
                                appController.getRangHeadersForTable());
                        labelBestObjValue2Method2.setText(appController.getResult2Method2(cr));
                    }
                    prevComboBoxValue2Method2 = cr;
                });

        // Buttons
        showResultsMethod2 = new JButton("Показать ход решения");
        showResultsMethod2.setBounds(640, 620, 200, 30);
        panelMethod2.add(showResultsMethod2);
        showResultsMethod2.addActionListener(
                e1 -> {
                    new ResultForm(
                            "Ход выполнения обобщенной ранжировки на основе ранговой корреляции",
                            Method2a.FILENAME);
                    new ResultForm(
                            "Ход выполнения обобщенной ранжировки на основе парных сравнений",
                            Method2b.FILENAME);
                });

        getContentPane().add(panelMethod2);
    }

    private void buildPanelMethod3() {
        panelMethod3 = new JPanel();
        panelMethod3.setLayout(null);

        // Labels
        JLabel labelTitle = new JLabel("Оценка согласованности мнений эксперта");
        labelTitle.setBounds(300, 20, 500, 20);
        labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.PLAIN, 16));
        panelMethod3.add(labelTitle);

        JLabel labelDispersion = new JLabel("1. Расчет дисперсионного коэффициента конкордации");
        labelDispersion.setBounds(20, 60, 500, 15);
        panelMethod3.add(labelDispersion);

        JLabel labelDispersion2 = new JLabel("а) без учета связанных рангов");
        labelDispersion2.setBounds(30, 90, 200, 15);
        panelMethod3.add(labelDispersion2);

        JLabel labelDispersion3 = new JLabel("б) с учетом связанных рангов");
        labelDispersion3.setBounds(450, 90, 200, 15);
        panelMethod3.add(labelDispersion3);

        JLabel labelDispersion4 = new JLabel("Выберите уровень значимости");
        labelDispersion4.setBounds(20, 325, 200, 15);
        panelMethod3.add(labelDispersion4);

        JLabel labelEntropy = new JLabel("2. Расчет энтропийного коэффициента конкордации");
        labelEntropy.setBounds(20, 370, 500, 15);
        panelMethod3.add(labelEntropy);

        // ComboBoxes
        comboBoxMethod3 = new JComboBox();
        comboBoxMethod3.setBounds(220, 320, 110, 20);
        panelMethod3.add(comboBoxMethod3);
        String[] mas = new String[] {"0.01", "0.025", "0,05", "0,95", "0,975", "0,99"};
        prevComboBoxValueMethod3 = -1;
        for (String val : mas) {
            comboBoxMethod3.addItem(val);
        }
        comboBoxMethod3.setSelectedIndex(2);
        prevComboBoxValueMethod3 = 0;
        comboBoxMethod3.addActionListener(
                e1 -> {
                    int cr = comboBoxMethod3.getSelectedIndex();
                    if (prevComboBoxValueMethod3 > -1 && prevComboBoxValueMethod3 != cr) {
                        appController.calculateMethod3(
                                comboBoxMethod3.getSelectedIndex(),
                                comboBoxMethod3.getSelectedItem().toString());
                        textAreaMethod3.setText(appController.getResultMethod3());
                    }
                    prevComboBoxValueMethod3 = cr;
                });

        // Tables
        tableModel1Method3 =
                new DefaultTableModel(
                        appController.getDataTable1Method3(),
                        appController.getHeadersTable1Method3());
        table1Method3 = new JTable(tableModel1Method3);
        table1Method3.setBounds(30, 120, 300, 150);
        scrollPaneTable1Method3 = new JScrollPane(table1Method3);
        scrollPaneTable1Method3.setBounds(30, 120, 300, 150);
        scrollPaneTable1Method3.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTable1Method3.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod3.add(scrollPaneTable1Method3);

        tableModel2Method3 =
                new DefaultTableModel(
                        appController.getDataTable2Method3(),
                        appController.getHeadersTable2Method3());
        table2Method3 = new JTable(tableModel2Method3);
        table2Method3.setBounds(450, 120, 300, 150);
        scrollPaneTable2Method3 = new JScrollPane(table2Method3);
        scrollPaneTable2Method3.setBounds(450, 120, 300, 150);
        scrollPaneTable2Method3.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTable2Method3.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod3.add(scrollPaneTable2Method3);

        tableModel3Method3 =
                new DefaultTableModel(
                        appController.getDataTable3Method3(),
                        appController.getHeadersTable3Method3());
        table3Method3 = new JTable(tableModel3Method3);
        table3Method3.setBounds(30, 400, 300, 150);
        scrollPaneTable3Method3 = new JScrollPane(table3Method3);
        scrollPaneTable3Method3.setBounds(30, 400, 300, 150);
        scrollPaneTable3Method3.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTable3Method3.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod3.add(scrollPaneTable3Method3);

        // TextAreas
        textAreaMethod3 = new JTextArea();
        textAreaMethod3.setBounds(370, 320, 460, 230);
        textAreaMethod3.setText(appController.getResultMethod3());
        scrollPaneTextAreaMethod3 = new JScrollPane(textAreaMethod3);
        scrollPaneTextAreaMethod3.setBounds(370, 320, 460, 230);
        scrollPaneTextAreaMethod3.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTextAreaMethod3.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod3.add(scrollPaneTextAreaMethod3);

        // Buttons
        showResultsMethod3 = new JButton("Показать ход решения");
        showResultsMethod3.setBounds(640, 620, 200, 30);
        panelMethod3.add(showResultsMethod3);
        showResultsMethod3.addActionListener(
                e1 -> {
                    new ResultForm(
                            "Ход расчета оценки согласованности мнений экспертов",
                            Method3.FILENAME);
                });

        getContentPane().add(panelMethod3);
    }

    private void buildPanelMethod4() {
        panelMethod4 = new JPanel();
        panelMethod4.setLayout(null);

        // Labels
        JLabel labelTitle = new JLabel("Обработка парных сравнений объектов");
        labelTitle.setBounds(300, 20, 500, 20);
        labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.PLAIN, 16));
        panelMethod4.add(labelTitle);

        JLabel labelCriterion = new JLabel("Выберите критерий из списка: ");
        labelCriterion.setBounds(20, 60, 200, 15);
        panelMethod4.add(labelCriterion);

        JLabel labelX = new JLabel("Матрица математических ожиданий оценок пар объектов");
        labelX.setBounds(20, 90, 500, 15);
        panelMethod4.add(labelX);

        JLabel labelX2 =
                new JLabel("Матрица математических ожиданий оценок пар объектов в квадрате");
        labelX2.setBounds(20, 345, 500, 15);
        panelMethod4.add(labelX2);

        JLabel labelBestObj = new JLabel("Наиболее предпочтительный объект: ");
        labelBestObj.setBounds(30, 590, 300, 20);
        labelBestObj.setFont(new Font(labelBestObj.getFont().getName(), Font.PLAIN, 16));
        panelMethod4.add(labelBestObj);

        labelBestObjValueMethod4 = new JLabel(appController.getResultMethod4(0));
        labelBestObjValueMethod4.setBounds(320, 590, 300, 20);
        labelBestObjValueMethod4.setFont(
                new Font(labelBestObjValueMethod4.getFont().getName(), Font.PLAIN, 16));
        panelMethod4.add(labelBestObjValueMethod4);

        // Tables
        tableModel1Method4 =
                new DefaultTableModel(
                        appController.getDataTable1Method4(0),
                        appController.getObjectHeadersForTable(listModelObjects));
        table1Method4 = new JTable(tableModel1Method4);
        table1Method4.setBounds(20, 120, 655, 200);
        scrollPaneTable1Method4 = new JScrollPane(table1Method4);
        scrollPaneTable1Method4.setBounds(20, 120, 655, 200);
        scrollPaneTable1Method4.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTable1Method4.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod4.add(scrollPaneTable1Method4);

        tableModel2Method4 =
                new DefaultTableModel(
                        appController.getDataTable2Method4(0),
                        appController.getHeadersTable2Method4());
        table2Method4 = new JTable(tableModel2Method4);
        table2Method4.setBounds(685, 120, 150, 200);
        scrollPaneTable2Method4 = new JScrollPane(table2Method4);
        scrollPaneTable2Method4.setBounds(685, 120, 150, 200);
        scrollPaneTable2Method4.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod4.add(scrollPaneTable2Method4);

        tableModel3Method4 =
                new DefaultTableModel(
                        appController.getDataTable3Method4(0),
                        appController.getObjectHeadersForTable(listModelObjects));
        table3Method4 = new JTable(tableModel3Method4);
        table3Method4.setBounds(20, 375, 655, 200);
        scrollPaneTable3Method4 = new JScrollPane(table3Method4);
        scrollPaneTable3Method4.setBounds(20, 375, 655, 200);
        scrollPaneTable3Method4.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTable3Method4.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod4.add(scrollPaneTable3Method4);

        tableModel4Method4 =
                new DefaultTableModel(
                        appController.getDataTable4Method4(0),
                        appController.getHeadersTable4Method4());
        table4Method4 = new JTable(tableModel4Method4);
        table4Method4.setBounds(685, 375, 150, 200);
        scrollPaneTable4Method4 = new JScrollPane(table4Method4);
        scrollPaneTable4Method4.setBounds(685, 375, 150, 200);
        scrollPaneTable4Method4.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod4.add(scrollPaneTable4Method4);

        // ComboBoxes
        comboBoxCriterionsMethod4 = new JComboBox(listModelCriterions.toArray());
        comboBoxCriterionsMethod4.setBounds(220, 55, 150, 20);
        panelMethod4.add(comboBoxCriterionsMethod4);
        comboBoxCriterionsMethod4.setVisible(false);
        prevComboBoxValueMethod4 = -1;
        comboBoxCriterionsMethod4.setVisible(true);
        comboBoxCriterionsMethod4.removeAllItems();
        for (Object criterion : listModelCriterions.toArray()) {
            comboBoxCriterionsMethod4.addItem(criterion.toString());
        }
        prevComboBoxValueMethod4 = 0;
        comboBoxCriterionsMethod4.addActionListener(
                e1 -> {
                    int cr = comboBoxCriterionsMethod4.getSelectedIndex();
                    if (prevComboBoxValueMethod4 > -1 && prevComboBoxValueMethod4 != cr) {
                        tableModel1Method4.setDataVector(
                                appController.getDataTable1Method4(cr),
                                appController.getObjectHeadersForTable(listModelObjects));
                        tableModel2Method4.setDataVector(
                                appController.getDataTable2Method4(cr),
                                appController.getHeadersTable2Method4());
                        tableModel3Method4.setDataVector(
                                appController.getDataTable3Method4(cr),
                                appController.getObjectHeadersForTable(listModelObjects));
                        tableModel4Method4.setDataVector(
                                appController.getDataTable4Method4(cr),
                                appController.getHeadersTable4Method4());
                        labelBestObjValueMethod4.setText(appController.getResultMethod4(cr));
                    }
                    prevComboBoxValueMethod4 = cr;
                });

        // Buttons
        showResultsMethod4 = new JButton("Показать ход решения");
        showResultsMethod4.setBounds(640, 620, 200, 30);
        panelMethod4.add(showResultsMethod4);
        showResultsMethod4.addActionListener(
                e1 -> {
                    new ResultForm("Ход обработки парных сравнений объектов", Method4.FILENAME);
                });

        getContentPane().add(panelMethod4);
    }

    private void buildPanelMethod5() {
        panelMethod5 = new JPanel();
        panelMethod5.setLayout(null);

        // Labels
        JLabel labelTitle = new JLabel("Определение взаимосвязи ранжировок");
        labelTitle.setBounds(300, 20, 500, 20);
        labelTitle.setFont(new Font(labelTitle.getFont().getName(), Font.PLAIN, 16));
        panelMethod5.add(labelTitle);

        JLabel labelCriterion = new JLabel("Выберите критерий из списка: ");
        labelCriterion.setBounds(20, 60, 200, 15);
        panelMethod5.add(labelCriterion);

        JLabel labelSpearman = new JLabel("1. Матрица коэффициентов ранговой корреляции Спирмена");
        labelSpearman.setBounds(20, 90, 500, 15);
        panelMethod5.add(labelSpearman);

        labelSpearmanResult = new JLabel("");
        labelSpearmanResult.setBounds(30, 320, 600, 20);
        labelSpearmanResult.setFont(
                new Font(labelSpearmanResult.getFont().getName(), Font.PLAIN, 16));
        panelMethod5.add(labelSpearmanResult);

        JLabel labelKendall = new JLabel("2. Матрица коэффициентов ранговой корреляции Кендалла");
        labelKendall.setBounds(20, 365, 500, 15);
        panelMethod5.add(labelKendall);

        labelKendallResult = new JLabel("");
        labelKendallResult.setBounds(30, 590, 600, 20);
        labelKendallResult.setFont(
                new Font(labelKendallResult.getFont().getName(), Font.PLAIN, 16));
        panelMethod5.add(labelKendallResult);

        // Tables
        tableModel1Method5 =
                new DefaultTableModel(
                        appController.getDataTable1Method5(0),
                        appController.getExpertHeadersForTable(listModelExperts));
        table1Method5 = new JTable(tableModel1Method5);
        table1Method5.setBounds(20, 120, 555, 200);
        scrollPaneTable1Method5 = new JScrollPane(table1Method5);
        scrollPaneTable1Method5.setBounds(20, 120, 555, 200);
        scrollPaneTable1Method5.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTable1Method5.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod5.add(scrollPaneTable1Method5);

        tableModel2Method5 =
                new DefaultTableModel(
                        appController.getDataTable2Method5(0),
                        appController.getExpertHeadersForTable(listModelExperts));
        table2Method5 = new JTable(tableModel2Method5);
        table2Method5.setBounds(20, 395, 555, 200);
        scrollPaneTable2Method5 = new JScrollPane(table2Method5);
        scrollPaneTable2Method5.setBounds(20, 395, 555, 200);
        scrollPaneTable2Method5.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTable2Method5.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod5.add(scrollPaneTable2Method5);

        // TextAreas
        textArea1Method5 = new JTextArea();
        textArea1Method5.setBounds(595, 120, 265, 200);
        textArea1Method5.setText(appController.getResult1Method5());
        scrollPaneTextArea1Method5 = new JScrollPane(textArea1Method5);
        scrollPaneTextArea1Method5.setBounds(595, 120, 265, 200);
        scrollPaneTextArea1Method5.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTextArea1Method5.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod5.add(scrollPaneTextArea1Method5);

        textArea2Method5 = new JTextArea();
        textArea2Method5.setBounds(595, 395, 265, 200);
        textArea2Method5.setText(appController.getResult2Method5());
        scrollPaneTextArea2Method5 = new JScrollPane(textArea2Method5);
        scrollPaneTextArea2Method5.setBounds(595, 395, 265, 200);
        scrollPaneTextArea2Method5.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTextArea2Method5.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelMethod5.add(scrollPaneTextArea2Method5);

        // ComboBoxes
        comboBoxCriterionsMethod5 = new JComboBox(listModelCriterions.toArray());
        comboBoxCriterionsMethod5.setBounds(220, 55, 150, 20);
        panelMethod5.add(comboBoxCriterionsMethod5);
        comboBoxCriterionsMethod5.setVisible(false);
        prevComboBoxValueMethod5 = -1;
        comboBoxCriterionsMethod5.setVisible(true);
        comboBoxCriterionsMethod5.removeAllItems();
        for (Object criterion : listModelCriterions.toArray()) {
            comboBoxCriterionsMethod5.addItem(criterion.toString());
        }
        prevComboBoxValueMethod5 = 0;
        comboBoxCriterionsMethod5.addActionListener(
                e1 -> {
                    int cr = comboBoxCriterionsMethod5.getSelectedIndex();
                    if (prevComboBoxValueMethod5 > -1 && prevComboBoxValueMethod5 != cr) {
                        tableModel1Method5.setDataVector(
                                appController.getDataTable1Method5(cr),
                                appController.getExpertHeadersForTable(listModelExperts));
                        tableModel2Method5.setDataVector(
                                appController.getDataTable2Method5(cr),
                                appController.getExpertHeadersForTable(listModelExperts));
                    }
                    prevComboBoxValueMethod5 = cr;
                });

        // Buttons
        showResultsMethod5 = new JButton("Показать ход решения");
        showResultsMethod5.setBounds(640, 620, 200, 30);
        panelMethod5.add(showResultsMethod5);
        showResultsMethod5.addActionListener(
                e1 -> {
                    new ResultForm("Ход определения взаимосвязи ранжировок", Method5.FILENAME);
                });

        getContentPane().add(panelMethod5);
    }

    public void setEnabledStepOne(boolean f) {
        buttonNext.setEnabled(f);
        listExperts.setEnabled(f);
        listExpertsK.setEnabled(f);
        listObjects.setEnabled(f);
        listCriterions.setEnabled(f);
        buttonAddExpert.setEnabled(f);
        buttonAddObject.setEnabled(f);
        buttonAddCriterion.setEnabled(f);
        buttonDelExpert.setEnabled(f);
        buttonDelObject.setEnabled(f);
        buttonDelCriterion.setEnabled(f);
        textFieldExperts.setEnabled(f);
        textFieldExpertsK.setEnabled(f);
        textFieldObjects.setEnabled(f);
        textFieldCriterions.setEnabled(f);
        buttonGoToCalculate.setEnabled(f);
    }

    public void setEnabledStepTwo(boolean f) {
        tableMarks.setEnabled(f);
        buttonSaveMarks.setEnabled(f);
        comboBoxCriterions.setEnabled(f);
        buttonPrev.setEnabled(f);
        buttonGoToCalculate.setEnabled(f);
    }
}
