package com.machinarymgmt.service.api.builder;

import com.machinarymgmt.service.api.config.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
public class ApiResponseBuilder {

    public <T> BaseApiResponse<T> buildSuccessResponse(T data) {
        return BaseApiResponse.<T>builder()
                .status(Status.SUCCESS)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public <T> BaseApiResponse<T> buildSuccessResponse(T data, String message) {
        ApiMessage apiMessage = ApiMessage.builder()
                .message(message)
                .type(MessageType.INFO)
                .build();

        return BaseApiResponse.<T>builder()
                .status(Status.SUCCESS)
                .messages(Collections.singletonList(apiMessage))
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public <T> BaseApiResponse<T> buildPagedResponse(Page<T> page) {
        Metadata metadata = Metadata.builder()
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .build();

        return BaseApiResponse.<T>builder()
                .status(Status.SUCCESS)
                .data((T) page.getContent())
                .metadata(metadata)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public <T> BaseApiResponse<T> buildErrorResponse(String errorMessage, ErrorType errorType) {
        ApiMessage apiMessage = ApiMessage.builder()
                .message(errorMessage)
                .type(MessageType.ERROR)
                .errorType(errorType)
                .build();

        return BaseApiResponse.<T>builder()
                .status(Status.ERROR)
                .messages(Collections.singletonList(apiMessage))
                .timestamp(LocalDateTime.now())
                .build();
    }

    public <T> BaseApiResponse<T> buildErrorResponse(List<ApiMessage> errorMessages) {
        return BaseApiResponse.<T>builder()
                .status(Status.ERROR)
                .messages(errorMessages)
                .timestamp(LocalDateTime.now())
                .build();
    }
}

