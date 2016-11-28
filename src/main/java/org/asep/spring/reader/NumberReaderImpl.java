package org.asep.spring.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.asep.spring.dataaccess.NumberStorer;
import org.asep.spring.report.ReadNumberReporter;
import org.springframework.util.Assert;

public class NumberReaderImpl implements NumberReader {

    private final NumberStorer numberStorer;
    private ReadNumberReporter readNumberReporter;

    public NumberReaderImpl(NumberStorer numberStorer) {
        Assert.notNull(numberStorer, "numberStorer cannot be null");

        this.numberStorer = numberStorer;
    }

    public void readNumbers() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while (!(line = bufferedReader.readLine()).equalsIgnoreCase("quit")) {
            if ("get".equalsIgnoreCase(line.trim())) {
                int mostRecentNumber = numberStorer.getMostRecentNumber();

                System.out.println("Most recent number is " + mostRecentNumber);
            } else {
                readNumber(line);
            }
        }
    }

    public void setReadNumberReporter(ReadNumberReporter readNumberReporter) {
        this.readNumberReporter = readNumberReporter;
    }

    private void readNumber(String line) {
        int number;

        try {
            number = Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        if (null != readNumberReporter) {
            readNumberReporter.report(number);
        }

        numberStorer.storeNumber(number);
    }
}
