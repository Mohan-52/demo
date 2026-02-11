package com.mohan.demo.exception;

public class ResourceAlreadyExistEx extends RuntimeException {
    public ResourceAlreadyExistEx(String message) {
        super(message);
    }
}
