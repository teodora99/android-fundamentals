package ro.utcn.sd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "skill")
public class Skill {

    @Id
    private String id;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
