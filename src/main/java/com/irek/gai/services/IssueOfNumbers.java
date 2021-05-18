package com.irek.gai.services;

import com.irek.gai.services.Number;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

@Component
public class IssueOfNumbers {
    private final Random random = new Random();
    private final Set<Number> numberSet = new LinkedHashSet<>();

    public String randomNumber() {
        Number newNumber = new Number();
        newNumber.setNumericalValues(random.nextInt(1000));
        int[] array = {0,0,0};
        for (int i = 0; i < 3; i++) {
            array[i] = random.nextInt(12);
        }
        newNumber.setLiteralValues(array);
        //проверяем не выдан ли этот номер ранее
        if (numberSet.contains(newNumber)) {
            randomNumber();
        }
        numberSet.add(newNumber);
        return newNumber.getNumber();
    }

    public String nextNumber() {
        Number newNumber = new Number();
        Number previousNumber;
        if(numberSet.size() != 0) {
            previousNumber = (Number) numberSet.toArray()[numberSet.size() - 1]; //получаем последний выданный номер
        }else{
            //если до random выполнить next
            newNumber.setLiteralValues(new int[]{0,0,0});
            newNumber.setNumericalValues(0);
            numberSet.add(newNumber);
            return newNumber.getNumber();
        }
        newNumber.setNumericalValues(previousNumber.getNumericalValues() + 1);
        newNumber.setLiteralValues(previousNumber.getLiteralValues());
        if(newNumber.getNumericalValues() == 1000) {
            newNumber.setNumericalValues(0);
            newNumber.plusLiteralValue();
        }
        numberSet.add(newNumber);
        return newNumber.getNumber();
    }
}
