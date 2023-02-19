package com.example.projectone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class StudentNotFoundHandler {
    @ExceptionHandler(value = {StudentNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException exception){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StudentNotFoundExceptionResponse studentNotFoundException = new StudentNotFoundExceptionResponse(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(studentNotFoundException, httpStatus);
    }
}
