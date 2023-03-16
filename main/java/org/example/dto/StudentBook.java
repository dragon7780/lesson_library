package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.enums.BookRole;

import javax.persistence.*;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "studentBook")
public class StudentBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //StudentBook (id,student_id,book_id,createdDate,status(TAKEN,RETURNED),returnedDate,duration)
    private Integer id;
    private Integer student_id;
    private Integer book_id;
    private LocalDate createdDate;
    private String status;
    private LocalDate returnedDate;
    private Integer duration;
}
