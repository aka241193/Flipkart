package services;

import models.*;
import java.util.*;

public class LeaderBoardServiceImpl {
    private Game game;
    private GameServiceImpl gameServiceImpl;
    private int globalRank = 0;

    public LeaderBoardServiceImpl(Game game, GameServiceImpl gameServiceImpl) {
        this.game = game;
        this.gameServiceImpl = gameServiceImpl;
    }

    public void upsertUser(String name, String country, String email) {
        User user = new User(name, country, email);
        gameServiceImpl.addUser(game, user);
        System.out.println(user.getName() + " added to the leaderboard");
    }

    public void upsertScore(String email, String score) {
        try {
            int intScore = Integer.parseInt(score);
            List<User> users = game.getUsers();
            User user = gameServiceImpl.getUserByEmail(users, email);
            user.setScore(intScore);
            user.setRank(globalRank);
            globalRank++;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Not a valid score");
        }
    }

    public void getTopKUsers(int k, String country) {
        PriorityQueue<User> pq = new PriorityQueue<>(new Comparator<User>(){
            public int compare(User u1, User u2) {
                if (u1.getScore() == u2.getScore()) 
                    return u2.getRank() - u1.getRank();
                return u1.getScore() - u2.getScore();
            }
        });
        List<User> users = game.getUsers();
        List<User> userByCountry = gameServiceImpl.getUsersByCountry(users, country);
        for (User user: userByCountry) {
            pq.offer(user);
            if (pq.size() > k) pq.poll();
        }

        List<User> result = new ArrayList<>();
        while (!pq.isEmpty() ) result.add(pq.poll());
        Collections.reverse(result);

        for (User user: result) {
            System.out.println(user.getEmail() + " => " + user.getScore());
        }
    }

    public void getUsersByScore(int score) {
        List<User> users = game.getUsers();
        List<User> usersByScore = gameServiceImpl.getUsersByScore(users, score);
        System.out.println("Users with score " + score + " are :");
        for (User user: usersByScore) {
            System.out.println(user.getEmail());
        }
    }

    public void searchUser(String name, int score, String country) {
        List<User> users = game.getUsers();
        if (!name.isEmpty()) users = gameServiceImpl.getUsersByName(users, name);
        if (score != -1) users = gameServiceImpl.getUsersByScore(users, score);
        if (!country.isEmpty())users = gameServiceImpl.getUsersByCountry(users, country);

        for (User user: users) {
            System.out.println(user.getEmail());
        } 
    }
}
