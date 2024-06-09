package ProjetGenieLogiciel.isepval.repositories;

import ProjetGenieLogiciel.isepval.models.Category;
import ProjetGenieLogiciel.isepval.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findById(long id);

    List<Category> findByNameLike(String name);

}
