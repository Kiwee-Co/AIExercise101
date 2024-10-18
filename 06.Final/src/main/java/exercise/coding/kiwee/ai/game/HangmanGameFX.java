package exercise.coding.kiwee.ai.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class HangmanGameFX extends Application {
    private static final String DICTIONARY_PATH = "/usr/share/dict/web2";
    private static final int MAX_ATTEMPTS = 6;

    private String wordToGuess;
    private HashSet<Character> guessedLetters = new HashSet<>();
    private int attemptsLeft = MAX_ATTEMPTS;

    private Label wordLabel = new Label();
    private Label attemptsLabel = new Label("Attempts left: " + MAX_ATTEMPTS);
    private Label messageLabel = new Label();
    private TextField inputField = new TextField();

    private StackPane hangmanPane = new StackPane();

    @Override
    public void start(Stage primaryStage) {
        List<String> words = loadWordsFromDictionary(DICTIONARY_PATH);
        if (words.isEmpty()) {
            showAlert("Error", "No words found in dictionary.");
            return;
        }

        wordToGuess = words.get((int) (Math.random() * words.size()));
        updateWordLabel();

        Button guessButton = new Button("Guess");
        guessButton.setOnAction(e -> handleGuess());

        HBox inputBox = new HBox(10, inputField, guessButton);
        VBox layout = new VBox(10, wordLabel, attemptsLabel, hangmanPane, inputBox, messageLabel);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setTitle("Hangman Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleGuess() {
        String input = inputField.getText().toLowerCase().trim();
        if (input.isEmpty()) {
            messageLabel.setText("Please enter a letter or word.");
            return;
        }

        if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
            char guessedChar = input.charAt(0);
            if (guessedLetters.contains(guessedChar)) {
                messageLabel.setText("You already guessed that letter.");
                return;
            }

            guessedLetters.add(guessedChar);

            if (wordToGuess.indexOf(guessedChar) >= 0) {
                messageLabel.setText("Good guess!");
                if (checkWordGuessed()) {
                    showAlert("Congratulations!", "You guessed the word: " + wordToGuess);
                    resetGame();
                }
            } else {
                messageLabel.setText("Wrong guess.");
                attemptsLeft--;
                attemptsLabel.setText("Attempts left: " + attemptsLeft);
                drawHangman(MAX_ATTEMPTS - attemptsLeft);
                if (attemptsLeft == 0) {
                    showAlert("Game Over", "You've run out of attempts. The word was: " + wordToGuess);
                    resetGame();
                }
            }
        } else if (input.equals(wordToGuess)) {
            showAlert("Congratulations!", "You guessed the word: " + wordToGuess);
            resetGame();
        } else {
            messageLabel.setText("Incorrect word guess.");
            attemptsLeft--;
            attemptsLabel.setText("Attempts left: " + attemptsLeft);
            drawHangman(MAX_ATTEMPTS - attemptsLeft);
            if (attemptsLeft == 0) {
                showAlert("Game Over", "You've run out of attempts. The word was: " + wordToGuess);
                resetGame();
            }
        }

        updateWordLabel();
        inputField.clear();
    }

    private void updateWordLabel() {
        StringBuilder displayWord = new StringBuilder();
        for (char c : wordToGuess.toCharArray()) {
            if (guessedLetters.contains(c)) {
                displayWord.append(c).append(' ');
            } else {
                displayWord.append("_ ");
            }
        }
        wordLabel.setText(displayWord.toString().trim());
    }

    private boolean checkWordGuessed() {
        for (char c : wordToGuess.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    private void resetGame() {
        guessedLetters.clear();
        attemptsLeft = MAX_ATTEMPTS;
        List<String> words = loadWordsFromDictionary(DICTIONARY_PATH);
        wordToGuess = words.get((int) (Math.random() * words.size()));

        updateWordLabel();
        attemptsLabel.setText("Attempts left: " + MAX_ATTEMPTS);
        hangmanPane.getChildren().clear();
    }

    private List<String> loadWordsFromDictionary(String path) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            showAlert("Error", "Error reading dictionary: " + e.getMessage());
        }
        return words;
    }

    private void drawHangman(int wrongAttempts) {
        hangmanPane.getChildren().clear();

        // Base
        Line base = new Line(50, 150, 150, 150);
        
        // Pole
        Line pole = new Line(100, 150, 100, 50);

        // Beam
        Line beam = new Line(100, 50, 150, 50);

        // Rope
        Line rope = new Line(150, 50, 150, 70);

        
       // Add gallows parts
       hangmanPane.getChildren().addAll(base, pole, beam, rope);

       // Draw the hangman based on wrong attempts
       switch (wrongAttempts) {
           case 1:
               Circle head = new Circle(150, 85, 15); // Head
               hangmanPane.getChildren().add(head);
               break;
           case 2:
               Line body = new Line(150, 100, 150, 130); // Body
               hangmanPane.getChildren().add(body);
               break;
           case 3:
               Line leftArm = new Line(150, 110, 140, 120); // Left Arm
               hangmanPane.getChildren().add(leftArm);
               break;
           case 4:
               Line rightArm = new Line(150, 110, 160, 120); // Right Arm
               hangmanPane.getChildren().add(rightArm);
               break;
           case 5:
               Line leftLeg = new Line(150, 130, 140, 140); // Left Leg
               hangmanPane.getChildren().add(leftLeg);
               break;
           case 6:
               Line rightLeg = new Line(150, 130, 160, 140); // Right Leg
               hangmanPane.getChildren().add(rightLeg);
               break; 
       }
   }

   private void showAlert(String title, String content) {
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle(title);
       alert.setHeaderText(null);
       alert.setContentText(content);
       alert.showAndWait();
   }

   public static void main(String[] args) {
       launch(args);
   }
}