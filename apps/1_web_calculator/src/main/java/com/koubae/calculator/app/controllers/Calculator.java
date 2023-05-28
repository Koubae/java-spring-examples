package com.koubae.calculator.app.controllers;

import com.koubae.calculator.app.services.ServiceCalculator;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class Calculator {
    private final ServiceCalculator calculator;

    public Calculator(ServiceCalculator _calculator) {
        calculator = _calculator;
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response add(
            @RequestParam int valueA,
            @RequestParam int valueB
    ) {
        final int result = calculator.add(valueA, valueB);
        final char operation = '+';
        return buildResponse(valueA, valueB, result, operation, "");
    }

    @PostMapping(value = "subtract", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response subtract(
        @RequestParam int valueA,
        @RequestParam int valueB
    ) {
        final int result = calculator.subtract(valueA, valueB);
        final char operation = '-';
        return buildResponse(valueA, valueB, result, operation, "");
    }

    @PostMapping(value = "multiply", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response multiply(
            @RequestParam int valueA,
            @RequestParam int valueB
    ) {
        final int result = calculator.multiply(valueA, valueB);
        final char operation = '*';
        return buildResponse(valueA, valueB, result, operation, "");
    }

    @PostMapping(value = "divide", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Response divide(
            @RequestParam int valueA,
            @RequestParam int valueB
    ) {
        final char operation = '/';
        int result = -1;
        String error = "";

        try {
            result = calculator.divide(valueA, valueB);
        } catch (ArithmeticException exception) {
            error = exception.getMessage();
        }

        return buildResponse(valueA, valueB, result, operation, error);
    }

    // --------------------
    // Responses Definitions
    // --------------------
    private record Response (int result, String resultDescription, int addendA, int addendB, char operation, String error) {}

    private Response buildResponse(int valueA, int valueB, int result, char operation, String error) {
        final String RESULT_DESCRIPTION_TEMPLATE = "%d %s %d = %d";
        final String resultDescription = String.format(RESULT_DESCRIPTION_TEMPLATE, valueA, operation, valueB, result);

        return new Response(
                result,
                resultDescription,
                valueA,
                valueB,
                operation,
                error
        );
    }


}
