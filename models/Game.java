package models;

import java.util.*;

public class Game {
    private List<User> users;
    // private int totalScore;

    public Game() { 
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
