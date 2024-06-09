package ProjetGenieLogiciel.isepval.repositories;

import ProjetGenieLogiciel.isepval.models.Group;
import ProjetGenieLogiciel.isepval.models.User;
import ProjetGenieLogiciel.isepval.models.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findById(long id);

    List<Group> findByNameLike(String name);
    List<Group> findAll();


}
