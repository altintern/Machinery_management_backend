package com.machinarymgmt.service.api.config.dto;

import lombok.Getter;

@Getter
public enum ErrorType {
    VALIDATION_ERROR("Validation Error"),
    SYSTEM_ERROR("System Error"),
    NOT_FOUND("Not Found"),
    DUPLICATE("Duplicate Entry"),
    UNAUTHORIZED("Unauthorized");

    private final String value;

    ErrorType(String value) {
        this.value = value;
    }
}

