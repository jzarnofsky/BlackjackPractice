package writingutensils;

public class Pens {

    public static void main(String[] asdfa) {
        byte asdf = 0;
        for (int i = 0; i < 51200000000l; i++) {
            System.out.println(asdf++);
        }

    }

    public Pens(String color, int inkRemaining) {

    }

    String write(String... whatYoureGoingToWrite) {

        for (int i = 0; i < whatYoureGoingToWrite.length; i++) {
            System.out.println(
                    whatYoureGoingToWrite[i]
            );
        }


        boolean didTheyCurse = false;

        for (String individualString : whatYoureGoingToWrite) {

            System.out.println("Before Lower case: " + individualString);

            if (individualString.toLowerCase().contains("cum")) {

                System.out.println(true);
                didTheyCurse = true;

            }
        }
        int i = 0;
        int[] number = new int[1];
        int[] asdfb = new int[1];

        if (number[i] < 65 || number[i] > 90) {
            asdfb[i] = 27;
        }


        if (whatYoureGoingToWrite.length != 0) {

        }

        try {
            System.out.println(whatYoureGoingToWrite[100]);
            System.out.println("You could do more");
        } catch (ArrayIndexOutOfBoundsException exceptionName) {
            System.out.println("You could do more");
        }


        return null;
    }
}
