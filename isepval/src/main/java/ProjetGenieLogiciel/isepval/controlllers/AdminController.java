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
public class AdminController {

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
        skillService.saveNewSkill(newSkill);
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

    @GetMapping("/admin/studentManagement")
    public String adminStudentManagement(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        return "admin/studentManagement/studentManagement";
    }

    @GetMapping("/admin/studentManagement/loadAllGroup")
    public String loadAllGroup(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }

        List<Group> allGroup = groupService.findAll();
        model.addAttribute("groupList", allGroup);
        model.addAttribute("newGroup", new Group());

        return "admin/studentManagement/groupList";
    }

    @PostMapping("/admin/studentManagement/findGroup")
    public String loadAllGroup(@Valid Group group, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }

        List<Group> allGroup = groupService.findByNameLike(group.getName());
        model.addAttribute("groupList", allGroup);
        model.addAttribute("newGroup", new Group());

        return "admin/studentManagement/groupList";
    }

    @PostMapping("/admin/studentManagement/createGroup")
    public String adminCreateGroup(@Valid Group newGroup, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        groupService.saveNewGroup(newGroup);
        return loadAllGroup(session, model);
    }

    @GetMapping("/admin/studentManagement/deleteGroup/{groupId}")
    public String adminDeleteGroup(@PathVariable long groupId, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        Group groupToDelete = groupService.findById(groupId);
        groupService.deleteGroup(groupToDelete);
        return loadAllStudent(session,model);
    }

    @GetMapping("/admin/studentManagement/loadAllStudent")
    public String loadAllStudent(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }

        List<User> allStudent = userService.findByUserType(UserType.STUDENT);
        List<Group> allGroup = groupService.findAll();
        model.addAttribute("allGroup", allGroup);
        model.addAttribute("studentList", allStudent);
        model.addAttribute("newStudent", new User());

        return "admin/studentManagement/studentList";
    }

    @GetMapping("/admin/studentManagement/findStudent")
    public String loadAllStudent(@Valid User student, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }

        List<User> allStudent = userService.findByUserTypeAndNameLike(UserType.STUDENT, student.getName());
        List<Group> allGroup = groupService.findAll();
        model.addAttribute("allGroup", allGroup);
        model.addAttribute("studentList", allStudent);
        model.addAttribute("newStudent", new User());

        return "admin/studentManagement/studentList";
    }

    @GetMapping("/admin/studentManagement/loadAllGroupStudent/{groupId}")
    public String loadAllStudent(@PathVariable long groupId, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        Group currentGroup = groupService.findById(groupId);
        List<User> allStudent = userService.findByGroup(currentGroup);
        List<Group> allGroup = groupService.findAll();
        model.addAttribute("allGroup", allGroup);
        model.addAttribute("studentList", allStudent);
        model.addAttribute("newStudent", new User());

        return "admin/studentManagement/studentList";
    }

    @PostMapping("/admin/studentManagement/createStudent")
    public String adminCreateStudent(@Valid User newStudent, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        userService.saveNewStudent(newStudent);
        return loadAllStudent(session, model);
    }


    @GetMapping("/admin/studentManagement/deleteStudent/{studentId}")
    public String adminDeleteStudent(@PathVariable long studentId, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        User studentToDelete = userService.findById(studentId);
        userService.deleteUser(studentToDelete);
        return loadAllStudent(session,model);
    }

    @GetMapping("admin/studentManagement/loadStudentSkill/{studentId}")
    public String adminLoadStudentSkill(@PathVariable long studentId, Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        User currentStudent = userService.findById(studentId);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("currentStudent", currentStudent);
        return "admin/studentManagement/studentSkill";
    }

    @PostMapping("admin/studentManagement/loadStudentSkill")
    public String adminLoadStudentSkillForSubCategory(@RequestParam long studentId,
                                                      @RequestParam long subCategoryId,
                                                      Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        User currentStudent = userService.findById(studentId);
        SubCategory currentSubCategory = subCategoryService.findById(subCategoryId);

        List<SkillEvaluated> allStudentSkill = userService.findAllStudentSkillFromSubCategory(currentStudent,currentSubCategory);
        model.addAttribute("allStudentSkill", allStudentSkill);
        return "admin/studentManagement/studentSkillForSubCategory";
    }

    @PostMapping("admin/studentManagement/updateStudentSkill")
    public String adminLoadStudentSkillForSubCategory(@RequestParam Mark mark,
                                                      @RequestParam long studentSkillId,
                                                      Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        SkillEvaluated updatedStudentSkill = skillEvaluatedService.findById(studentSkillId);
        updatedStudentSkill.setMark(mark);
        skillEvaluatedService.saveSkillEvaluated(updatedStudentSkill);
        return "admin/studentManagement/studentSkillForSubCategory";
    }

    @GetMapping("/admin/teacherManagement")
    public String adminTeacherManagement(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        return "admin/teacherManagement/teacherManagement";
    }

    @GetMapping("/admin/teacherManagement/loadAllTeacher")
    public String loadAllTeacher(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }

        List<User> allTeacher = userService.findByUserType(UserType.TEACHER);
        List<Group> allGroup = groupService.findAll();
        model.addAttribute("allGroup", allGroup);
        model.addAttribute("teacherList", allTeacher);
        model.addAttribute("newTeacher", new User());

        return "admin/teacherManagement/teacherList";
    }

    @PostMapping("/admin/teacherManagement/createTeacher")
    public String adminCreateTeacher(@Valid User newTeacher, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        newTeacher.setUserType(UserType.TEACHER);
        userService.saveNewUser(newTeacher);
        return loadAllTeacher(session, model);
    }


    @GetMapping("/admin/teacherManagement/deleteTeacher/{teacherId}")
    public String adminDeleteTeacher(@PathVariable long teacherId, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.ADMIN) {
            return "401";
        }
        User teacherToDelete = userService.findById(teacherId);
        userService.deleteUser(teacherToDelete);
        return loadAllTeacher(session,model);
    }

}
