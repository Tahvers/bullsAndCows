import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        String attempt = "";
        int cows;
        int bulls;
        int lengthInput;
        int symbolsRange;

        try {
            System.out.println("Please, enter the secret code's length:");
            lengthInput = sc.nextInt();

            System.out.println("Input the number of possible symbols in the code:");
            symbolsRange = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e){
            System.out.println("Error: invalid input. Please enter a number.");
            return;
        }

        if (lengthInput <= 0) {
            System.out.println("Error: can't generate a secret number with a length of " + lengthInput + ".");
            return;
        }

        if (symbolsRange <= 0) {
            System.out.println("Error: number of possible symbols must be positive.");
            return;
        }

        if (lengthInput > symbolsRange) {
            System.out.println("Error: it's not possible to generate a code with a length of "
                    + lengthInput + " with " + symbolsRange + " unique symbols.");
            return;
        }

        if (symbolsRange > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }

        String secretCode = GeneratingNumber.randomNumber(lengthInput, symbolsRange);

        if (secretCode == null){
            return;
        }

        String mask = "";
        for (int i = 0; i < lengthInput; i++) {
            mask += "*";
        }
        String rangeDescription = GeneratingNumber.getSymbolRange(symbolsRange);
        System.out.println("The secret is prepared: " + mask + " (" + rangeDescription + ").");

        System.out.println("Okay, let's start a game!");

        do {
            counter++;
            bulls = 0;
            cows = 0;

            System.out.println("Turn " + counter + ":");
            attempt = sc.nextLine();

            for (int i = 0; i < secretCode.length(); i++) {
                char sn = secretCode.charAt(i);
                char at = attempt.charAt(i);

                if (sn == at) {
                    bulls++;
                } else if (secretCode.indexOf(at) != -1) {
                    cows++;
                }
            }

            System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s)");

        } while (!attempt.equals(secretCode));

        System.out.println("Congratulations! You guessed the secret code.");
    }
}
