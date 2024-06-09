package ProjetGenieLogiciel.isepval.services;


import ProjetGenieLogiciel.isepval.models.Group;
import ProjetGenieLogiciel.isepval.models.Skill;
import ProjetGenieLogiciel.isepval.models.SkillEvaluated;
import ProjetGenieLogiciel.isepval.models.User;
import ProjetGenieLogiciel.isepval.models.enums.UserType;
import ProjetGenieLogiciel.isepval.repositories.GroupRepository;
import ProjetGenieLogiciel.isepval.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;


    public Group findById(long id) {
        return groupRepository.findById(id);
    }

    public List<Group> findByNameLike(String name){return groupRepository.findByNameLike(name);}

    public void saveNewGroup(Group group) {
        groupRepository.save(group);
    }


    public void deleteGroup(Group group){
        groupRepository.delete(group);
    }


    public List<Group> findAll() {
        return groupRepository.findAll();
    }
}
