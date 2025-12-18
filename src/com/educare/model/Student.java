package com.educare.model;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private double attendance; // 0-100
    private double marks; // 0-100
    private double assignmentCompletion; // 0-100
    private int backlogs;
    private int stressLevel; // 1-10
    private double sleepHours; // 0-10

    public Student() {}

    public Student(String id, String name, double attendance, double marks, double assignmentCompletion, int backlogs, int stressLevel, double sleepHours) {
        this.id = id;
        this.name = name;
        this.attendance = attendance;
        this.marks = marks;
        this.assignmentCompletion = assignmentCompletion;
        this.backlogs = backlogs;
        this.stressLevel = stressLevel;
        this.sleepHours = sleepHours;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getAttendance() { return attendance; }
    public void setAttendance(double attendance) { this.attendance = attendance; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    public double getAssignmentCompletion() { return assignmentCompletion; }
    public void setAssignmentCompletion(double assignmentCompletion) { this.assignmentCompletion = assignmentCompletion; }

    public int getBacklogs() { return backlogs; }
    public void setBacklogs(int backlogs) { this.backlogs = backlogs; }

    public int getStressLevel() { return stressLevel; }
    public void setStressLevel(int stressLevel) { this.stressLevel = stressLevel; }

    public double getSleepHours() { return sleepHours; }
    public void setSleepHours(double sleepHours) { this.sleepHours = sleepHours; }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
