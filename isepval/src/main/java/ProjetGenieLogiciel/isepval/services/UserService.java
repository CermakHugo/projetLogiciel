package ProjetGenieLogiciel.isepval.services;


import ProjetGenieLogiciel.isepval.models.*;
import ProjetGenieLogiciel.isepval.models.enums.UserType;
import ProjetGenieLogiciel.isepval.repositories.GroupRepository;
import ProjetGenieLogiciel.isepval.repositories.SkillEvaluatedRepository;
import ProjetGenieLogiciel.isepval.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private SkillService skillService;
    @Autowired
    private SkillEvaluatedRepository skillEvaluatedRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User findById(long id) {
        return userRepository.findById(id);
    }

    public List<User> findByUserType(UserType userType){return userRepository.findByUserType(userType);}

    public List<User> findByGroup(Group group){return userRepository.findByGroup(group);}

    public User findByLogin(String login) {
        return userRepository.findByLoginLike(login);
    }

    public boolean isLoginUnique(String login) {
        return !userRepository.existsByName(login);
    }

    public boolean isEmailUnique(String email) {
        return !userRepository.existsByEmail(email);
    }

    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getGroup() == null){
            user.setGroup(groupRepository.findById(1));
        }
        userRepository.save(user);
    }

    public void saveNewStudent(User student){
        List<Skill> allSkill = skillService.findAll();
        saveNewUser(student);
        for (Skill skill: allSkill ) {
            SkillEvaluated skillEvaluated = new SkillEvaluated();
            skillEvaluated.setStudent(student);
            skillEvaluated.setSkill(skill);
            skillEvaluatedRepository.save(skillEvaluated);
        }
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public User authenticate(String login, String password) {
        User user = userRepository.findByLoginLike(login);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<SkillEvaluated> findAllStudentSkillFromSubCategory(User student, SubCategory subCategory){
        List<Skill> allSkillFromSubCategory = subCategory.getSkills();
        List<SkillEvaluated> allStudentSkillFromSubCategory = new ArrayList<>();
        for (Skill skill: allSkillFromSubCategory) {
            SkillEvaluated studentSkill = skillEvaluatedRepository.findBySkillAndStudent(skill,student);
            allStudentSkillFromSubCategory.add(studentSkill);
        }
        return allStudentSkillFromSubCategory;
    }
}
