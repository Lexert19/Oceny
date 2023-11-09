package com.example.oceny;

public enum ActivityCode {
    MainActivity(0),
    GradesActivity(1);

    private final int value;
    ActivityCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
