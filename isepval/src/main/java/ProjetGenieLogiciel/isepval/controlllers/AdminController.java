package ProjetGenieLogiciel.isepval.controlllers;

import ProjetGenieLogiciel.isepval.models.Category;
import ProjetGenieLogiciel.isepval.models.Skill;
import ProjetGenieLogiciel.isepval.models.SubCategory;
import ProjetGenieLogiciel.isepval.models.User;
import ProjetGenieLogiciel.isepval.models.enums.UserType;
import ProjetGenieLogiciel.isepval.services.CategoryService;
import ProjetGenieLogiciel.isepval.services.SkillService;
import ProjetGenieLogiciel.isepval.services.SubCategoryService;
import ProjetGenieLogiciel.isepval.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private SkillService skillService;

    @GetMapping("/admin")
    public String adminPannel(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        return "admin/adminHome";
    }

    @GetMapping("/admin/skillManagement")
    public String adminSkillManagement(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        return "admin/skillManagement/skillManagement";
    }

    @GetMapping("/admin/skillManagement/loadAllCategory")
    public String adminLoadAllCategory(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        List<Category> allCategory = categoryService.findAll();
        model.addAttribute("categoryList", allCategory);
        model.addAttribute("newCategory", new Category());
        return "admin/skillManagement/categoryList";
    }

    @GetMapping("/admin/skillManagement/createCategory")
    public String adminCreateCategory(@Valid Category category, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        categoryService.saveNewCategory(category);
        List<Category> allCategory = categoryService.findAll();
        model.addAttribute("categoryList", allCategory);
        model.addAttribute("newCategory", new Category());
        return "admin/skillManagement/categoryList";
    }

    @GetMapping("/admin/skillManagement/deleteCategory/{categoryId}")
    public String adminDeleteCategory(@PathVariable long categoryId, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        Category categoryToDelete = categoryService.findById(categoryId);
        categoryService.deleteCategory(categoryToDelete);
        List<Category> allCategory = categoryService.findAll();
        model.addAttribute("categoryList", allCategory);
        model.addAttribute("newCategory", new Category());
        return "admin/skillManagement/categoryList";
    }

    @GetMapping("/admin/skillManagement/loadAllSubCategory/{categoryId}")
    public String adminLoadAllSubCategory(@PathVariable long categoryId, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        Category currentCategory = categoryService.findById(categoryId);
        List<SubCategory> allSubCategory = subCategoryService.findByCategoryId(categoryId);
        model.addAttribute("currentCategory", currentCategory);
        model.addAttribute("subCategoryList", allSubCategory);
        model.addAttribute("newSubCategory", new SubCategory());
        return "admin/skillManagement/subCategoryList";
    }

    @GetMapping("/admin/skillManagement/createSubCategory")
    public String adminCreateSubCategory(@Valid SubCategory newSubCategory, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        long categoryId = newSubCategory.getCategory().getId();
        subCategoryService.saveNewCategory(newSubCategory);
        Category currentCategory = categoryService.findById(categoryId);
        List<SubCategory> allSubCategory = subCategoryService.findByCategoryId(categoryId);
        model.addAttribute("currentCategory", currentCategory);
        model.addAttribute("subCategoryList", allSubCategory);
        model.addAttribute("newSubCategory", new SubCategory());
        return "admin/skillManagement/subCategoryList";
    }

    @GetMapping("/admin/skillManagement/deleteSubCategory/{subCategoryId}")
    public String adminDeleteSubCategory(@PathVariable long subCategoryId, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        SubCategory subCategoryToDelete = subCategoryService.findById(subCategoryId);
        subCategoryService.deleteCategory(subCategoryToDelete);

        long categoryId = subCategoryToDelete.getCategory().getId();
        Category currentCategory = categoryService.findById(categoryId);
        List<SubCategory> allSubCategory = subCategoryService.findByCategoryId(categoryId);
        model.addAttribute("currentCategory", currentCategory);
        model.addAttribute("subCategoryList", allSubCategory);
        model.addAttribute("newSubCategory", new SubCategory());
        return "admin/skillManagement/subCategoryList";
    }

    @GetMapping("/admin/skillManagement/loadAllSkill/{subCategoryId}")
    public String adminLoadAllSkill(@PathVariable long subCategoryId, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        SubCategory currentSubCategory = subCategoryService.findById(subCategoryId);
        List<Skill> allSkill = skillService.findBySubCategoryId(subCategoryId);
        model.addAttribute("currentSubCategory", currentSubCategory);
        model.addAttribute("skillList", allSkill);
        model.addAttribute("newSkill", new Skill());
        return "admin/skillManagement/skillList";
    }

    @GetMapping("/admin/skillManagement/createSkill")
    public String adminCreateSkill(@Valid Skill newSkill, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        long subCategoryId = newSkill.getSubCategory().getId();
        skillService.saveNewCategory(newSkill);
        SubCategory currentSubCategory = subCategoryService.findById(subCategoryId);
        List<Skill> allSkill = skillService.findBySubCategoryId(subCategoryId);
        model.addAttribute("currentSubCategory", currentSubCategory);
        model.addAttribute("skillList", allSkill);
        model.addAttribute("newSkill", new Skill());
        return "admin/skillManagement/skillList";
    }

    @GetMapping("/admin/skillManagement/deleteSkill/{skillId}")
    public String adminDeleteSkill(@PathVariable long skillId, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        Skill skillToDelete = skillService.findById(skillId);
        skillService.deleteSkill(skillToDelete);

        long subCategoryId = skillToDelete.getSubCategory().getId();
        SubCategory currentSubCategory = subCategoryService.findById(subCategoryId);
        List<Skill> allSkill = skillService.findBySubCategoryId(subCategoryId);
        model.addAttribute("currentSubCategory", currentSubCategory);
        model.addAttribute("skillList", allSkill);
        model.addAttribute("newSkill", new Skill());
        return "admin/skillManagement/skillList";
    }

}
