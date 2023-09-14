public class God {


    public static int howManyGods = 67;

    private static int nameTakenInVain = 0;

    public static void takeNameInVain() {
        nameTakenInVain = nameTakenInVain + 1;
    }

    public static int howManyTimesHavePeopleBeenDicks() {
        return nameTakenInVain;
    }
}
