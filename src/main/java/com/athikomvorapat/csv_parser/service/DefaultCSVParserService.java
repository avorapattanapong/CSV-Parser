package com.athikomvorapat.csv_parser.service;

import com.athikomvorapat.csv_parser.dto.ParsedLogDto;
import com.athikomvorapat.csv_parser.dto.ParsedLogDto.TimeType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class DefaultCSVParserService implements CSVParserService {

  private static final int EXPECTED_PARTS = 4;
  /**
   * Parse a csv file of the format: time, description, <START or END>, pid
   * @param file the file to parse
   * @return
   * @throws IOException
   */
  @Override
  public List<ParsedLogDto> parseFile(MultipartFile file) throws IOException {
    List<ParsedLogDto> parsedLogs = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length != EXPECTED_PARTS) {
          throw new CSVParserServiceException("Invalid log entry: more parts than expected " + line);
        }
        try {
          LocalTime time = LocalTime.parse(parts[0]);
          TimeType timeType = TimeType.valueOf(parts[2].trim().toUpperCase());
          ParsedLogDto parsedLog = ParsedLogDto.builder()
              .time(time)
              .timeType(timeType)
              .description(parts[1])
              .pid(Long.parseLong(parts[3]))
              .build();
          parsedLogs.add(parsedLog);
        } catch (DateTimeParseException | IllegalArgumentException e) {
          throw new CSVParserServiceException("Invalid log entry: " + line, e);
        }
      }
    }
    return parsedLogs;
  }
}
