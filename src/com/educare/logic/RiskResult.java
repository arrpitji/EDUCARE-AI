package com.educare.logic;

import java.util.ArrayList;
import java.util.List;

public class RiskResult {
    private double totalScore;
    private String riskLevel;
    private List<String> explanations;

    public RiskResult(double totalScore, String riskLevel, List<String> explanations) {
        this.totalScore = totalScore;
        this.riskLevel = riskLevel;
        this.explanations = explanations;
    }

    public double getTotalScore() { return totalScore; }
    public String getRiskLevel() { return riskLevel; }
    public List<String> getExplanations() { return explanations; }
}
