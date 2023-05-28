package com.koubae.calculator.app.controllers;

import com.koubae.calculator.app.services.ServiceCalculator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class Calculator {
    private final ServiceCalculator calculator;

    public Calculator(ServiceCalculator calculator) {
        this.calculator = calculator;
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseAdd add(
            @RequestParam int valueA,
            @RequestParam int valueB
    ) {
        int result = calculator.add(valueA, valueB);
        char operation = '+';
        String resultDescription = String.format("%d %s %d = %d", valueA, operation, valueB, result);

        return new ResponseAdd(
                result,
                resultDescription,
                valueA,
                valueB,
                operation
        );
    }

    // --------------------
    // Responses Definitions
    // --------------------
    private record ResponseAdd (int result, String resultDescription, int addendA, int addendB, char operation) {}


}
