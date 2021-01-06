import models.*;
import services.*;
import java.util.*;

public class Driver {
    public static void main (String argsStr[]) {
        // UPSERT_USER, Nikhil, India, nikhil@flipkart.com
        // UPSERT_USER, Rahul, India, rahul@flipkart.com
        // UPSERT_SCORE, rahul@flipkart.com, 1
        // UPSERT_SCORE, nikhil@flipkart.com, 5
        // UPSERT_USER, Karan, Argentina, karan@flipkart.com
        // UPSERT_SCORE, karan@flipkart.com, 1
        // GET_TOP, 3
        // GET_USERS_WITH_SCORE, 1
        // GET_TOP, 2, India
        // SEARCH, GET_TOP, 2, IndiaNikhil, null, India 
        // SEARCH,null, null, India

        Game game = new Game();
        GameServiceImpl gameService = new GameServiceImpl();
        LeaderBoardServiceImpl leaderBoardService = new LeaderBoardServiceImpl(game, gameService);
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String input = sc.nextLine();
                String[] args = input.split(",\\s*");
                String command = args[0];
                if (command.equals("UPSERT_USER")) {
                    String name = args[1];
                    String country = args[2];
                    String email  = args[3];
                    leaderBoardService.upsertUser(name, country, email);
                } else if (command.equals("UPSERT_SCORE")) {
                    String email  = args[1];
                    String score = args[2];
                    leaderBoardService.upsertScore(email, score);
                } else if (command.equals("GET_TOP")) {
                    int k  = Integer.parseInt(args[1]);
                    String country = "";
                    if (args.length > 2) country = args[2];
                    leaderBoardService.getTopKUsers(k, country);
                } else if (command.equals("GET_USERS_WITH_SCORE")) {
                    int score  = Integer.parseInt(args[1]);
                    leaderBoardService.getUsersByScore(score);
                } else if (command.equals("SEARCH")) {
                    String name = "";
                    if (!args[1].equals("null")) name  = args[1];
                    int score = -1;
                    if (!args[2].equals("null"))
                        score  = Integer.parseInt(args[2]);
                    String country = "";
                    if (!args[3].equals("null")) country = args[3];
                    
                    leaderBoardService.searchUser(name, score, country);
                } else {
                    System.out.println(command);
                    break;
                }
            } catch(Exception e) {
                System.out.println("Invalid input");
            }
        }

        sc.close();
    }
}
