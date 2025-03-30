package com.athikomvorapat.csv_parser.service;

public class CSVParserServiceException extends RuntimeException {

  public CSVParserServiceException(String message) {
    super(message);
  }

  public CSVParserServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
