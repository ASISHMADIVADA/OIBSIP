import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private JComboBox<String> difficultyBox;
    private JTextField guessField;
    private JButton guessButton;
    private JButton playAgainButton;

    private JLabel rangeLabel;
    private JLabel attemptsLabel;
    private JLabel resultLabel;
    private JLabel roundLabel;
    private JLabel scoreLabel;

    private GameLogic game;

    private int rounds = 1;
    private int wins = 0;

    public GameFrame() {

        setTitle("Number Guessing Game");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        panel.setLayout(new GridLayout(11,1,10,10));

        JLabel title = new JLabel("NUMBER GUESSING GAME");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,22));

        difficultyBox = new JComboBox<>();
        difficultyBox.addItem("Easy");
        difficultyBox.addItem("Medium");
        difficultyBox.addItem("Hard");

        rangeLabel = new JLabel();

        attemptsLabel = new JLabel();

        roundLabel = new JLabel("Round : 1");

        scoreLabel = new JLabel("Wins : 0");

        guessField = new JTextField();

        guessButton = new JButton("Guess");

        playAgainButton = new JButton("Play Again");

        resultLabel = new JLabel("Enter your Guess");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial",Font.BOLD,18));

        panel.add(title);
        panel.add(new JLabel("Select Difficulty"));
        panel.add(difficultyBox);
        panel.add(rangeLabel);
        panel.add(attemptsLabel);
        panel.add(roundLabel);
        panel.add(scoreLabel);
        panel.add(guessField);
        panel.add(guessButton);
        panel.add(resultLabel);

        add(panel,BorderLayout.CENTER);
        add(playAgainButton,BorderLayout.SOUTH);

        startGame();

        difficultyBox.addActionListener(e -> startGame());

        guessButton.addActionListener(e -> checkGuess());

        playAgainButton.addActionListener(e -> {

            rounds++;

            roundLabel.setText("Round : " + rounds);

            guessButton.setEnabled(true);

            startGame();

        });

        setVisible(true);

    }

    private void startGame(){

        String level = difficultyBox.getSelectedItem().toString();

        int range;
        int attempts;

        switch(level){

            case "Easy":
                range = 50;
                attempts = 10;
                break;

            case "Hard":
                range = 200;
                attempts = 5;
                break;

            default:
                range = 100;
                attempts = 7;
                break;

        }

        game = new GameLogic(range,attempts);

        rangeLabel.setText("Guess Number Between 1 and " + range);

        attemptsLabel.setText("Attempts Left : " + attempts);

        resultLabel.setText("Enter your Guess");

        guessField.setText("");

    }

    private void checkGuess(){

        try{

            int guess = Integer.parseInt(guessField.getText());

            String result = game.checkGuess(guess);

            attemptsLabel.setText("Attempts Left : " + game.getAttemptsLeft());

            if(result.equals("CORRECT")){

                wins++;

                scoreLabel.setText("Wins : " + wins);

                resultLabel.setText("Correct!");

                JOptionPane.showMessageDialog(this,
                        "Congratulations!\nYou guessed correctly in "
                                + game.getAttemptsUsed()
                                + " attempts.");

                guessButton.setEnabled(false);

            }

            else if(result.equals("HIGH")){

                resultLabel.setText("Too High!");

            }

            else{

                resultLabel.setText("Too Low!");

            }

            if(game.getAttemptsLeft()==0 && !result.equals("CORRECT")){

                JOptionPane.showMessageDialog(this,
                        "You Lost!\nCorrect Number : "
                                + game.getRandomNumber());

                guessButton.setEnabled(false);

            }

            guessField.setText("");

        }

        catch(NumberFormatException e){

            JOptionPane.showMessageDialog(this,
                    "Please enter a valid number.");

        }

    }

}