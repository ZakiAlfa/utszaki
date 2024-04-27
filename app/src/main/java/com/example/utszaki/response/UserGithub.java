package com.example.utszaki.response;

import com.google.gson.annotations.SerializedName;

public class UserGithub {
    @SerializedName("login")
    private String username;
    @SerializedName("avatar_url")
    private String avatarUrl;
    private String name;
    private String bio;
    private String location;
    private String email;
    private String followers;
    private String following;

    public String getUsername() {return username; }

    public String getAvatarUrl() {return avatarUrl; }
    public String getName() {return name;}
    public String getBio() { return bio; }
    public String getLocation() { return location; }
    public String getEmail() { return email; }
    public String getfollowers() { return followers; }
    public String getfollowing() { return following; }
}
