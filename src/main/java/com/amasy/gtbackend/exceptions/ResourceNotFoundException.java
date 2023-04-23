package com.amasy.gtbackend.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long resourceValue;
    public ResourceNotFoundException(String resourceName, String fieldName, long resourceValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, resourceValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.resourceValue = resourceValue;
    }
}
