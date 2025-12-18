package com.educare.logic;

import com.educare.model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String FILE_PATH = "student_data.csv";
    private static final String HEADER = "ID,Name,Attendance,Marks,Assignments,Backlogs,Stress,Sleep";

    public static void saveStudent(Student s) throws IOException {
        File file = new File(FILE_PATH);
        boolean isNewFile = !file.exists();

        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
            if (isNewFile) {
                pw.println(HEADER);
            }
            pw.printf("%s,%s,%.2f,%.2f,%.2f,%d,%d,%.2f%n",
                    s.getId(),
                    s.getName(),
                    s.getAttendance(),
                    s.getMarks(),
                    s.getAssignmentCompletion(),
                    s.getBacklogs(),
                    s.getStressLevel(),
                    s.getSleepHours()
            );
        }
    }

    public static List<Student> loadStudents() throws IOException {
        List<Student> students = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return students;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    try {
                        String id = parts[0];
                        String name = parts[1];
                        double attendance = Double.parseDouble(parts[2]);
                        double marks = Double.parseDouble(parts[3]);
                        double assignments = Double.parseDouble(parts[4]);
                        int backlogs = Integer.parseInt(parts[5]);
                        int stress = Integer.parseInt(parts[6]);
                        double sleep = Double.parseDouble(parts[7]);

                        students.add(new Student(id, name, attendance, marks, assignments, backlogs, stress, sleep));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid record: " + line);
                    }
                }
            }
        }
        return students;
    }
}
