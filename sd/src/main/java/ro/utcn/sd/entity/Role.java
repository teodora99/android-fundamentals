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
@Table(name = "role")
public class Role {

    @Id
    private String id;

    @Column
    private String role;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private List<User> users;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private List<Admin> admins;
}
