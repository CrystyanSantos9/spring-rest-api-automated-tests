package dev.cryss.apiautomatedtests.utils;

import static dev.cryss.apiautomatedtests.utils.NumberConverter.convertToDouble;

public class SimpleMath {
    public Double sum(Double numberOne, Double numberTwo) {
        return numberOne + numberOne;
    }


    public Double sub(Double numberOne, Double numberTwo) {
        return numberOne - numberOne;
    }

    public Double mult(Double numberOne, Double numberTwo) {
        return numberOne * numberOne;
    }

    public Double div(Double numberOne, Double numberTwo) {
        return numberOne / numberTwo;
    }

    public Double mean(Double numberOne, Double numberTwo) {
        return (numberOne + numberTwo) / 2;
    }

    public Double sqrt(Double number) {
        return Math.sqrt (number);
    }


}
