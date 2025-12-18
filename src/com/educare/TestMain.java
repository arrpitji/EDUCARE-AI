package com.educare;

import com.educare.logic.DataManager;
import com.educare.logic.RiskEngine;
import com.educare.logic.RiskResult;
import com.educare.model.Student;

public class TestMain {
    public static void main(String[] args) {
        try {
            System.out.println("Running EDUCARE AI Logic Test...");

            // 1. Create Student
            Student s1 = new Student("S001", "John Doe", 50.0, 40.0, 30.0, 2, 8, 4.0);
            System.out.println("Created Student: " + s1.getName());

            // 2. Analyze Risk
            RiskEngine engine = new RiskEngine();
            RiskResult result = engine.analyze(s1);

            System.out.println("Risk Score: " + result.getTotalScore());
            System.out.println("Risk Level: " + result.getRiskLevel());
            System.out.println("Reasons: " + result.getExplanations());

            // 3. Save Data
            DataManager.saveStudent(s1);
            System.out.println("Saved student data.");

            // 4. Load Data
            System.out.println("Loading students...");
            for (Student s : DataManager.loadStudents()) {
                System.out.println("Loaded: " + s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
