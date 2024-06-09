package ProjetGenieLogiciel.isepval.services;


import ProjetGenieLogiciel.isepval.models.Skill;
import ProjetGenieLogiciel.isepval.models.SkillEvaluated;
import ProjetGenieLogiciel.isepval.models.User;
import ProjetGenieLogiciel.isepval.models.enums.UserType;
import ProjetGenieLogiciel.isepval.repositories.SkillRepository;
import ProjetGenieLogiciel.isepval.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SkillEvaluatedService skillEvaluatedService;

    public Skill findById(long id) {
        return skillRepository.findById(id);
    }

    public List<Skill> findByName(String name) {
        return skillRepository.findByNameLike(name);
    }

    public List<Skill> findBySubCategoryId(long subCategoryId){return skillRepository.findBySubCategoryId(subCategoryId);}
    public void saveNewSkill(Skill skill) {
        skillRepository.save(skill);
        List<User> allStudent = userRepository.findByUserType(UserType.STUDENT);
        for (User student: allStudent) {
            SkillEvaluated skillEvaluated = new SkillEvaluated();
            skillEvaluated.setStudent(student);
            skillEvaluated.setSkill(skill);
            skillEvaluatedService.saveSkillEvaluated(skillEvaluated);
        }
    }

    public void deleteSkill(Skill skill) {
        skillRepository.delete(skill);
    }

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }
}
