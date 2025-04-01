package com.machinarymgmt.service.api.config.dto;

import lombok.Getter;

@Getter
public enum Status {
    SUCCESS("Success"),
    ERROR("Error"),
    WARNING("Warning");

    private final String value;

    Status(String value) {
        this.value = value;
    }
}

