package ProjetGenieLogiciel.isepval.repositories;

import ProjetGenieLogiciel.isepval.models.Category;
import ProjetGenieLogiciel.isepval.models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    SubCategory findById(long id);

    List<SubCategory> findByNameLike(String name);

    List<SubCategory> findByCategoryId(long categoryId);

}
