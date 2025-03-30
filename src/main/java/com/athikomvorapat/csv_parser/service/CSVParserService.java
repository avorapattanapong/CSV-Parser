package com.athikomvorapat.csv_parser.service;

import com.athikomvorapat.csv_parser.dto.ParsedLogDto;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface CSVParserService {

  /**
   * Parse the given file and return a list of ParsedLogDto objects.
   *
   * @param file the file to parse
   * @return a list of ParsedLogDto objects
   * @throws IOException if an I/O error occurs
   */
  List<ParsedLogDto> parseFile(MultipartFile file) throws IOException;

}
