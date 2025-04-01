package com.machinarymgmt.service.api.config.dto;

import lombok.Getter;

@Getter
public enum MessageType {
    INFO("Info"),
    ERROR("Error"),
    WARNING("Warning");

    private final String value;

    MessageType(String value) {
        this.value = value;
    }
}

