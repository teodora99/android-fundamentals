package ro.utcn.sd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "study")
public class Study {

    @Id
    private String id;

    @Column
    private LocalDate yearBegin;

    @Column
    private LocalDate yearEnd;

    @Column
    private String institution;

    @Column
    private String certification;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
