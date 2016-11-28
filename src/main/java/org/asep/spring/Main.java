package org.asep.spring;

import java.io.IOException;

import org.asep.spring.config.ApplicationConfiguration;
import org.asep.spring.reader.NumberReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        NumberReader numberReader = applicationContext.getBean(NumberReader.class);

        numberReader.readNumbers();
    }

}
