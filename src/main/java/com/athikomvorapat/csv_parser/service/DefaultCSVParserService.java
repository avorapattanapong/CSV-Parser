package com.athikomvorapat.csv_parser.service;

import com.athikomvorapat.csv_parser.dto.ParsedLogDto;
import com.athikomvorapat.csv_parser.dto.ParsedLogDto.TimeType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
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
    Map<Long, Integer> pidCount = new HashMap<>();
    List<ParsedLogDto> parsedLogs = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length != EXPECTED_PARTS) {
          throw new CSVParserServiceException("Invalid log entry: more parts than expected " + line);
        }

        Long pid = Long.parseLong(parts[3]);
        if (pidCount.containsKey(pid)) {
          pidCount.put(pid, pidCount.get(pid) + 1);
          if (pidCount.get(pid) > 2) {
            throw new CSVParserServiceException("Invalid log entry: more than 2 entries for pid " + pid);
          }
        } else {
          pidCount.put(pid, 1);
        }

        try {
          LocalTime time = LocalTime.parse(parts[0]);
          TimeType timeType = TimeType.valueOf(parts[2].trim().toUpperCase());
          ParsedLogDto parsedLog = ParsedLogDto.builder()
              .time(time)
              .timeType(timeType)
              .description(parts[1])
              .pid(pid)
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
