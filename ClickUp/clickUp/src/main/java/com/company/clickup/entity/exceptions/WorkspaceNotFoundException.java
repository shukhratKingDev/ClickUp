package com.company.clickup.entity.exceptions;

public class WorkspaceNotFoundException extends Exception {
    String message;
    public WorkspaceNotFoundException(String s) {
       message=s;
    }

}
