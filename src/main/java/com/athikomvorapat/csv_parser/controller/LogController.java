package com.athikomvorapat.csv_parser.controller;

import com.athikomvorapat.csv_parser.dto.ApiCollectionResponse;
import com.athikomvorapat.csv_parser.dto.LogDto;
import com.athikomvorapat.csv_parser.dto.ParsedLogDto;
import com.athikomvorapat.csv_parser.service.CSVParserService;
import com.athikomvorapat.csv_parser.service.LogService;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/logs")
@CrossOrigin(origins="*")
public class LogController {

  private final LogService logService;
  private final CSVParserService csvParserService;

  public LogController(LogService logService, CSVParserService csvParserService) {
    this.logService = logService;
    this.csvParserService = csvParserService;
  }

  @GetMapping
  public ApiCollectionResponse<LogDto> getLogs() {
    List<LogDto> logs = logService.getLogs();

    return ApiCollectionResponse.<LogDto>builder()
        .total(logs.size())
        .data(logs)
        .build();
  }

  @PostMapping
  public void uploadLogs(
      @RequestParam("file") MultipartFile file) throws IOException {

    List<ParsedLogDto> parsedLogs = csvParserService.parseFile(file);
    logService.saveLogs(parsedLogs);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAllLogs() {
    logService.clearLogs();
  }
}
