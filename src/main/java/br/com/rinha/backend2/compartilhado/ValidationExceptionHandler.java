package br.com.rinha.backend2.compartilhado;

import jakarta.persistence.OptimisticLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleBeanValidationExceptions() {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }
    @ExceptionHandler(OptimisticLockException.class)
    public ResponseEntity<?> handleLockOtimistaExceptions() {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }
}
