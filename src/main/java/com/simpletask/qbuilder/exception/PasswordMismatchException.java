package com.simpletask.qbuilder.exception;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException(Integer userId) {
        super("Password mismatch for user with id:" + userId);
    }
}
