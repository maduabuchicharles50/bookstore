package com.charles.bookstore.exception;

import com.charles.bookstore.dto.ErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleException(Throwable throwable, HttpServletRequest request) {

        if (throwable instanceof ModelNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(throwable.getClass().getSimpleName(), throwable.getMessage()));
        }

        if (throwable instanceof DataIntegrityViolationException) {
            return ResponseEntity.unprocessableEntity().body(new ErrorDto(throwable.getClass().getSimpleName(), throwable.getMessage()));
        }

        if (throwable instanceof MethodArgumentNotValidException) {
            var errorDto = new ErrorDto(throwable.getClass().getSimpleName(), "Invalid field value");

            errorDto.fieldErrors = ((MethodArgumentNotValidException) throwable).getFieldErrors().stream().collect(Collectors.toMap(
                    FieldError::getField,
                    item -> item.getDefaultMessage() == null ? "" : item.getDefaultMessage()
            ));

            return ResponseEntity.unprocessableEntity().body(errorDto);
        }

        return ResponseEntity.internalServerError().body(new ErrorDto(throwable.getClass().getSimpleName(), "Unhandled Exception: " + throwable.getMessage()));
    }
}
