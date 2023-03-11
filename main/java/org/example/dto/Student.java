package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.enums.StudentRole;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Student {
    private Integer id;
    private String name;
    private String surname;
    private StudentRole role;
    private String phone;
    private LocalDate createdDate;
    private Boolean visible;
}
