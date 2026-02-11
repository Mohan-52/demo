package com.mohan.demo.exception;

public class ResourceNotExistsEx extends RuntimeException {
    public ResourceNotExistsEx(String message) {
        super(message);
    }
}
