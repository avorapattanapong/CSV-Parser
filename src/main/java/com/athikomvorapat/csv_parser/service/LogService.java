package com.athikomvorapat.csv_parser.service;

import com.athikomvorapat.csv_parser.dto.LogDto;
import com.athikomvorapat.csv_parser.dto.ParsedLogDto;
import java.util.List;

public interface LogService {

  List<LogDto> getLogs();

  void saveLogs(List<ParsedLogDto> parsedLogs);

  void clearLogs();

}
