package com.athikomvorapat.csv_parser.service;

import com.athikomvorapat.csv_parser.domain.Log;
import com.athikomvorapat.csv_parser.dto.LogDto;
import com.athikomvorapat.csv_parser.dto.ParsedLogDto;
import com.athikomvorapat.csv_parser.respository.LogRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class DefaultLogService implements LogService {

  private LogRepository logRepository;

  public DefaultLogService(LogRepository logRepository) {
    this.logRepository = logRepository;
  }

  @Override
  public List<LogDto> getLogs() {
    List<Log> logs = logRepository.findAll();
    List<LogDto> logDtos = new ArrayList<>();
    logs.forEach(log -> {
      logDtos.add(LogDto.builder()
          .id(log.getId())
          .startTime(log.getStartTime())
          .endTime(log.getEndTime())
          .description(log.getDescription())
          .pid(log.getPid())
          .build());
    });

    return logDtos;
  }

  /**
   * ParseLogs contains time that is either a START or END time of a process. We need to consolidate
   * these parsed logs into a record that contains both start and end time of the same pid.
   *
   * Iterating over each ParsedLogs, we create an entry in the map if pid does not exist. The Log entry is
   * created with either start or end time. If the pid already exists in the map, we update the start or end
   * This assume that ParsedLogs do not contain more than two entries for the same pid.
   *
   * @param parsedLogs
   */
  @Override
  public void saveLogs(List<ParsedLogDto> parsedLogs) {
    Map<Long, Log> logDtoMap = new HashMap<>();

    parsedLogs.forEach(parsedLogDto -> {
      // If the pid does not exist in the map, create a new Log entry
      if (!logDtoMap.containsKey(parsedLogDto.getPid())){
        logDtoMap.put(parsedLogDto.getPid(), getLog(parsedLogDto));
      } else {
        // If the pid already exists in the map, update the start or end time
        // The assumption is that ParsedLogs do not contain more than two entries for the same pid
        if (parsedLogDto.getTimeType() == ParsedLogDto.TimeType.START) {
          logDtoMap.get(parsedLogDto.getPid()).setStartTime(parsedLogDto.getTime());
        } else {
          logDtoMap.get(parsedLogDto.getPid()).setEndTime(parsedLogDto.getTime());
        }
      }
    });

    List<Log> logs = logDtoMap.values()
        .stream()
        .toList();

    logRepository.saveAll(logs);
  }

  @Override
  public void clearLogs() {
    logRepository.deleteAll();
  }

  private Log getLog(ParsedLogDto parsedLogDto) {
    Log log = Log.builder()
        .description(parsedLogDto.getDescription())
        .pid(parsedLogDto.getPid())
        .build();

    if (parsedLogDto.getTimeType() == ParsedLogDto.TimeType.START) {
      log.setStartTime(parsedLogDto.getTime());
    } else {
      log.setEndTime(parsedLogDto.getTime());
    }

    return log;
  }
}
