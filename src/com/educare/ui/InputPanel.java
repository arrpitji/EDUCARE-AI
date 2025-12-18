package com.educare.ui;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    
    public JTextField txtId = new JTextField(15);
    public JTextField txtName = new JTextField(15);
    
    // Percentages
    public JSpinner spinAttendance = new JSpinner(new SpinnerNumberModel(75.0, 0.0, 100.0, 1.0));
    public JSpinner spinMarks = new JSpinner(new SpinnerNumberModel(65.0, 0.0, 100.0, 1.0));
    public JSpinner spinAssignments = new JSpinner(new SpinnerNumberModel(80.0, 0.0, 100.0, 1.0));
    
    // Integers / Scales
    public JSpinner spinBacklogs = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
    public JSlider sliderStress = new JSlider(1, 10, 5);
    public JSpinner spinSleep = new JSpinner(new SpinnerNumberModel(7.0, 0.0, 24.0, 0.5));

    public JButton btnAnalyze = new JButton("Analyze Risk");
    public JButton btnSave = new JButton("Save Record");
    public JButton btnClear = new JButton("Clear Form");

    public InputPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Student Details"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Config labels and components
        addLabelAndComp("Student ID:", txtId, 0, gbc);
        addLabelAndComp("Name:", txtName, 1, gbc);
        addLabelAndComp("Attendance (%):", spinAttendance, 2, gbc);
        addLabelAndComp("Marks (%):", spinMarks, 3, gbc);
        addLabelAndComp("Assignments (%):", spinAssignments, 4, gbc);
        addLabelAndComp("Backlogs:", spinBacklogs, 5, gbc);
        addLabelAndComp("Sleep (Hrs):", spinSleep, 6, gbc);
        
        // Stress Slider
        gbc.gridx = 0; gbc.gridy = 7;
        add(new JLabel("Stress Level (1-10):"), gbc);
        gbc.gridx = 1;
        sliderStress.setMajorTickSpacing(1);
        sliderStress.setPaintTicks(true);
        sliderStress.setPaintLabels(true);
        add(sliderStress, gbc);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(btnAnalyze);
        btnPanel.add(btnSave);
        btnPanel.add(btnClear);
        
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(btnPanel, gbc);
        
        btnAnalyze.setBackground(new Color(70, 130, 180));
        btnAnalyze.setForeground(Color.WHITE);
        btnAnalyze.setFocusPainted(false);
    }

    private void addLabelAndComp(String text, JComponent comp, int y, GridBagConstraints gbc) {
        gbc.gridx = 0; gbc.gridy = y;
        gbc.gridwidth = 1;
        add(new JLabel(text), gbc);
        
        gbc.gridx = 1;
        add(comp, gbc);
    }
}
