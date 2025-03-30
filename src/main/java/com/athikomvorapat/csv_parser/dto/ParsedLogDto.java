package com.athikomvorapat.csv_parser.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * ParsedLogDto is a data transfer object that represents the parsed log data.
 * The parsedLogDto is returned from the ParsingService when it is ready to be processed.
 */
@AllArgsConstructor
@Data
@Builder
public class ParsedLogDto {

  @NotNull
  private Long pid;

  @NotEmpty
  private String description;

  @NotEmpty
  private LocalTime time;

  @NotNull
  private TimeType timeType;

  // Represents whether the record is a start or end time of the corresponding process (pid)
  public enum TimeType {
    START, END
  }

}
