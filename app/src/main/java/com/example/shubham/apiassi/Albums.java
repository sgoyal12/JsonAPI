package com.example.shubham.apiassi;

/**
 * Created by shubham on 7/10/2018.
 */

public class Albums
{
    private String title;
    private int userId;
    private int id;

    public Albums(String title, int userId, int id) {
        this.title = title;
        this.userId = userId;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
