package id.co.imastudio.retrofitauthentication.model;

import com.google.gson.annotations.SerializedName;

public class TwitterResponse {
    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("text")
    private String text;

    @SerializedName("user")
    private User user;


    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}