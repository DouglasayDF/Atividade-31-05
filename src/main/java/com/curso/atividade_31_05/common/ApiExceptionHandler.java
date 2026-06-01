package com.curso.atividade_31_05.common;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleNotFound(EntityNotFoundException ex) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        detail.setProperty("timestamp", LocalDateTime.now());
        return detail;
    }

    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    public ProblemDetail handleBusinessException(RuntimeException ex) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        detail.setProperty("timestamp", LocalDateTime.now());
        return detail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Dados invalidos.");
        detail.setProperty("timestamp", LocalDateTime.now());
        return detail;
    }
}
