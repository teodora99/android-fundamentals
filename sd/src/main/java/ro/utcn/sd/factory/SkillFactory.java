package ro.utcn.sd.factory;

import org.springframework.stereotype.Component;
import ro.utcn.sd.entity.*;

@Component
public class SkillFactory {

    public Skill createSkill(String type){
        if(type.equals("Organisational Skill")){
            return new OrganisationalSkill();
        }else if(type.equals("Digital Skill")){
            return new DigitalSkill();
        }else if(type.equals("Language Skill")){
            return new LanguageSkill();
        }else if(type.equals("Communication Skill")){
            return new CommunicationSkill();
        }
        return null;
    }
}
