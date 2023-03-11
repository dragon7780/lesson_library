package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Book {
    private Integer id;
    private String title;
    private String author;
    private LocalDate publisherYear;
    private Integer amount;
    private Boolean visible;
}
