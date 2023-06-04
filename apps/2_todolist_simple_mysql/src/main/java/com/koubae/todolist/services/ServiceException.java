package com.koubae.todolist.services;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {
    private final String messageOriginal;
    private final HttpStatus code;

    public ServiceException(HttpStatus code, String message) {
        super(message);
        this.code = code;
        this.messageOriginal = message;
   }

   public String toString() {
        return this.messageOriginal;
   }

   public HttpStatus getCode() {
        return this.code;
   }

}
