package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultDTO {

    private String subject;

    private Map<Integer, Integer> markCount = new HashMap<>();

    private Double average;

    private List<Student> students = new ArrayList<>();

    public String getSubject() {
        return subject;
    }

    public ResultDTO setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Map<Integer, Integer> getMarkCount() {
        return markCount;
    }

    public ResultDTO setMarkCount(Map<Integer, Integer> markCount) {
        this.markCount = markCount;
        return this;
    }

    public Double getAverage() {
        return average;
    }

    public ResultDTO setAverage(Double average) {
        this.average = average;
        return this;
    }

    public List<Student> getStudents() {
        return students;
    }

    public ResultDTO setStudents(List<Student> students) {
        this.students = students;
        return this;
    }
}
