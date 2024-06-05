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
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(User user, HttpSession session, Model model) {
        User authenticatedUser = userService.authenticate(user.getUsername(), user.getPassword());

        if (authenticatedUser != null) {
            session.setAttribute("user", authenticatedUser);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@Valid User user, Model model) {
        boolean isUsernameUnique = userService.isUsernameUnique(user.getUsername());
        boolean isEmailUnique = userService.isEmailUnique(user.getEmail());

        if (!isUsernameUnique) {
            model.addAttribute("usernameError", "Username is already taken!");
        }
        if (!isEmailUnique) {
            model.addAttribute("emailError", "Email is already taken!");
        }
        if (isUsernameUnique && isEmailUnique) {
            userService.saveNewUser(user);
            return "redirect:/login";
        } else {
            return "signup";
        }
    }
}
