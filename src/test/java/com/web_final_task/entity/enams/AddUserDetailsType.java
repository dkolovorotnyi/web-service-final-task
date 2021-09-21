package com.web_final_task.entity.enams;

public enum AddUserDetailsType {
    DB("db"), API("api");

    private String title;

    AddUserDetailsType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
