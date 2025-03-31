package com.athikomvorapat.csv_parser.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiCollectionResponse<T> {
  private int total;
  private List<T> data;
}
