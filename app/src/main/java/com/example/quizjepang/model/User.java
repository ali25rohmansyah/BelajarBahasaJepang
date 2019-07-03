package com.example.quizjepang.model;

public class User {

    private long Score;

    public User() {
    }

    public User(long score) {
        Score = score;
    }

    public long getScore() {
        return Score;
    }

    public void setScore(long score) {
        Score = score;
    }
}
