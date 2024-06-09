package ProjetGenieLogiciel.isepval.controlllers;

import ProjetGenieLogiciel.isepval.models.User;
import ProjetGenieLogiciel.isepval.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @Autowired
    private UserService userService;


}
