package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random rand = new Random(5);
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {

        int myNum = rand.nextInt(100) + 1;
        String answer;

        do {
            System.out.println("Enter your guess: ");
            boolean userWon = false;
            for (int i = 0; i < 5; i++) {
                int userNum = scn.nextInt();
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
                System.out.println("Sorry, you out of luck");
            }
            System.out.println("Do you want to play more? (y/n)");
            answer = scn.next();
        } while (answer.equalsIgnoreCase("y"));
    }
}
