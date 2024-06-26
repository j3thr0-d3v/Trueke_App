package com.j3thr0.trueke.truekeapi.error.model.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.j3thr0.trueke.truekeapi.error.model.ApiSubError;
import lombok.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiValidationSubError extends ApiSubError {

    private String object;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String field;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object rejectedValue;

    public static ApiValidationSubError fromObjectError(ObjectError objectError){
        if(objectError instanceof FieldError){
            FieldError fieldError = (FieldError) objectError;
            return ApiValidationSubError.builder()
                    .object(objectError.getObjectName())
                    .field(fieldError.getField())
                    .rejectedValue(fieldError.getRejectedValue())
                    .message(fieldError.getDefaultMessage())
                    .build();

        }else {
            return ApiValidationSubError.builder()
                    .object(objectError.getObjectName())
                    .message(objectError.getDefaultMessage())
                    .build();
        }
    }
}
