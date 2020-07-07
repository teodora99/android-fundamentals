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
@Table(name = "admin")
public class Admin {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.REMOVE)
    private List<Internship> internships;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
