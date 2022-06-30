package com.company.clickup.entity.exceptions;

public class SpaceNotFoundException extends Exception {
    private String message;
    public SpaceNotFoundException(String s) {
        this.message=s;
    }
}
