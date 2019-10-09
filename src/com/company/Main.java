package com.company;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random rand = new Random();
    static Scanner scn = new Scanner(System.in);


    public static void main(String[] args) {
        int playerMaxTurns = 10;

        System.out.println("Hello and Welcome to the " + '"' + "GUESS THE NUMBER" + '"');
        System.out.printf("Try to guess the number from 1 to 100 in less than %d turns\n\n", playerMaxTurns);

        String playerName = askPlayerName("What's your name?\n",3,15);

        do {
             playGame(playerMaxTurns);
        } while (choseOption("Do you want to play again? (Y/N)"));
        System.out.println("Goodbye " + '"' + playerName + '"' + " !");
    }

    //-- Game itself
    static int playGame(int numOfAttempts) {
        int myNum = rand.nextInt(100) + 1;
        boolean userWon = false;

        for (int i = 0; i < numOfAttempts; i++) {
            int userNum = askInt("Enter your guess: ", 1, 100, i);

            if (userNum == myNum) {
                userWon = true;
                System.out.println("Number is " + myNum + " !");
                System.out.println("Congratulations, you are right!");
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
        return 0;
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
            String inputName = scn.nextLine();
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

}




