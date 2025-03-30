package com.athikomvorapat.csv_parser.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.athikomvorapat.csv_parser.dto.ParsedLogDto;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class DefaultCSVParserServiceTest {

  private final String TEST_FILE_PATH = "src/test/resources/";
  private final String TEST_FILE_NAME = "sample.log";
  private MultipartFile testFile;

  private DefaultCSVParserService service = new DefaultCSVParserService();

  @Before
  public void setup() throws Exception {
    Path path = Paths.get(TEST_FILE_PATH + TEST_FILE_NAME);
    byte[] content = Files.readAllBytes(path);
    testFile =  new MockMultipartFile(TEST_FILE_NAME, TEST_FILE_NAME, "text/plain", content);
  }

  @Test
  public void testParseFile() throws IOException {
    List<ParsedLogDto> parsedLogs = service.parseFile(testFile);

    // Assert that the parsedLogs list is not empty
    assertNotEquals(0, parsedLogs.size());

    // Assert that the first parsed log has the correct values
    ParsedLogDto firstLog = parsedLogs.get(0);
    assertNotEquals(null, firstLog);
    assertEquals("11:35:23", firstLog.getTime().toString());
    assertEquals("scheduled task 032", firstLog.getDescription());
    assertEquals(ParsedLogDto.TimeType.START, firstLog.getTimeType());
    assertEquals(37980, firstLog.getPid().longValue());
  }

  @Test
  public void testParseFileWithInvalidTime() throws IOException {
    MockMultipartFile file
        = new MockMultipartFile(
        "file",
        "hello.txt",
        MediaType.TEXT_PLAIN_VALUE,
        "Hello, World!".getBytes()
    );

    try {
      service.parseFile(testFile);
    } catch (CSVParserServiceException e) {
      assertEquals(DateTimeParseException.class, e.getCause().getClass());
    }
  }

  @Test
  public void testParseFileInvalidParts() throws IOException {
    MockMultipartFile file
        = new MockMultipartFile(
        "file",
        "hello.txt",
        MediaType.TEXT_PLAIN_VALUE,
        "hello,world,new,world,test".getBytes()
    );

    try {
      service.parseFile(testFile);
    } catch (CSVParserServiceException e) {
      assertEquals("Invalid log entry: more parts than expected hello,world,new,world,test", e.getMessage());
    }
  }

}
