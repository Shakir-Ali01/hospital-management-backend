package com.hms.user.utility;
import com.hms.user.exceptions.HmsExceptions;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @Autowired
    Environment environment;
    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);


    @ExceptionHandler(NestedServletException.class)
public ResponseEntity<ErrorInfo> handleNestedServletException(NestedServletException ex) {
    Throwable cause = ex.getCause();
    if (cause instanceof HmsExceptions) {
        return hmsExceptionHandler((HmsExceptions) cause);
    } else if (cause instanceof MethodArgumentNotValidException) {
        return handleMethodArgNotValid((MethodArgumentNotValidException) cause);
    } else if (cause instanceof ConstraintViolationException) {
        return handleConstraintViolation((ConstraintViolationException) cause);
    }
    ErrorInfo errorInfo = new ErrorInfo();
    errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    errorInfo.setErrorMessage("Some Error Occurred");
    errorInfo.setTimestamp(LocalDateTime.now());
    return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
}
    @ExceptionHandler(HmsExceptions.class)
    public ResponseEntity<ErrorInfo> hmsExceptionHandler(HmsExceptions e) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorInfo.setErrorMessage(environment.getProperty(e.getMessage()));
        errorInfo.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> handleMethodArgNotValid(MethodArgumentNotValidException ex) {
        logger.error("handleMethodArgNotValid triggered");
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setErrorMessage(errorMessage);
        errorInfo.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> handleConstraintViolation(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining(", "));
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setErrorMessage(errorMessage);
        errorInfo.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorInfo> exceptionHandler(Exception e) {
        logger.error("generic exceptionHandler triggered: " + e.getClass().getName(), e);
       ErrorInfo errorInfo = new ErrorInfo();
       errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
       errorInfo.setErrorMessage("Some Error Occurred");
       errorInfo.setTimestamp(LocalDateTime.now());
       return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
