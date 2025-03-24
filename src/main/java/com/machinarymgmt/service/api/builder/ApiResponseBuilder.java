package com.machinarymgmt.service.api.builder;

import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
import com.machinarymgmt.service.api.config.dto.Metadata;
import com.machinarymgmt.service.api.config.dto.Status;
import com.machinarymgmt.service.api.utils.Constants;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ApiResponseBuilder {

    private final Tracer tracer;

    public BaseApiResponse buildSuccessApiResponse(String statusMessage) {
        BaseApiResponse response = new BaseApiResponse()
                .metadata(new Metadata().timestamp(Instant.now())
                        .traceId(null != tracer.currentSpan()
                                ? Objects.requireNonNull(tracer.currentSpan()).context().traceId()
                                : ""))
                .status(new Status().statusCode(HttpStatus.OK.value()).statusMessage(statusMessage)
                        .statusMessageKey(Constants.RESPONSE_MESSAGE_KEY_SUCCESS));
        return response;
    }


}
