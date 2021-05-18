package com.irek.gai.services;

import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Objects;

@Component
public class Number {
    private String number = "";
    private static final String region = "116 RUS";
    private static final String[] letters = {"A", "B", "E", "K", "M", "H", "O", "P", "C", "T", "Y", "X"};
    private int[] literalValues = {0,0,0};
    private int numericalValues = 0;

    public void plusLiteralValue() {
        literalValues[2]++;
        if (literalValues[2] == 12) {
            literalValues[2] = 0;
            literalValues[1]++;
            if (literalValues[1] == 12) {
                literalValues[1] = 0;
                literalValues[0]++;
                if (literalValues[0] == 12) {
                    for (int i = 0; i < 3; i++) {
                        literalValues[i] = 0;
                    }
                }
            }
        }
    }

    public int[] getLiteralValues() {
        return literalValues;
    }

    public void setLiteralValues(int[] literalValues) {
        this.literalValues = literalValues;
    }

    public int getNumericalValues() {
        return numericalValues;
    }

    public void setNumericalValues(int numericalValues) {
        this.numericalValues = numericalValues;
    }

    public String getNumber() {
        return number = String.format("%s%03d%s%s %s",
                letters[literalValues[0]], numericalValues,
                letters[literalValues[1]], letters[literalValues[2]], region);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number = (Number) o;
        return numericalValues == number.numericalValues && Arrays.equals(literalValues, number.literalValues);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(numericalValues);
        result = 31 * result + Arrays.hashCode(literalValues);
        return result;
    }
}


