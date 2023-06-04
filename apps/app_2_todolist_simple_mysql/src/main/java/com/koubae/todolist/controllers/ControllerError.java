package com.koubae.todolist.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControllerError implements ErrorController {
    private record ResponseError (int status, String url, String error, String description) {}

    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseError handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String url = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        String description = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        int statusCode = -1;
        String error = "Something Went Wrong";
        if (status != null) {
            statusCode = Integer.parseInt(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                error = "Error 404 - Not Found";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                error = "Error 500 - Internal Server Error";
            }
        }
        if (exception != null) {
            if (description.isEmpty()) {
                description = String.format("Error %s", exception);
            } else {
                description += String.format(", error %s", exception);
            }
        }

        return new ResponseError(
                statusCode,
                url,
                error,
                description
        );

    }
}
