package ProjetGenieLogiciel.isepval.repositories;

import ProjetGenieLogiciel.isepval.models.Skill;
import ProjetGenieLogiciel.isepval.models.SkillEvaluated;
import ProjetGenieLogiciel.isepval.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillEvaluatedRepository extends JpaRepository<SkillEvaluated, Long> {

    SkillEvaluated findById(long id);


    SkillEvaluated findBySkillAndStudent(Skill skill, User student);
}
