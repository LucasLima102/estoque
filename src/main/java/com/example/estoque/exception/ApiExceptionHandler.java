package com.example.estoque.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException exception) {
        return buildResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, String> campos = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                campos.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> body = defaultBody(HttpStatus.BAD_REQUEST, "Dados invalidos");
        body.put("campos", campos);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException exception) {
        Map<String, String> campos = new LinkedHashMap<>();
        exception.getConstraintViolations().forEach(violation ->
                campos.put(violation.getPropertyPath().toString(), violation.getMessage())
        );

        Map<String, Object> body = defaultBody(HttpStatus.BAD_REQUEST, "Parametro de rota invalido");
        body.put("campos", campos);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleBadCredentials() {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Usuario ou senha invalidos");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrity() {
        return buildResponse(HttpStatus.CONFLICT, "Registro viola uma restricao de integridade");
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(defaultBody(status, message));
    }

    private Map<String, Object> defaultBody(HttpStatus status, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("erro", status.getReasonPhrase());
        body.put("mensagem", message);
        return body;
    }
}
