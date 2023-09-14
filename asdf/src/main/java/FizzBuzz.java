public class FizzBuzz {


    //This class
        //needs to have a method that can take a number
            //Such as 15
        //Needs a method that can take a set of numbers
            //Such as [14, 38, 391830, 5263]

        //With that number or set of numbers, the method should
            //Print "Fizz" if the number is divisible by 3
            //Print "Buzz" if the number is divisible by 5
            //Print "FizzBuzz!" if the number is divisible by both
    public static void buzz(int... notKyne) {
        for (int i = 0; i < notKyne.length; i++) {
            String printable = "";
            if (notKyne[i] == 0) {
                System.out.println("no");
            } else {
                if (notKyne[i] % 3 == 0) {
                    System.out.println("Fizz");
                }
                if (notKyne[i] % 5 == 0) {
                    System.out.println("Buzz");
                }
            }
            if (!printable.equals("")) {
                System.out.println(printable);
            }
        }
    }

    public static void main(String[] asdf) {
        FizzBuzz.buzz(1,2,3,4,5,6,7,8,9,0,10,11,12,13,14,15,16,17,18,19,0);
        FizzBuzz.buzz();
        FizzBuzz.buzz(6);
    }
}
