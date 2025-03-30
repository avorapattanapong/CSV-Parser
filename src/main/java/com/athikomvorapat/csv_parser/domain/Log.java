package com.athikomvorapat.csv_parser.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.Instant;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private Long pid;
    private String description;
    @Column(name = "start_time", columnDefinition = "TIME")
    private LocalTime startTime;
    @Column(name = "end_time", columnDefinition = "TIME")
    private LocalTime endTime;
    @CreationTimestamp
    private Instant createdOn;
}
