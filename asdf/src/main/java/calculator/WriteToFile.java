package calculator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

    public static void writeToFile(String answer) {
        File answerFile = new File("/Users/JDoty/Desktop/Answer.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(answerFile));
            writer.write(answer);
            writer.flush();
            writer.close();
        } catch (IOException ignored) { }
    }
}
