package com.eg.ihse.controller.helper;


import com.eg.ihse.controller.response.ErrorResponse;

import com.eg.ihse.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {
    Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ErrorResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error(e.getMessage(), e);
        return new ErrorResponse(Constant.OOPS_SOMETHING_UNEXPECTED_HAPPENED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = { RuntimeException.class })
    protected ErrorResponse handleRuntimeException(RuntimeException ex, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        return new ErrorResponse(Constant.OOPS_SOMETHING_UNEXPECTED_HAPPENED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { NoSuchElementException.class })
    protected ErrorResponse handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {

        return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {

        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {

        String validationErrors = String.join(", ",
                ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));

        return new ErrorResponse(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    protected ErrorResponse handleUnAuthorizedException(HttpMessageNotReadableException ex, WebRequest request) {

        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
    protected ErrorResponse handleUnAuthorizedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {

        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
