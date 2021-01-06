package models;

import java.util.UUID;

public class User {
    private String userId;
    private String name;
    private String email;
    private String country;
    private int score;
    private int rank;

    public User(String name, String country, String email) {
        userId = UUID.randomUUID().toString();
        this.name = name;
        this.country = country;
        this.email = email;
        this.score = 0;
        this.rank = 0;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
