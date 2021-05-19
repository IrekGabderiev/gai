package com.irek.gai.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

@Component
public class IssueOfNumbers {
    private final static int MAX_AMOUNT_OF_NUMBERS = 1_728_000; //максимальное количество номеров для региона = 1 728 000
    private final Random random = new Random();
    private final Set<Number> numberSet = new LinkedHashSet<>();
    private final Logger logger = LoggerFactory.getLogger(IssueOfNumbers.class);

    public String randomNumber() {
        if (numberSet.size() >= MAX_AMOUNT_OF_NUMBERS) {
            logger.info("Все номера выданы, программа успешно завершена");
            System.exit(0);
        }
        Number newNumber = new Number();
        go:
        {
            newNumber.setNumericalValues(random.nextInt(1000));
            int[] array = {0, 0, 0};
            for (int i = 0; i < 3; i++) {
                array[i] = random.nextInt(12);
            }
            newNumber.setLiteralValues(array);

            //проверяем не выдан ли этот номер ранее
            if (numberSet.contains(newNumber)) {
                break go;
            }
        }
        numberSet.add(newNumber);
        logger.info("Выдан рандомный номер: " + newNumber.getNumber());
        return newNumber.getNumber();

    }

    public String nextNumber() {
        if (numberSet.size() >= MAX_AMOUNT_OF_NUMBERS) {
            logger.info("Все номера выданы, программа успешно завершена");
            System.exit(0);
        }
        Number newNumber = new Number();
        Number previousNumber;
        go:
        {
            if (numberSet.size() != 0) {
                previousNumber = (Number) numberSet.toArray()[numberSet.size() - 1]; //получаем последний выданный номер
            } else {
                //если до random выполнить next
                newNumber.setLiteralValues(new int[]{0, 0, 0});
                newNumber.setNumericalValues(0);
                numberSet.add(newNumber);
                return newNumber.getNumber();
            }
            newNumber.setNumericalValues(previousNumber.getNumericalValues() + 1);
            newNumber.setLiteralValues(previousNumber.getLiteralValues());
            if (newNumber.getNumericalValues() == 1000) {
                newNumber.setNumericalValues(0);
                newNumber.plusLiteralValue();
            }

            if (numberSet.contains(newNumber)) {
                numberSet.remove(newNumber);
                numberSet.add(newNumber);
                break go;
            }
        }
        numberSet.add(newNumber);
        logger.info("Выдан следующий номер: " + newNumber.getNumber());
        return newNumber.getNumber();

    }
}
