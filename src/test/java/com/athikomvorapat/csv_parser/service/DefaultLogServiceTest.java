package com.athikomvorapat.csv_parser.service;

import static org.junit.Assert.assertEquals;

import com.athikomvorapat.csv_parser.domain.Log;
import com.athikomvorapat.csv_parser.dto.LogDto;
import com.athikomvorapat.csv_parser.dto.ParsedLogDto;
import com.athikomvorapat.csv_parser.respository.LogRepository;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DefaultLogServiceTest {

  LogRepository logRepository;
  DefaultLogService service;

  @Before
  public void setUp() {
    logRepository = Mockito.mock(LogRepository.class);
    service = new DefaultLogService(logRepository);
  }

  @Test
  public void testGetLogs_HappyPath() {
    Log log1 = Log.builder()
        .description("description1")
        .pid(23452L)
        .startTime(LocalTime.now().minusHours(1))
        .endTime(LocalTime.now())
        .build();

    Log log2 = Log.builder()
        .description("description2")
        .pid(234332L)
        .startTime(LocalTime.now().minusHours(1))
        .endTime(LocalTime.now())
        .build();

    List<Log> logs = Arrays.asList(log1, log2);
    Mockito.when(logRepository.findAll()).thenReturn(logs);
    List<LogDto> logDtos = service.getLogs();

    assertEquals(2, service.getLogs().size());
    assertEquals(log1.getDescription(), service.getLogs().get(0).getDescription());
    assertEquals(log1.getPid(), service.getLogs().get(0).getPid());
    assertEquals(log1.getStartTime(), service.getLogs().get(0).getStartTime());
    assertEquals(log1.getEndTime(), service.getLogs().get(0).getEndTime());

    assertEquals(log2.getDescription(), service.getLogs().get(1).getDescription());
    assertEquals(log2.getPid(), service.getLogs().get(1).getPid());
    assertEquals(log2.getStartTime(), service.getLogs().get(1).getStartTime());
    assertEquals(log2.getEndTime(), service.getLogs().get(1).getEndTime());
  }

  @Test
  public void testSaveLogs_HappyPath() {
    ParsedLogDto parsedLogDto = ParsedLogDto.builder()
        .description("description1")
        .pid(23452L)
        .time(LocalTime.now().minusHours(1))
        .timeType(ParsedLogDto.TimeType.START)
        .build();

    ParsedLogDto parsedLogDto2 = ParsedLogDto.builder()
        .description("description1")
        .pid(23452L)
        .time(LocalTime.now())
        .timeType(ParsedLogDto.TimeType.END)
        .build();

    service.saveLogs(Arrays.asList(parsedLogDto, parsedLogDto2));

    List<Log> logs = Arrays.asList(
        Log.builder()
            .description("description1")
            .pid(23452L)
            .startTime(parsedLogDto.getTime())
            .endTime(parsedLogDto2.getTime())
            .build()
    );
    Mockito.verify(logRepository, Mockito.times(1)).saveAll(logs);
  }

  @Test
  public void testClearLogs_HappyPath() {
    service.clearLogs();
    Mockito.verify(logRepository, Mockito.times(1)).deleteAll();
  }
}
