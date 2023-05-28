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
            @RequestParam int addendA,
            @RequestParam int addendB
    ) {
        int result = calculator.add(addendA, addendB);
        char operation = '+';
        String resultDescription = String.format("%d %s %d = %d", addendA, operation, addendB, result);

        return new ResponseAdd(
                result,
                resultDescription,
                addendA,
                addendB,
                operation
        );
    }

    // --------------------
    // Responses Definitions
    // --------------------
    private record ResponseAdd (int result, String resultDescription, int addendA, int addendB, char operation) {}


}
