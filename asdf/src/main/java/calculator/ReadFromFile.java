package calculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFromFile {
    private static final File equation = new File("/Users/JDoty/Desktop/Equation.txt");
    private static final File answer = new File("/Users/JDoty/Desktop/Answer.txt");
    private static final File other = new File("/Users/JDoty/Desktop/nonsense.txt");
    private static final File otherTwo = new File("/Users/JDoty/Desktop/onon 2.txt");

    public static void main(String[] asdf) {
        StringBuilder builderOne = new StringBuilder();
        StringBuilder builderTwo = new StringBuilder();
        StringBuilder builderThree = new StringBuilder();
        Scanner scanner = null;
        try {
            scanner = new Scanner(other);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNext()) {
            builderOne.append(scanner.nextLine() + "\n");
        }
        try {
            scanner = new Scanner(otherTwo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNext()) {
            builderTwo.append(scanner.nextLine().replace(" ", ""));
        }

        for (int i = 0; i < builderOne.toString().length(); i++) {
            String[] one = builderOne.toString().split("");
            String[] two = builderTwo.toString().split("");
            if (!one[i].equals("'")
                    && !one[i].equals(".")
                    && !one[i].equals("\"")
                    && !one[i].equals("\t")
                    && !one[i].equals("\n")) {
                builderThree.append(two[i]);
            } else {
                builderThree.append(one[i]);
            }
        }
        System.out.println(builderThree.toString());

    }

    public static String getRawEquation() {
        try {
            Scanner scanner = new Scanner(equation);
            return scanner.nextLine();
        } catch (FileNotFoundException ignored) { }
        return "";
    }

    public static String getAnswer() {
        try {
            Scanner scanner = new Scanner(answer);
            return scanner.nextLine();
        } catch (FileNotFoundException ignored) { }
        return "";
    }



}
