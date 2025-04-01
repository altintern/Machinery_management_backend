package com.machinarymgmt.service.api.config.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiMessage {
    private String message;
    private MessageType type;
    private ErrorType errorType;
}

