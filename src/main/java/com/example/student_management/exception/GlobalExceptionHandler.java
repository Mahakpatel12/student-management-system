package com.example.student_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFound(StudentNotFoundException ex){

        ErrorResponse errorResponse = new ErrorResponse(404,ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        StringBuilder sb = new StringBuilder();

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        for(FieldError error: fieldErrors){
                  sb.append(error.getField())
                    .append(": ")
                    .append(error.getDefaultMessage())
                    .append(", ");
        }

        if(sb.length() > 0){
            sb.setLength(sb.length() - 2);
        }

        ErrorResponse errorResponse = new ErrorResponse(400,sb.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}



