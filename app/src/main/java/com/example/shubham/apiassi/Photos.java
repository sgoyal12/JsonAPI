package com.example.shubham.apiassi;

/**
 * Created by shubham on 7/10/2018.
 */

public class Photos {
    private String title;
    private int id;
    private int albumId;
    private String url;

    public Photos(String title, int id, int albumId, String url) {
        this.title = title;
        this.id = id;
        this.albumId = albumId;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
