package ProjetGenieLogiciel.isepval.controlllers;

import ProjetGenieLogiciel.isepval.models.*;
import ProjetGenieLogiciel.isepval.models.enums.Mark;
import ProjetGenieLogiciel.isepval.models.enums.UserType;
import ProjetGenieLogiciel.isepval.services.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillEvaluatedService skillEvaluatedService;
    @GetMapping("/student")
    public String studentPannel(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.STUDENT) {
            return "401";
        }
        return "index";
    }
    
    @GetMapping("student/loadStudentSkill")
    public String studentLoadStudentSkill(Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() !=UserType.STUDENT) {
            return "401";
        }
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("currentStudent", currentUser);
        return "student/studentSkill";
    }

    @PostMapping("student/loadStudentSkill")
    public String studentLoadStudentSkillForSubCategory(
                                                        @RequestParam long subCategoryId,
                                                        Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.STUDENT) {
            return "401";
        }
        SubCategory currentSubCategory = subCategoryService.findById(subCategoryId);

        List<SkillEvaluated> allStudentSkill = userService.findAllStudentSkillFromSubCategory(currentUser,currentSubCategory);
        model.addAttribute("allStudentSkill", allStudentSkill);
        return "student/studentSkillForSubCategory";
    }
}
