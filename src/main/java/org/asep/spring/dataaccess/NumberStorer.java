package org.asep.spring.dataaccess;

public interface NumberStorer {

    void storeNumber(int number);

    int getMostRecentNumber();

}
