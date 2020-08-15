package com.simpletask.qbuilder.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(int userId) {
        super("Access for user with id:" + userId);
    }
}
