import java.util.Random;

public class GameLogic {

    private int randomNumber;
    private int maxAttempts;
    private int attemptsUsed;
    private int maxRange;

    public GameLogic(int maxRange, int maxAttempts) {
        this.maxRange = maxRange;
        this.maxAttempts = maxAttempts;
        startNewGame();
    }

    public void startNewGame() {
        Random random = new Random();
        randomNumber = random.nextInt(maxRange) + 1;
        attemptsUsed = 0;
    }

    public String checkGuess(int guess) {
        attemptsUsed++;

        if (guess == randomNumber) {
            return "CORRECT";
        }

        if (guess > randomNumber) {
            return "HIGH";
        }

        return "LOW";
    }

    public int getAttemptsUsed() {
        return attemptsUsed;
    }

    public int getAttemptsLeft() {
        return maxAttempts - attemptsUsed;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getMaxRange() {
        return maxRange;
    }
}