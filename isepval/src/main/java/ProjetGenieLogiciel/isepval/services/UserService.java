package ProjetGenieLogiciel.isepval.services;


import ProjetGenieLogiciel.isepval.models.User;
import ProjetGenieLogiciel.isepval.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    public User findById(long id) {
        return userRepository.findById(id);
    }

    public User findByLogin(String login) {
        return userRepository.findByLoginLike(login);
    }

    public boolean isLoginUnique(String login) {
        return !userRepository.existsByName(login);
    }

    public boolean isEmailUnique(String email) {
        return !userRepository.existsByEmail(email);
    }

    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User authenticate(String login, String password) {
        User user = userRepository.findByLoginLike(login);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
