package ProjetGenieLogiciel.isepval.repositories;

import ProjetGenieLogiciel.isepval.models.Category;
import ProjetGenieLogiciel.isepval.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    User findById(long id);

    User findByLoginLike(String login);

}
