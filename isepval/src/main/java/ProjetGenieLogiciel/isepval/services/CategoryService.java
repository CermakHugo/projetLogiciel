package ProjetGenieLogiciel.isepval.services;


import ProjetGenieLogiciel.isepval.models.Category;
import ProjetGenieLogiciel.isepval.models.User;
import ProjetGenieLogiciel.isepval.repositories.CategoryRepository;
import ProjetGenieLogiciel.isepval.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> findAll(){return  categoryRepository.findAll();}

    public Category findById(long id) {
        return categoryRepository.findById(id);
    }

    public List<Category> findByName(String name) {
        return categoryRepository.findByNameLike(name);
    }


    public void saveNewCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

}
