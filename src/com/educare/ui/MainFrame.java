package com.educare.ui;

import com.educare.logic.DataManager;
import com.educare.logic.RiskEngine;
import com.educare.logic.RiskResult;
import com.educare.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    
    private InputPanel inputPanel;
    private OutputPanel outputPanel;
    private RiskEngine riskEngine;
    
    public MainFrame() {
        super("EDUCARE AI – Student Risk Analytics System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        riskEngine = new RiskEngine();
        
        // Components
        inputPanel = new InputPanel();
        outputPanel = new OutputPanel();
        
        // Layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, outputPanel);
        splitPane.setDividerLocation(500);
        add(splitPane, BorderLayout.CENTER);
        
        // Listeners
        inputPanel.btnAnalyze.addActionListener(this::onAnalyze);
        inputPanel.btnSave.addActionListener(this::onSave);
        inputPanel.btnClear.addActionListener(e -> {
            // Clear inputs logic if needed, simplified here
        });
    }
    
    private void onAnalyze(ActionEvent e) {
        try {
            Student s = getStudentFromForm();
            RiskResult result = riskEngine.analyze(s);
            outputPanel.updateResult(result.getTotalScore(), result.getRiskLevel(), result.getExplanations());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error processing data: " + ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void onSave(ActionEvent e) {
        try {
            Student s = getStudentFromForm();
            DataManager.saveStudent(s);
            JOptionPane.showMessageDialog(this, "Student record saved to CSV successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage(), "IO Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Student getStudentFromForm() {
        String id = inputPanel.txtId.getText();
        String name = inputPanel.txtName.getText();
        
        if (id.isEmpty() || name.isEmpty()) {
            throw new IllegalArgumentException("ID and Name are required.");
        }
        
        double attendance = (Double) inputPanel.spinAttendance.getValue();
        double marks = (Double) inputPanel.spinMarks.getValue();
        double assignments = (Double) inputPanel.spinAssignments.getValue();
        int backlogs = (Integer) inputPanel.spinBacklogs.getValue();
        int stress = inputPanel.sliderStress.getValue();
        double sleep = (Double) inputPanel.spinSleep.getValue();
        
        return new Student(id, name, attendance, marks, assignments, backlogs, stress, sleep);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Set Look and Feel
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            
            new MainFrame().setVisible(true);
        });
    }
}
