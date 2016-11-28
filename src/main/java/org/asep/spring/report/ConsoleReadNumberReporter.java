package org.asep.spring.report;

public class ConsoleReadNumberReporter implements ReadNumberReporter {

    public void report(int number) {
        System.out.println("Read number " + number);
    }
}
