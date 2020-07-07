package ro.utcn.sd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@Entity
@DiscriminatorValue("CommunicationSkill")
public class CommunicationSkill extends Skill {
}
