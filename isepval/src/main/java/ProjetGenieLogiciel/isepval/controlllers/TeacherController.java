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
public class TeacherController {

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

    @GetMapping("/teacher")
    public String teacherPannel(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.TEACHER) {
            return "401";
        }
        return "index";
    }
    @GetMapping("/teacher/studentManagement")
    public String teacherStudentManagement(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.TEACHER) {
            return "401";
        }
        return "teacher/studentManagement/studentManagement";
    }

    @GetMapping("/teacher/studentManagement/loadAllGroup")
    public String loadAllGroup(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.TEACHER) {
            return "401";
        }

        List<Group> allGroup = groupService.findAll();
        model.addAttribute("groupList", allGroup);
        model.addAttribute("newGroup", new Group());

        return "teacher/studentManagement/groupList";
    }

    @PostMapping("/teacher/studentManagement/findGroup")
    public String loadAllGroup(@Valid Group group, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.TEACHER) {
            return "401";
        }

        List<Group> allGroup = groupService.findByNameLike(group.getName());
        model.addAttribute("groupList", allGroup);
        model.addAttribute("newGroup", new Group());

        return "teacher/studentManagement/groupList";
    }


    @GetMapping("/teacher/studentManagement/loadAllStudent")
    public String loadAllStudent(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.TEACHER) {
            return "401";
        }

        List<User> allStudent = userService.findByUserType(UserType.STUDENT);
        List<Group> allGroup = groupService.findAll();
        model.addAttribute("allGroup", allGroup);
        model.addAttribute("studentList", allStudent);
        model.addAttribute("newStudent", new User());

        return "teacher/studentManagement/studentList";
    }

    @GetMapping("/teacher/studentManagement/findStudent")
    public String loadAllStudent(@Valid User student, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.TEACHER) {
            return "401";
        }

        List<User> allStudent = userService.findByUserTypeAndNameLike(UserType.STUDENT, student.getName());
        List<Group> allGroup = groupService.findAll();
        model.addAttribute("allGroup", allGroup);
        model.addAttribute("studentList", allStudent);
        model.addAttribute("newStudent", new User());

        return "teacher/studentManagement/studentList";
    }

    @GetMapping("/teacher/studentManagement/loadAllGroupStudent/{groupId}")
    public String loadAllStudent(@PathVariable long groupId, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.TEACHER) {
            return "401";
        }
        Group currentGroup = groupService.findById(groupId);
        List<User> allStudent = userService.findByGroup(currentGroup);
        List<Group> allGroup = groupService.findAll();
        model.addAttribute("allGroup", allGroup);
        model.addAttribute("studentList", allStudent);
        model.addAttribute("newStudent", new User());

        return "teacher/studentManagement/studentList";
    }

    @GetMapping("teacher/studentManagement/loadStudentSkill/{studentId}")
    public String teacherLoadStudentSkill(@PathVariable long studentId, Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() !=UserType.TEACHER) {
            return "401";
        }
        User currentStudent = userService.findById(studentId);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("currentStudent", currentStudent);
        return "teacher/studentManagement/studentSkill";
    }

    @PostMapping("teacher/studentManagement/loadStudentSkill")
    public String teacherLoadStudentSkillForSubCategory(@RequestParam long studentId,
                                                      @RequestParam long subCategoryId,
                                                      Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.TEACHER) {
            return "401";
        }
        User currentStudent = userService.findById(studentId);
        SubCategory currentSubCategory = subCategoryService.findById(subCategoryId);

        List<SkillEvaluated> allStudentSkill = userService.findAllStudentSkillFromSubCategory(currentStudent,currentSubCategory);
        model.addAttribute("allStudentSkill", allStudentSkill);
        return "teacher/studentManagement/studentSkillForSubCategory";
    }

    @PostMapping("teacher/studentManagement/updateStudentSkill")
    public String teacherLoadStudentSkillForSubCategory(@RequestParam Mark mark,
                                                      @RequestParam long studentSkillId,
                                                      Model model, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.TEACHER) {
            return "401";
        }
        SkillEvaluated updatedStudentSkill = skillEvaluatedService.findById(studentSkillId);
        updatedStudentSkill.setMark(mark);
        skillEvaluatedService.saveSkillEvaluated(updatedStudentSkill);
        return "teacher/studentManagement/studentSkillForSubCategory";
    }

    @GetMapping("/teacher/teacherManagement")
    public String teacherTeacherManagement(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.TEACHER) {
            return "401";
        }
        return "teacher/teacherManagement/teacherManagement";
    }

    @GetMapping("/teacher/teacherManagement/loadAllTeacher")
    public String loadAllTeacher(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        } else if (currentUser.getUserType() != UserType.TEACHER) {
            return "401";
        }

        List<User> allTeacher = userService.findByUserType(UserType.TEACHER);
        List<Group> allGroup = groupService.findAll();
        model.addAttribute("allGroup", allGroup);
        model.addAttribute("teacherList", allTeacher);
        model.addAttribute("newTeacher", new User());

        return "teacher/teacherManagement/teacherList";
    }

}
