import javax.swing.JOptionPane;
import java.util.Random;

public class GuessNumberGame {

    private static final int MAX_ATTEMPTS = 6;
    private static final int MIN_NUMBER   = 1;
    private static final int MAX_NUMBER   = 100;
    private static final Random RANDOM    = new Random();

    public static void main(String[] args) {
        int totalScore = 0;          // Cumulative score across rounds
        boolean playAgain = true;    // Controls replay loop

        while (playAgain) {
            int secret      = RANDOM.nextInt(MAX_NUMBER) + MIN_NUMBER; // Random number 1‑100
            int attempts    = MAX_ATTEMPTS;
            boolean guessed = false;
            int roundScore  = 0;

            
            //  GUESSING LOOP (ONE ROUND)
            while (attempts > 0 && !guessed) {
                //  USER INPUT DIALOG BOX
                //  Prompts the player to enter a guess each turn.
                String input = JOptionPane.showInputDialog(
                        null,
                        "Guess the number (" + MIN_NUMBER + "‑" + MAX_NUMBER + ")\nAttempts left: " + attempts,
                        "Guess the Number",
                        JOptionPane.QUESTION_MESSAGE
                );

                // If the user presses Cancel, terminate the whole game.
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Game cancelled. Goodbye!");
                    System.exit(0);
                }

                int guess;
                try {
                    guess = Integer.parseInt(input.trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer! (No attempt lost)");
                    continue; // Invalid input does not consume an attempt
                }

                if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
                    JOptionPane.showMessageDialog(null, "Your guess must be between " + MIN_NUMBER + " and " + MAX_NUMBER + "! (No attempt lost)");
                    continue;
                }

                attempts--; // Attempt was made

                if (guess == secret) {
                    guessed = true;
                    roundScore = attempts + 1; // More remaining attempts → higher score
                    totalScore += roundScore;
                    JOptionPane.showMessageDialog(null,
                            "🎉 Correct! You guessed the number in " + (MAX_ATTEMPTS - attempts) + " tries.\n" +
                                    "Round Score : " + roundScore + "\nTotal Score  : " + totalScore);
                } else if (guess < secret) {
                    JOptionPane.showMessageDialog(null, "Too low! Try again.");
                } else {
                    JOptionPane.showMessageDialog(null, "Too high! Try again.");
                }
            }

            if (!guessed) {
                JOptionPane.showMessageDialog(null, "❌ Out of attempts! The number was " + secret + ".");
            }

            //  Asks the player whether they want to start a new round.
            int option = JOptionPane.showConfirmDialog(
                    null,
                    "Would you like to play another round?",
                    "Play Again?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            playAgain = (option == JOptionPane.YES_OPTION);
        }

        JOptionPane.showMessageDialog(null, "Thanks for playing!\nYour final score: " + totalScore);
    }
}

