package com.athikomvorapat.csv_parser.respository;

import com.athikomvorapat.csv_parser.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {

}
