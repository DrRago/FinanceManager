package springapp.controller;

import aggregates.UserAggregate;
import authentication.Sha512Hash;
import domain_services.AuthenticationDomainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repositories.UserRepository;
import repository_bridges.UserRepositoryBridge;
import services.AuthenticationService;
import value_objects.PasswordVO;
import value_objects.UsernameVO;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public String registerRender(HttpSession session) {
        UserAggregate user = (UserAggregate) session.getAttribute("user");
        if (user != null) {
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerActor(Model model, HttpSession session, @RequestParam String username,
                                @RequestParam String password,
                                @RequestParam(name = "password-repeat") String passwordRepeat) {
        UserRepository repo = new UserRepositoryBridge();
        AuthenticationDomainService authService = new AuthenticationService(repo);

        UsernameVO usernameVO;
        try {
            usernameVO = new UsernameVO(username);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Username must be at least 3 characters long");
            return "register";
        }

        PasswordVO passwordVO = new PasswordVO(Sha512Hash.hash(password));
        PasswordVO passwordRepeatVO = new PasswordVO(Sha512Hash.hash(passwordRepeat));
        if (!passwordVO.equals(passwordRepeatVO)){
            model.addAttribute("error", "Passwords don't match");
            return "register";
        }

        UserAggregate user;
        if ((user = authService.register(usernameVO, passwordVO)) != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        }
        model.addAttribute("error", "Username already exists!");
        return "register";
    }
}
