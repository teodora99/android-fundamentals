package ro.utcn.sd.entity;



import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@AllArgsConstructor
@DiscriminatorValue("OrganisationalSkill")
public class OrganisationalSkill extends Skill {

}
