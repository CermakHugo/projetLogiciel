package ProjetGenieLogiciel.isepval.repositories;

import ProjetGenieLogiciel.isepval.models.Category;
import ProjetGenieLogiciel.isepval.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    Skill findById(long id);

    List<Skill> findByNameLike(String name);

    List<Skill> findBySubCategoryId(long subCategoryId);
}
