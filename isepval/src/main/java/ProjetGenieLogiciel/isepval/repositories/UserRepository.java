package ProjetGenieLogiciel.isepval.repositories;

import ProjetGenieLogiciel.isepval.models.Group;
import ProjetGenieLogiciel.isepval.models.User;
import ProjetGenieLogiciel.isepval.models.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    User findById(long id);

    User findByLoginLike(String login);
    List<User> findByGroup(Group group);
    List<User> findByUserType(UserType userType);

    List<User> findByUserTypeAndNameLike(UserType userType, String name);

}
