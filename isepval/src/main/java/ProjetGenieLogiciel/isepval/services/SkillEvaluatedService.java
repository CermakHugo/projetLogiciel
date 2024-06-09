package ProjetGenieLogiciel.isepval.services;


import ProjetGenieLogiciel.isepval.models.Skill;
import ProjetGenieLogiciel.isepval.models.SkillEvaluated;
import ProjetGenieLogiciel.isepval.models.User;
import ProjetGenieLogiciel.isepval.repositories.SkillEvaluatedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillEvaluatedService {

    @Autowired
    private SkillEvaluatedRepository skillEvaluatedRepository;

    public SkillEvaluated findById(long id) {
        return skillEvaluatedRepository.findById(id);
    }

    public SkillEvaluated findBySkillAndStudent(Skill skill, User student){
        return skillEvaluatedRepository.findBySkillAndStudent(skill, student);
    }
    public void saveSkillEvaluated(SkillEvaluated skillEvaluated) {
        skillEvaluatedRepository.save(skillEvaluated);
    }

}
