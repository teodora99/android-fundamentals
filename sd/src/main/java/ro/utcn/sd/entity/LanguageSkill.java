package ro.utcn.sd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
@DiscriminatorValue("LanguageSkill")
public class LanguageSkill extends Skill {

    @Id
    private String id;

    @Column
    private String language;

    @Column
    private String level;

    public LanguageSkill(){
        super();
    }

}
