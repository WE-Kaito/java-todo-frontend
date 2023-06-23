package com.example.backend.toDo;

enum Status {
    OPEN("Todo"),
    DOING("Doing"),
    DONE("Done");

    private final String label;

    Status(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
