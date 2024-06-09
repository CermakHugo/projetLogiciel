package ProjetGenieLogiciel.isepval.services;


import ProjetGenieLogiciel.isepval.models.Category;
import ProjetGenieLogiciel.isepval.models.SubCategory;
import ProjetGenieLogiciel.isepval.repositories.CategoryRepository;
import ProjetGenieLogiciel.isepval.repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public SubCategory findById(long id) {
        return subCategoryRepository.findById(id);
    }

    public List<SubCategory> findByName(String name) {
        return subCategoryRepository.findByNameLike(name);
    }

    public List<SubCategory> findByCategoryId(long categoryId){return subCategoryRepository.findByCategoryId(categoryId);}
    public void saveNewCategory(SubCategory subCategory) {
        subCategoryRepository.save(subCategory);
    }

    public void deleteCategory(SubCategory subCategory) {
        subCategoryRepository.delete(subCategory);
    }

}
