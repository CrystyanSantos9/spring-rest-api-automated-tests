package dev.cryss.apiautomatedtests.controllers;

import dev.cryss.apiautomatedtests.exceptions.UnsupportedMathOperationException;
import dev.cryss.apiautomatedtests.utils.SimpleMath;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import static dev.cryss.apiautomatedtests.utils.NumberConverter.convertToDouble;
import static dev.cryss.apiautomatedtests.utils.NumberConverter.isNumeric;

@RestController
public class MathController {
    private final AtomicLong counter = new AtomicLong ();
    private SimpleMath math = new SimpleMath ();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!isNumeric (numberOne) || !isNumeric (numberTwo)) {
            throw new UnsupportedMathOperationException ("Please set a numeric value in path variables!");
        }

        return math.sum (convertToDouble (numberOne), convertToDouble (numberTwo));
    }

    @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double subtraction(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!isNumeric (numberOne) || !isNumeric (numberTwo)) {
            throw new UnsupportedMathOperationException ("Please set a numeric value in path variables!");
        }

        return math.sub (convertToDouble (numberOne), convertToDouble (numberTwo));
    }

    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double multiplication(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!isNumeric (numberOne) || !isNumeric (numberTwo)) {
            throw new UnsupportedMathOperationException ("Please set a numeric value in path variables!");
        }

        return math.mult (convertToDouble (numberOne), convertToDouble (numberTwo));
    }

    @RequestMapping(value = "/division/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double division(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!isNumeric (numberOne) || !isNumeric (numberTwo)) {
            throw new UnsupportedMathOperationException ("Please set a numeric value in path variables!");
        }

        return math.div (convertToDouble (numberOne), convertToDouble (numberTwo));
    }

    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double mean(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!isNumeric (numberOne) || !isNumeric (numberTwo)) {
            throw new UnsupportedMathOperationException ("Please set a numeric value in path variables!");
        }

        return math.mean (convertToDouble (numberOne), convertToDouble (numberTwo));
    }

    @RequestMapping(value = "/squareRoot/{number}",
            method = RequestMethod.GET)
    public Double squareRoot(
            @PathVariable(value = "number") String number
    ) throws Exception {

        if (!isNumeric (number)) {
            throw new UnsupportedMathOperationException ("Please set a numeric value in path variables!");
        }

        return math.sqrt (convertToDouble (number));
    }


}
