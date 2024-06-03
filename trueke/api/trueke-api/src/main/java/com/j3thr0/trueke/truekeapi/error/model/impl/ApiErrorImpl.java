package com.j3thr0.trueke.truekeapi.error.model.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.j3thr0.trueke.truekeapi.error.model.ApiError;
import com.j3thr0.trueke.truekeapi.error.model.ApiSubError;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiErrorImpl implements ApiError
{
    private HttpStatus status;
    private String message;
    private String path;
    private int statusCode;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    @Builder.Default
    private LocalDateTime date = LocalDateTime.now();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ApiSubError> subErrors;

    public int getStatusCode(){
        return status != null ?
                status.value()
                : 0;
    }

}
