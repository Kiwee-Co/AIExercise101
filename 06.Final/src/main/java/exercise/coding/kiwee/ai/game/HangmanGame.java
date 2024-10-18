package exercise.coding.kiwee.ai.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class HangmanGame {
    private static final String DICTIONARY_PATH = "/usr/share/dict/web2";
    private static final int MAX_ATTEMPTS = 6;

    public static void main(String[] args) {
        List<String> words = loadWordsFromDictionary(DICTIONARY_PATH);
        if (words.isEmpty()) {
            System.out.println("No words found in dictionary.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        String wordToGuess = words.get((int) (Math.random() * words.size()));
        HashSet<Character> guessedLetters = new HashSet<>();
        int attemptsLeft = MAX_ATTEMPTS;
        boolean wordGuessed = false;

        System.out.println("Welcome to Hangman!");

        while (attemptsLeft > 0 && !wordGuessed) {
            System.out.println("\nAttempts left: " + attemptsLeft);
            displayHangman(MAX_ATTEMPTS - attemptsLeft);
            displayWordState(wordToGuess, guessedLetters);

            System.out.print("Guess a letter or the entire word: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                // Handle single letter guess
                char guessedChar = input.charAt(0);
                if (guessedLetters.contains(guessedChar)) {
                    System.out.println("You already guessed that letter.");
                    continue;
                }

                guessedLetters.add(guessedChar);

                if (wordToGuess.indexOf(guessedChar) >= 0) {
                    System.out.println("Good guess!");
                    wordGuessed = checkWordGuessed(wordToGuess, guessedLetters);
                } else {
                    System.out.println("Wrong guess.");
                    attemptsLeft--;
                }
            } else if (input.equals(wordToGuess)) {
                // Handle full word guess
                wordGuessed = true;
            } else {
                // Incorrect full word guess
                System.out.println("Incorrect word guess.");
                attemptsLeft--;
            }
        }

        if (wordGuessed) {
            System.out.println("\nCongratulations! You guessed the word: " + wordToGuess);
        } else {
            displayHangman(MAX_ATTEMPTS);
            System.out.println("\nSorry, you've run out of attempts. The word was: " + wordToGuess);
        }

        scanner.close();
    }

    private static List<String> loadWordsFromDictionary(String path) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading dictionary: " + e.getMessage());
        }
        return words;
    }

    private static void displayWordState(String word, HashSet<Character> guessedLetters) {
        for (char c : word.toCharArray()) {
            if (guessedLetters.contains(c)) {
                System.out.print(c + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    private static boolean checkWordGuessed(String word, HashSet<Character> guessedLetters) {
        for (char c : word.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private static void displayHangman(int wrongAttempts) {
        String[] hangmanStages = {
            """
              +---+
              |   |
                  |
                  |
                  |
                  |
             =========""",
             
            """
              +---+
              |   |
              O   |
                  |
                  |
                  |
             =========""",
             
            """
              +---+
              |   |
              O   |
              |   |
                  |
                  |
             =========""",
             
            """
              +---+
              |   |
              O   |
             /|   |
                  |
                  |
             =========""",
             
            """
              +---+
              |   |
              O   |
             /|\\  |
                  |
                  |
             =========""",
             
            """
              +---+
              |   |
              O   |
             /|\\  |
             /    |
                  |
             =========""",
             
            """
              +---+
              |   |
              O   |
             /|\\  |
             / \\  |
                  |
             ========="""
        };

        System.out.println(hangmanStages[wrongAttempts]);
    }
}