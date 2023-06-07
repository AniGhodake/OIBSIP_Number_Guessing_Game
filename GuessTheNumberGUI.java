import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class GuessTheNumberGUI extends JFrame {
    private int targetNumber;
    private int maxAttempts;
    private int attempts;
    private int score;

    private JLabel titleLabel;
    private JLabel attemptsLabel;
    private JLabel resultLabel;
    private JLabel scoreLabel;
    private JButton guessButton;
    private JButton restartButton;

    public GuessTheNumberGUI() {
        setTitle("Guess the Number");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        targetNumber = generateRandomNumber(1, 100);
        maxAttempts = 5;
        attempts = 0;
        score = 100;

        titleLabel = new JLabel("Guess the Number from 1 to 100   ");
        attemptsLabel = new JLabel("   Attempted: " + attempts);
        resultLabel = new JLabel("");
        scoreLabel = new JLabel("   Score:      " + score);
        guessButton = new JButton("  Guess  ");
        restartButton = new JButton("  Restart  ");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (attempts < maxAttempts) {
                    String guessString = JOptionPane.showInputDialog(null, "Enter your guess:");
                    if (guessString != null) {
                        int guess = Integer.parseInt(guessString);
                        attempts++;

                        if (guess == targetNumber) {
                            resultLabel.setText("Congratulations! You guessed the number correctly.");
                            scoreLabel.setText("Score: " + score);
                            guessButton.setEnabled(false);
                            JOptionPane.showMessageDialog(null, "You guessed the number correctly!\nTarget number: " + targetNumber + "\nYour score: 100", "Guess Result", JOptionPane.INFORMATION_MESSAGE);
                        } else if (guess < targetNumber) {
                            resultLabel.setText("Your guess is too low.");
                            JOptionPane.showMessageDialog(null, "Your guess is low.", "Guess Result", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            resultLabel.setText("Your guess is too high.");
                            JOptionPane.showMessageDialog(null, "Your guess is high.", "Guess Result", JOptionPane.INFORMATION_MESSAGE);
                        }

                        attemptsLabel.setText("Attempts: " + attempts);

                        if (attempts == maxAttempts) {
                            resultLabel.setText("Game over! You have reached the maximum number of attempts.");
                            JOptionPane.showMessageDialog(null, "The target number was: " + targetNumber + "\nYour score: 0", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            score = 0;
                            scoreLabel.setText("Score: " + score);
                            guessButton.setEnabled(false);
                        }

                        score -= 20;
                        scoreLabel.setText("Score: " + score);
                    }
                }
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                targetNumber = generateRandomNumber(1, 100);
                attempts = 0;
                score = 100;

                resultLabel.setText("");
                attemptsLabel.setText("Attempts: " + attempts);
                scoreLabel.setText("Score: " + score);
                guessButton.setEnabled(true);
            }
        });

        add(titleLabel);
        add(attemptsLabel);
        add(guessButton);
        add(restartButton);
        add(resultLabel);
        add(scoreLabel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuessTheNumberGUI();
            }
        });
    }
}
