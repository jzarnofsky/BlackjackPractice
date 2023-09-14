import calculator.ReadFromFile;
import writingutensils.Pens;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SillyBoi {

    private static String someString = "asdf";
    private static int howManytimesWasSomeStringUsed = 0;

    public static String getSomeString() {
        howManytimesWasSomeStringUsed++;
        return someString;
    }

    public static void main(String[] externalInputs) {




//        File scrabbleFile = new File("/Users/JDoty/Desktop/scrabble.txt");
//        String theAnswer = "satire";
//        try {
//            Scanner scanner = new Scanner(scrabbleFile);
//
//            int numberOfWordsInTheDictionary = scanner.nextInt();
//            if (scanner.hasNextLine()) {
//                scanner.nextLine();
//            }
//
//            List<String> dictionaryOfWords = new ArrayList<>();
//            for (int i = 0; i < numberOfWordsInTheDictionary; i++) {
//                dictionaryOfWords.add( scanner.nextLine() );
//            }
//
//
//            String myHand = scanner.nextLine();
//            List<String> usableWords = getUsableWords(dictionaryOfWords, myHand);
//
//            String myAnswer = getTheAnswer(usableWords);
//            System.out.println(myAnswer.equals(theAnswer));
//        } catch (FileNotFoundException ignored) { }

    }

    public static int getLetterValue(String letter) {
        return switch (letter) {
            case "b", "c", "m", "p" -> 3;
            case "f", "h", "v", "w", "y" -> 4;
            case "k" -> 5;
            case "j", "x" -> 8;
            case "z", "q" -> 10;
            default -> 1;
        };
    }

    private static String chooseHighestValueWord(List<String> dictionaryOfWords) {
        String lastWord = "";
        for (String dictionaryWord : dictionaryOfWords) {
            if (getWordValue(dictionaryWord) > getWordValue(lastWord)) {
                lastWord = dictionaryWord;
            }
        }
        return lastWord;
    }

    private static int getWordValue(String word) {
        int wordValueTotal = 0;
        String[] splitWord = word.split("");
        for (String letterOfTheSplitWord: splitWord) {
            wordValueTotal += getLetterValue(letterOfTheSplitWord);
        }
        return wordValueTotal;
    }


    //YOU ARE STARTING HERE DON'T GO ELSEWHERE
    private static List<String> getUsableWords(List<String> listOfDictionaryWords, String myHand) {
        List<String> ourUsableWords = new ArrayList<>();
        for (String dictionaryWord : listOfDictionaryWords) {
            if (doWeHaveAllTheLetters(dictionaryWord, myHand)) {
                ourUsableWords.add(dictionaryWord);
            }
        }
        return ourUsableWords;
    }

    private static boolean compareAgainstHand(String dictionaryWord, String myHand) {
        String[] wordLetters = dictionaryWord.split("");
        for (String letterOfWord: wordLetters) {
            if (!compareLetterAgainstHand(letterOfWord, myHand)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareLetterAgainstHand(String letterToCompare, String myHand) {
        String[] setOfTiles = myHand.split("");
        for (String tile : setOfTiles) {
            if (tile.contains(letterToCompare)) {
                return true;
            }
        }
        return false;
    }

    private static String getTheAnswer(List<String> usableWords) {
        return chooseHighestValueWord(usableWords);
    }


    private static boolean doWeHaveAllTheLetters(String dictionaryWord, String myHand) {
        List<String> tilesLeft = new ArrayList<>();
        String[] lettersInDictionaryWord = dictionaryWord.split("");
        for (String letter : lettersInDictionaryWord) {
            if (tilesLeft.contains(letter)) {
                tilesLeft.remove(letter);
            } else {
                return false;
            }
        }
        return true;
    }

    //ask: what am I trying to solve/do
        //ask: What do I need to have to solve/do that
            //Actually getting those things comes later
        //If I have what I need, what do I need to do to solve/do that
        //Do I need to have anything to show once I've solved/done that


























































//    static int firstNumberFromUser;
//    static int secondNumberFromUser;
//    static int answer;
//
//    static String userMath;
//
//    //To do this, I'm going to need 3 things
//        //two numbers
//        //what math is wanted
//    private static void getInputFromUser() {
//
//        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
//        System.out.println("Enter username");
//
//        firstNumberFromUser = scanner.nextInt();  // Read user input
//        System.out.println(firstNumberFromUser);
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//
//        }
//
//        secondNumberFromUser = scanner.nextInt();  // Read user input
//
//        System.out.println(secondNumberFromUser);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//
//        }
//        System.out.println(secondNumberFromUser);
//
////        userMath = new String(scanner.nextLine(), StandardCharsets.US_ASCII);  // Read user input
//
//        System.out.println(userMath);
//        System.out.println(userMath);
//
//    }
//
//
//    private static void whatMathToDo() {
//        System.out.println(userMath.getBytes());
////        System.out.println("+".);
//
//        if (userMath.getBytes().equals("+")) {
//            answer = firstNumberFromUser + secondNumberFromUser;
//        } else if (userMath.equals("-")) {
//            answer = secondNumberFromUser - secondNumberFromUser;
//        } else if (userMath.equals("/")) {
//            answer = firstNumberFromUser / secondNumberFromUser;
//        } else if (userMath.equals("*")) {
//            answer = secondNumberFromUser * secondNumberFromUser;
//        } else {
//            answer = 21122112;
//        }
//
//
//    }
//
//
//    private static void doTheMath() {
//
//    }
//
//    private static void showAnswer() {
//        System.out.println(answer);
//    }

}