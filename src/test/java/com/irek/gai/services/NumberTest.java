package com.irek.gai.services;

import static org.junit.Assert.*;

public class NumberTest {
    private final Number number = new Number();

    @org.junit.Before
    public void setUp() {
        number.setNumericalValues(777);
        number.setLiteralValues(new int[]{0,1,2});
    }

    @org.junit.Test
    public void getNumber() {
        String expected = "A777BE 116 RUS";
        String actual = number.getNumber();
        assertEquals(expected, actual);
    }

    @org.junit.Test
    public void plusLiteralValue() {
        int[] expected = {0,1,3};
        number.plusLiteralValue();
        int[] actual = number.getLiteralValues();
        assertArrayEquals(expected, actual);
    }


}