package net.thebookofcode.www.restapijava.entities;

import com.google.gson.annotations.SerializedName;

public class Comment {

    private int id;
    private Integer postId;
    private  String name;
    private  String email;
    @SerializedName("body")
    private String text;

    public int getId() {
        return id;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}
