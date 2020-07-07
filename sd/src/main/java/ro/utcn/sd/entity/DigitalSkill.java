package ro.utcn.sd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@Entity
@DiscriminatorValue("DigitalSkill")
public class DigitalSkill extends Skill {
}
