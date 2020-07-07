package ro.utcn.sd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "city")
public class City {

    @Id
    private String id;

    @Column
    private String city;

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name = "internship_city",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "internship_id"))
    private List<Internship> internships;
}
