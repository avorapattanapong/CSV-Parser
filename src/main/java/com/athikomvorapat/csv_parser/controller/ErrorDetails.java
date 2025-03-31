package com.athikomvorapat.csv_parser.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_EMPTY)
public class ErrorDetails {

  private final String message;
  private final String target;

  public ErrorDetails(String message, String target) {
    this.message = message;
    this.target = target;
  }

}
