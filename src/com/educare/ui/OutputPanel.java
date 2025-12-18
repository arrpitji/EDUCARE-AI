package com.educare.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OutputPanel extends JPanel {
    
    private JLabel lblScoreVal;
    private JLabel lblRiskLevel;
    private JTextArea txtExplanations;
    private JPanel meterPanel;

    public OutputPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Risk Analysis Report"));
        setPreferredSize(new Dimension(300, 0));

        // Top: Score and Level
        JPanel summaryPanel = new JPanel(new GridLayout(2, 1));
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        lblScoreVal = new JLabel("---", SwingConstants.CENTER);
        lblScoreVal.setFont(new Font("Arial", Font.BOLD, 48));
        
        lblRiskLevel = new JLabel("WAITING", SwingConstants.CENTER);
        lblRiskLevel.setFont(new Font("Arial", Font.BOLD, 24));
        lblRiskLevel.setOpaque(true);
        lblRiskLevel.setBackground(Color.LIGHT_GRAY);
        
        summaryPanel.add(lblScoreVal);
        summaryPanel.add(lblRiskLevel);
        
        add(summaryPanel, BorderLayout.NORTH);

        // Center: Explanations
        txtExplanations = new JTextArea();
        txtExplanations.setEditable(false);
        txtExplanations.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtExplanations.setLineWrap(true);
        txtExplanations.setWrapStyleWord(true);
        
        JScrollPane scroll = new JScrollPane(txtExplanations);
        scroll.setBorder(BorderFactory.createTitledBorder("Model Explanations"));
        add(scroll, BorderLayout.CENTER);
    }

    public void updateResult(double score, String level, List<String> reasons) {
        lblScoreVal.setText(String.format("%.1f", score));
        lblRiskLevel.setText(level);
        
        switch (level) {
            case "SAFE":
                lblRiskLevel.setBackground(new Color(144, 238, 144)); // Light Green
                break;
            case "AT RISK":
                lblRiskLevel.setBackground(new Color(255, 215, 0)); // Gold
                break;
            case "CRITICAL":
                lblRiskLevel.setBackground(new Color(255, 99, 71)); // Tomato Red
                break;
            default:
                lblRiskLevel.setBackground(Color.LIGHT_GRAY);
        }

        StringBuilder sb = new StringBuilder();
        if (reasons != null) {
            for (String r : reasons) {
                sb.append("• ").append(r).append("\n");
            }
        }
        txtExplanations.setText(sb.toString());
    }
    
    public void clear() {
        lblScoreVal.setText("---");
        lblRiskLevel.setText("WAITING");
        lblRiskLevel.setBackground(Color.LIGHT_GRAY);
        txtExplanations.setText("");
    }
}
