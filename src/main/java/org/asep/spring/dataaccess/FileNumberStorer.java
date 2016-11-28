package org.asep.spring.dataaccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileNumberStorer implements NumberStorer {

    public static final Path PATH = Paths.get("numbers.txt");
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public void storeNumber(int number) {
        try {
            String line = Integer.toString(number) + "\n";

            Files.write(PATH, line.getBytes(CHARSET), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMostRecentNumber() {
        int number = 0;

        try {
            BufferedReader bufferedReader = Files.newBufferedReader(PATH, CHARSET);

            String line;
            while (null != (line = bufferedReader.readLine())) {
                number = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return number;
    }
}
