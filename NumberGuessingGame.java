package Task_4;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int score = 0;               // total rounds won
        int maxAttempts = 5;         // attempt limit
        boolean playAgain = true;

        System.out.println("Welcome to Number Guessing Game");

        while (playAgain) {

            int numberToGuess = random.nextInt(100) + 1; // 1 to 100
            int attemptsLeft = maxAttempts;
            boolean isGuessed = false;

            System.out.println("\nI have chosen a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attemptsLeft > 0) {

                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                attemptsLeft--;

                if (userGuess == numberToGuess) {
                    System.out.println("Correct! You guessed the number.");
                    isGuessed = true;
                    score++;
                    break;
                } 
                else if (userGuess > numberToGuess) {
                    System.out.println("Too High!");
                } 
                else {
                    System.out.println("Too Low!");
                }

                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!isGuessed) {
                System.out.println("You lost! The number was: " + numberToGuess);
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            String choice = sc.next();

            if (!choice.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("\nGame Over!");
        System.out.println("Your total score (rounds won): " + score);
        System.out.println("Thanks for playing");

        sc.close();
    }
}

