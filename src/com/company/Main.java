package com.company;

import java.lang.management.GarbageCollectorMXBean;
import java.util.*;
import java.io.*;

public class Main {

    static Random rand = new Random();
    static Scanner scn = new Scanner(System.in);
    static ArrayList<GameResult> users = new ArrayList<>();

    public static void main(String[] args) {

        loadResults();

        int numOfAttempts = 10;

        System.out.println("Hello and Welcome to the " + '"' + "GUESS THE NUMBER" + '"');
        System.out.printf("Try to guess the number from 1 to 100 in less than %d turns\n\n", numOfAttempts);
        System.out.print("Press ENTER key to START!");

        do {

            scn.nextLine();
            String playerName = askPlayerName("What's your name?\n" +
                    "Please enter word without spaces", 3, 15);

            int myNum = rand.nextInt(100) + 1;
            boolean userWon = false;

            long timeStart = System.currentTimeMillis();

            for (int i = 0; i < numOfAttempts; i++) {
                System.out.println(myNum);
                int userNum = askInt("Enter your guess: ", 1, 100, i);

                if (userNum == myNum) {
                    userWon = true;

                    long timeStop = System.currentTimeMillis();

                    System.out.println("Number is " + myNum + " !");
                    System.out.println("Congratulations, you are right!");

                    GameResult r = new GameResult();
                    r.setName(playerName);
                    r.setTriesCount(i + 1);
                    r.setUserTime(timeStop - timeStart);
                    users.add(r);
                    break;
                } else if (myNum > userNum) {
                    System.out.println("Number is greater");
                } else if (myNum < userNum) {
                    System.out.println("Number is smaller");
                }
            }

            if (!userWon) {
                System.out.println("Number is " + myNum + "!");
                System.out.println("Sorry, you're out of luck");
            }

        } while (choseOption("Do you want to play again? (Y/N)"));

        showResults();
        //showResults2();
        saveResults();

        System.out.println("Goodbye !");
    }

    private static void loadResults() {
        File file = new File("myfile.txt");
        try (Scanner in = new Scanner(file)) {
            while (in.hasNext()) {
                GameResult r = new GameResult();
                String name = in.next();
                int tries = in.nextInt();
                long time = in.nextLong();

                r.setName(name);
                r.setTriesCount(tries);
                r.setUserTime(time);

                users.add(r);
            }
        } catch (IOException e) {
            System.out.println("Cannot read leader board");
        }
    }

    private static void showResults2() {
        users.stream()
                .sorted(Comparator
                        .comparingInt(GameResult::getTriesCount)
                        .thenComparing(GameResult::getUserTime))
                .limit(3)
                .forEach(r -> System.out.printf("Nickname:%s \t\t Attempts:%d\t Time:%f sec.\n",
                        r.getName(),
                        r.getTriesCount(),
                        r.getUserTime() / 1000.0));
    }

    private static void showResults() {
        users.sort(Comparator
                .comparing(GameResult::getTriesCount)
                .thenComparing(GameResult::getUserTime));

        for (GameResult result : users) {
            System.out.printf("Nickname:%s \t\t Attempts:%d\t Time:%d sec.\n",
                    result.getName(),
                    result.getTriesCount(),
                    result.getUserTime());
        }
    }

    //--Asking and checking if it's a number, from a player
    static int askInt(String msg, int min, int max, int attempt) {
        while (true) {
            try {
                System.out.println("Turn " + (attempt + 1) + ". " + msg);
                int answer = scn.nextInt();

                if (answer >= min && answer <= max) {
                    return answer;
                }
            } catch (InputMismatchException ex) {
                System.out.println("It isn't a number!");
                scn.next();
            }
            System.out.printf("ERROR! Please enter number from %d to %d!\n", min, max);
        }
    }

    //--Checking if player wants to continue
    static boolean choseOption(String msg) {
        while (true) {
            System.out.println(msg);
            String option = scn.next();
            boolean isY = option.equalsIgnoreCase("y");
            boolean isN = option.equalsIgnoreCase("n");

            if (isY || isN) {
                return isY;
            }
            System.out.println("Please chose Y or N");
        }
    }

    //--Asking player's name
    static String askPlayerName(String msg, int min, int max) {
        System.out.println(msg);
        while (true) {
            String inputName = scn.next();
            String userName = inputName.replace(" ", "");
            int nameLength = userName.length();

            if (nameLength > max) {
                System.out.printf("Sorry, but your name is to long!\nPlease input less than a %d characters\n", max);
            } else if (nameLength < min) {
                System.out.printf("Sorry, but your name is to short!\nPlease input at least %d characters\n", min);
            } else {
                System.out.println("Greetings " + '"' + userName + '"' + "!\n");
                return userName;
            }
        }
    }

    static void saveResults() {
        File file = new File("myfile.txt");
        try (PrintWriter out = new PrintWriter(file)) {
            for (GameResult result : users) {
                out.printf(" %s %d %d ",
                        result.getName(),
                        result.getTriesCount(),
                        result.getUserTime());
            }
        } catch (IOException e) {
            System.out.println("Cannot write in leader board");
        }
    }
}






