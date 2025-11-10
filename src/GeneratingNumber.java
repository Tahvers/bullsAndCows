import java.util.Random;

public class GeneratingNumber {
    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static String randomNumber(int lengthInput, int symbolsRange) {
        if (lengthInput > symbolsRange) {
            System.out.println("Error: can't generate a secret number with a length of " + lengthInput
                    + " because there aren't enough unique symbols.");
            return null;
        }

        String allowedSymbols = SYMBOLS.substring(0, symbolsRange);
        String secret = "";
        Random random = new Random();

        while (secret.length() < lengthInput) {
            char next = allowedSymbols.charAt(random.nextInt(symbolsRange));
            if (secret.indexOf(next) == -1) {
                secret += next;
            }
        }

        return secret;
    }

    public static String getSymbolRange(int symbolsRange) {
        if (symbolsRange <= 10) {
            return "0-" + (symbolsRange - 1);
        } else {
            char lastChar = SYMBOLS.charAt(symbolsRange - 1);
            return "0-9, a-" + lastChar;
        }
    }
}
