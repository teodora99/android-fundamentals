package ro.utcn.sd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "workExperience")
public class WorkExperience {

    @Id
    private String id;

    @Column
    private LocalDate yearBegin;

    @Column
    private LocalDate yearEnd;

    @Column
    private String company;

    @Column
    private String jobTitle;

    @Column
    private String jobDescription;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;


}