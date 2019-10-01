package com.company;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random rand = new Random(5);
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {

        int myNum = rand.nextInt(100) + 1;
        boolean answer;

        String PlayerName = userName("What is your name?");

        do {
            boolean userWon = false;

            for (int i = 0; i < 5; i++) {
                int userNum = askInt("Enter your guess: ", 1, 100);

                if (userNum == myNum) {
                    userWon = true;
                    System.out.println("Requested number: " + myNum);
                    System.out.println("Congratulations, you are right");
                    break;
                } else if (myNum > userNum) {
                    System.out.println("Requested number is bigger ");
                    System.out.println("Guess again");
                } else if (myNum < userNum) {
                    System.out.println("Requested number is lesser ");
                    System.out.println("Guess again");
                }
            }
            if (!userWon) {
                System.out.println("Requested number: " + myNum);
                System.out.println("Sorry, you're out of luck");
            }
        } while (choseOption("Do you want to play more? (Y/N)"));

        System.out.println("Goodbye" + PlayerName);
    }

    static int askInt(String msg, int min, int max) {
        while (true) {
            try {
                System.out.println(msg);
                int answer = scn.nextInt();
                if (answer >= min && answer <= max) {
                    return answer;
                }
            } catch (InputMismatchException ex) {
                System.out.println("It isn't a number");
                scn.next();
            }
            System.out.printf("Please enter number from %d to %d\n", min, max);
        }
    }

    static boolean choseOption(String msg) {
        while (true) {
            String option = scn.next();
            boolean isY = option.equalsIgnoreCase("y");
            boolean isN = option.equalsIgnoreCase("n");
            if (isY || isN) {
                return isY;
            }
            System.out.println("Please chose Y or N");
        }
    }

    static String userName (String msg){
        System.out.println(msg);
        while(true) {
            try {
                String name = scn.nextLine();
                System.out.println("Welcome");
                    return name;
            } catch (InputMismatchException ex){
                System.out.println("use only letters");
                scn.next();
            }
        }
    }
}

