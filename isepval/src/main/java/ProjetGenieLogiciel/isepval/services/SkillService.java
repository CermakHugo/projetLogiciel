package ProjetGenieLogiciel.isepval.services;


import ProjetGenieLogiciel.isepval.models.Skill;
import ProjetGenieLogiciel.isepval.models.SubCategory;
import ProjetGenieLogiciel.isepval.repositories.SkillRepository;
import ProjetGenieLogiciel.isepval.repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;


    public Skill findById(long id) {
        return skillRepository.findById(id);
    }

    public List<Skill> findByName(String name) {
        return skillRepository.findByNameLike(name);
    }

    public List<Skill> findBySubCategoryId(long subCategoryId){return skillRepository.findBySubCategoryId(subCategoryId);}
    public void saveNewCategory(Skill skill) {
        skillRepository.save(skill);
    }

    public void deleteSkill(Skill skill) {
        skillRepository.delete(skill);
    }

}
