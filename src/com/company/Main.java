package com.company;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random rand = new Random();
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {

        int myNum = rand.nextInt(100) + 1;
        boolean answer;
        String PlayerName = userName("What is your name?\n" +
                "(please input from 3 to 10 characters without spaces)", 3, 10);

        do {
            boolean userWon = false;

            for (int i = 0; i < 10; i++) {
                int n = 9;
                int userNum = askInt("Enter your guess: ", 1, 100);

                if (userNum == myNum) {
                    userWon = true;
                    System.out.println("Number is: " + myNum + " !");
                    System.out.println("Congratulations, you are right!");
                    break;
                } else if (myNum > userNum) {
                    System.out.println("Number is greater\n Attempts left: " + (n - i));
                } else if (myNum < userNum) {
                    System.out.println("Number is smaller\n Attempts left: " + (n - i));
                }
            }

            if (!userWon) {
                System.out.println("Number is: " + myNum + "!");
                System.out.println("Sorry, you're out of luck");
            }
        } while (choseOption("Do you want to play more? (Y/N)"));
        System.out.println("Goodbye " + '"' + PlayerName + '"' + "!");
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
                System.out.println("It isn't a number!");
                scn.next();
            }
            System.out.printf("Please enter number from %d to %d\n", min, max);
        }
    }

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

    static String userName(String msg, int min, int max) {
        System.out.println(msg);
        while (true) {
            String name = scn.nextLine();
            name = name.replaceAll("\\s+", "");
            int nameLength = name.length();

            if (nameLength > max) {
                System.out.printf("Sorry, but your name is to long!\nPlease input less than a %d characters\n", max);
            } else if (nameLength < min) {
                System.out.printf("Sorry, but your name is to short!\nPlease input at least %d characters\n", min);
            } else {
                System.out.println("Welcome " + '"' + name + '"' + "!");
                return name;
            }
        }
    }
}




