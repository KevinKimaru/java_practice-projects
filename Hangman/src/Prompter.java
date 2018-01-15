import java.util.Scanner;

/**
 * Created by Kevin Kimaru Chege on 8/1/2017.
 */
public class Prompter {
    private Game game;

    public Prompter(Game game) {
        this.game = game;
    }

    public boolean promptForGuess() {
        Scanner scanner = new Scanner(System.in);
        boolean isHit = false;
        boolean isAcceptable = false;

        do {
            System.out.println("Enter a letter: ");
            String guessInput = scanner.nextLine();

            try {
                isHit = game.applyGuess(guessInput);
                isAcceptable = true;
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        } while (!isAcceptable);
        return isHit;
    }

    public void displayProgress() {
        System.out.printf("You have %d tries left to solve: %s%n",
                game.getRemainingTries(),
                game.getCurrentProgress());
    }

    public void displayOutcome() {
        if (game.isWon()) {
            System.out.printf("Congratulations. You won with %s tries remaining.%n", game.getRemainingTries());
        } else {
            System.out.printf("Bummer. The word is %S :( %n", game.getAnswer());
        }
    }
}
