package services;

import java.util.*;

import models.*;
import exceptions.*;

public class GameServiceImpl {
    public void addUser(Game game, User newUser) {
        try {
            List<User> users = game.getUsers();
            for (User user : users) 
                if (user.getEmail().equals(newUser.getEmail())) throw new UserPresentException("User already present");
            
            users.add(newUser);
        } catch (UserPresentException e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(List<User> users, String email) {
        try {
            for (User user : users) 
                if (user.getEmail().equals(email)) return user;
            
            throw new UserPresentException("User not present");
        } catch (UserPresentException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> getUsersByCountry(List<User> users, String country) {
        List<User> usersByCountry = new ArrayList<>();
        if (country.isEmpty()) return users;
        for (User user : users) 
            if (user.getCountry().equals(country)) usersByCountry.add(user);

        return usersByCountry;
    }

    public List<User> getUsersByScore(List<User> users, int score) {
        List<User> usersByScore = new ArrayList<>();
        for (User user : users) 
            if (user.getScore() == score) usersByScore.add(user);

        return usersByScore;
    }

    public List<User> getUsersByName(List<User> users, String name) {
        List<User> usersByName = new ArrayList<>();
        for (User user : users) 
            if (user.getName().equals(name)) usersByName.add(user);

        return usersByName;
    } 
}
