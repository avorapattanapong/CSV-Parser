package com.athikomvorapat.csv_parser.controller;

import com.athikomvorapat.csv_parser.service.CSVParserServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(CSVParserServiceException.class)
  public ResponseEntity<?> handleCSVParserServiceException(CSVParserServiceException ex, WebRequest request) {
    ErrorDetails errorDetails = ErrorDetails.builder()
        .message(ex.getMessage())
        .build();
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
    ErrorDetails errorDetails = ErrorDetails.builder()
        .message(ex.getMessage())
        .build();
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }


}
