package ro.utcn.sd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "application")
public class Application {

    @Id
    private String id;

    @Column
    private LocalDate date;

    @Column
    private String additionalNotes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "internship_id")
    private Internship internship;


}
