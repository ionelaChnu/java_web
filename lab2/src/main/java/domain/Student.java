package domain;

import java.io.Serializable;

public class Student implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String groupNumber;

    private int mathMark;

    private int historyMark;

    private int musicMark;

    public Long getId() {
        return id;
    }

    public Student setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Student setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Student setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public Student setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
        return this;
    }

    public int getMathMark() {
        return mathMark;
    }

    public Student setMathMark(int mathMark) {
        this.mathMark = mathMark;
        return this;
    }

    public int getHistoryMark() {
        return historyMark;
    }

    public Student setHistoryMark(int historyMark) {
        this.historyMark = historyMark;
        return this;
    }

    public int getMusicMark() {
        return musicMark;
    }

    public Student setMusicMark(int musicMark) {
        this.musicMark = musicMark;
        return this;
    }
}
