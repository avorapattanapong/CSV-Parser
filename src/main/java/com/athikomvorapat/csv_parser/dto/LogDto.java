package com.athikomvorapat.csv_parser.dto;

import jakarta.persistence.Column;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogDto {

  private Long id;
  private Long pid;
  private String description;
  private LocalTime startTime;
  private LocalTime endTime;

}
