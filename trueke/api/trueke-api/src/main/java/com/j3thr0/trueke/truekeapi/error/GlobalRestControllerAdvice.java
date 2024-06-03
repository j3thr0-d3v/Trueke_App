package com.j3thr0.trueke.truekeapi.error;

import com.j3thr0.trueke.truekeapi.error.model.impl.ApiErrorImpl;
import com.j3thr0.trueke.truekeapi.error.model.impl.ApiValidationSubError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request
    ) {
        return buildApiError(ex, request, HttpStatus.NOT_FOUND);
    }

    private final ResponseEntity<Object> buildApiError(Exception ex, WebRequest request, HttpStatus status){
        return ResponseEntity
                .status(status)
                .body(
                        ApiErrorImpl.builder()
                                .status(status)
                                .message(ex.getMessage())
                                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                                .build()
                );
    }

    private final ResponseEntity<Object> buildApiErrorWithSubError(
            String message, WebRequest request, HttpStatus status, List<ObjectError> subErrors
    ){
        return ResponseEntity
                .status(status)
                .body(
                        ApiErrorImpl.builder()
                                .status(status)
                                .message(message)
                                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                                .subErrors(
                                        subErrors.stream()
                                                .map(ApiValidationSubError::fromObjectError)
                                                .collect(Collectors.toList())
                                ).build()
                );
    }
}
