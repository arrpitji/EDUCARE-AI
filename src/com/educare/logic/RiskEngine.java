package com.educare.logic;

import com.educare.model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * Rule-based Weighted Risk Scoring System
 */
public class RiskEngine {

    // Weights
    private static final double W_ATTENDANCE = 25.0;
    private static final double W_MARKS = 30.0;
    private static final double W_ASSIGNMENTS = 15.0;
    private static final double W_BACKLOGS = 15.0;
    private static final double W_STRESS = 10.0;
    private static final double W_SLEEP = 5.0;

    // Thresholds for Max Risk Impact
    private static final int MAX_BACKLOGS_FOR_CALC = 5; // 5 or more backlogs = max risk contribution

    public RiskResult analyze(Student s) {
        double totalRisk = 0.0;
        List<String> reasons = new ArrayList<>();

        // 1. Attendance Risk (Lower is worse)
        // If 100% attendance, risk is 0. If 0% attendance, risk is W_ATTENDANCE.
        double attendanceRisk = ((100.0 - s.getAttendance()) / 100.0) * W_ATTENDANCE;
        totalRisk += attendanceRisk;
        if (s.getAttendance() < 75) {
            reasons.add(String.format("Low attendance detected (%.1f%%)", s.getAttendance()));
        }

        // 2. Marks Risk (Lower is worse)
        double marksRisk = ((100.0 - s.getMarks()) / 100.0) * W_MARKS;
        totalRisk += marksRisk;
        if (s.getMarks() < 50) {
            reasons.add(String.format("Poor academic performance (Avg Marks: %.1f%%)", s.getMarks()));
        }

        // 3. Assignment Risk (Lower is worse)
        double assignRisk = ((100.0 - s.getAssignmentCompletion()) / 100.0) * W_ASSIGNMENTS;
        totalRisk += assignRisk;
        if (s.getAssignmentCompletion() < 60) {
            reasons.add("Low assignment completion rate");
        }

        // 4. Backlogs Risk (Higher is worse)
        // Cap calculation at MAX_BACKLOGS_FOR_CALC
        int cappedBacklogs = Math.min(s.getBacklogs(), MAX_BACKLOGS_FOR_CALC);
        double backlogRisk = ((double) cappedBacklogs / MAX_BACKLOGS_FOR_CALC) * W_BACKLOGS;
        totalRisk += backlogRisk;
        if (s.getBacklogs() > 0) {
            reasons.add(String.format("Has active backlogs (%d)", s.getBacklogs()));
        }

        // 5. Stress Risk (Higher is worse) (Scale 1-10)
        double stressRisk = (s.getStressLevel() / 10.0) * W_STRESS;
        totalRisk += stressRisk;
        if (s.getStressLevel() >= 7) {
            reasons.add("High stress level reported");
        }

        // 6. Sleep Risk (Lower is worse) (Scale 0-10)
        double sleepRisk = ((10.0 - s.getSleepHours()) / 10.0) * W_SLEEP;
        totalRisk += sleepRisk;
        if (s.getSleepHours() < 6) {
            reasons.add("Insufficient sleep pattern");
        }

        // Cap Total Risk at 100 (though logic above sums to 100 max)
        totalRisk = Math.min(100.0, Math.max(0.0, totalRisk));

        // Determine Level
        String level;
        if (totalRisk < 40) {
            level = "SAFE";
        } else if (totalRisk < 70) {
            level = "AT RISK";
        } else {
            level = "CRITICAL";
        }

        if (reasons.isEmpty() && totalRisk < 40) {
            reasons.add("Student performance and wellbeing indicators are good.");
        }

        return new RiskResult(totalRisk, level, reasons);
    }
}
