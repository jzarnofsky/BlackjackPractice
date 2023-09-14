package calculator;

import blackjack.Deck;

import java.util.ArrayList;
import java.util.List;

public class Calculate {

    List<Double> numbers = new ArrayList<>();
    List<String> operators = new ArrayList<>();

    public static void main(String[] kiuyhjkiuyhjkiu) {
        System.out.println(Deck.Face.EIGHT.toString());
//        Calculate calculator = new Calculate();
//        String equation = ReadFromFile.getRawEquation();
//        StringBuilder equationBuilder = new StringBuilder();
//        calculator.solve(equation, equationBuilder);
    }

    public void solve(String equation, StringBuilder equationBuilder) {
        solveParenthesis(equation.contains("("), equationBuilder);
        doMath(equationBuilder.toString());
    }

    public void solveParenthesis(boolean containsParenthesis, StringBuilder equationBuilder) {
        while (containsParenthesis) {
            String whileEquation = equationBuilder.toString();
            String insideParenthesisEquation = getOriginalEquationAndClearBuilder(equationBuilder) ;
            double parenthesisAnswer = doMath(insideParenthesisEquation);
            equationBuilder.append(
                    whileEquation.replace("( " + insideParenthesisEquation + " )", Double.toString(parenthesisAnswer))
            );
            containsParenthesis = equationBuilder.toString().contains("(");
        }
    }

    private String getOriginalEquationAndClearBuilder(StringBuilder equationBuilder) {
        String equationFromStringBuilder = equationBuilder.toString();
        doTheParenthesis(equationFromStringBuilder);
        equationBuilder.setLength(0);
        return equationFromStringBuilder;
    }

    public void parseEquation(String equation) {
        clearLists();
        fillLists(equation.split(" "));
    }

    private void fillLists(String[] equationPieces) {
        for (String equationPiece : equationPieces) {
            try {
                numbers.add(getNumber(equationPiece));
            } catch (NumberFormatException exception) {
                operators.add(equationPiece);
            }
        }
    }

    private void clearLists() {
        numbers.clear();
        operators.clear();
    }

    private double doMath(String equation) {
        double answer = 0;
        parseEquation(equation);
        for (int i = 0; operators.size() > 0; i++) {
            int pemdasIndex = getPemdasIndex();
            if (pemdasIndex > -1) {
                answer = performOperation(operators.get(pemdasIndex), numbers.get(pemdasIndex), numbers.get(pemdasIndex + 1));
                operators.remove(pemdasIndex);
                numbers.remove(pemdasIndex);
                numbers.remove(pemdasIndex);
                numbers.add(pemdasIndex, answer);
                parseEquation(makeNewEquation());
            }
        }
        answer = dropUnwantedDigit(answer);
        return answer;
    }

    public double performOperation(String operator, double firstNumber, double secondNumber) {
        return switch (operator) {
            case "rt" -> rootMath(firstNumber, secondNumber);
            case "+" -> firstNumber + secondNumber;
            case "-" -> firstNumber - secondNumber;
            case "*" -> firstNumber * secondNumber;
            case "/" -> firstNumber / secondNumber;
            case "^" -> exponentMath(firstNumber, secondNumber);
            default -> throw new NumberFormatException();
        };
    }

    private double dropUnwantedDigit(double answer) {
        String answerAsString = Double.toString(answer);
        int decimalIndex = answerAsString.indexOf(".");

        int charactersAfterDecimal = answerAsString.split("\\.")[1].length();
        return dropLastCharacterIfDesired(decimalIndex, charactersAfterDecimal, answerAsString);
    }

    private double dropLastCharacterIfDesired(int decimalIndex, int charactersAfterDecimal, String answerAsString) {
        if (decimalIndex >= 0 && charactersAfterDecimal > 5) {
            return Double.parseDouble(dropLastCharacterFromString(answerAsString));
        }
        return Double.parseDouble(answerAsString);
    }

    private double exponentMath(double firstNumber, double secondNumber) {
        double tempNumber = 1;
        for (int i = 0; i < secondNumber; i++) {
            tempNumber *= firstNumber;
        }
        return tempNumber;
    }

    private double rootMath(double firstNumber, double secondNumber) {
        double tempNumber = firstNumber;
        for (int i = 0; i < secondNumber; i++) {
            tempNumber = Math.sqrt(tempNumber);
        }
        return tempNumber;
    }

    private double getNumber(String equationPiece) {
        if (equationPiece.equals("ANS")) {
            return Double.parseDouble(ReadFromFile.getAnswer());
        } else {
            return Double.parseDouble(equationPiece);
        }
    }

    private String makeNewEquation() {
        StringBuilder newEquation = new StringBuilder();
        return sortNumbersAndOperatorsIntoEquation(newEquation).toString().trim();
    }

    private StringBuilder sortNumbersAndOperatorsIntoEquation(StringBuilder newEquation) {
        for (int i = 0; i < numbers.size(); i++) {
            appendNumber(newEquation, numbers.get(i));
            try {
                appendOperator(newEquation, operators.get(i));
            } catch (IndexOutOfBoundsException ignored) { }
        }
        return newEquation;
    }

    private void appendNumber(StringBuilder equation, double number) {
        appendToEquation(equation, Double.toString(number));
    }

    private void appendOperator(StringBuilder equation, String operator) {
        appendToEquation(equation, operator);
    }

    private void appendToEquation(StringBuilder equation, Object whatToAppend) {
        equation.append(whatToAppend);
        equation.append(" ");
    }

    private int getPemdasIndex() {
        if (operators.contains("^")) {
            return operators.indexOf("^");
        } else if (operators.contains("rt")) {
            return operators.indexOf("rt");
        } else if (operators.contains("*")) {
            return operators.indexOf("*");
        } else if (operators.contains("/")) {
            return operators.indexOf("/");
        } else if (operators.contains("+")) {
            return operators.indexOf("+");
        } else {
            return operators.indexOf("-");
        }
    }

    private String dropLastCharacterFromString(String string) {
        return string.substring(0, string.length() - 1);
    }

    private String doTheParenthesis(String equation) {
        int indexOfOpener = equation.indexOf("(");
        int indexOfCloser = equation.indexOf(")");
        if (indexOfOpener > -1) {
            String newEquation = equation.substring(indexOfOpener + 1, indexOfCloser).trim();
            int indexOfOtherParenthesis = newEquation.indexOf("(");

            StringBuilder equationBuilder = new StringBuilder();
            equationBuilder.append(newEquation);

            while (indexOfOtherParenthesis > -1) {
                String whileEquation = equationBuilder.toString();
                equationBuilder.setLength(0);
                equationBuilder.append(whileEquation.substring(indexOfOtherParenthesis + 1).trim());
                indexOfOtherParenthesis = equationBuilder.indexOf("(");
            }

            return equationBuilder.toString();
        }
        return equation;
    }

}
