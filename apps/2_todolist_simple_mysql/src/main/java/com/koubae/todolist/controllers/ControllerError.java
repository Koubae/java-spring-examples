package com.koubae.todolist.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControllerError implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Exception error = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        String errorMessageTitle = "<h3>Something Went Wrong</h3>";
        String errorMessageBody = String.format("<p>Error: %s </p>", error);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                errorMessageTitle = "<h3>Error 404 - Not Found</h3>";
                errorMessageBody = "<p>What you requested wasn't found!</p>";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorMessageBody = "<h3>Error 500 - Internal Server Error</h3>";
            }
        }
        return errorMessageTitle + errorMessageBody;

    }
}
