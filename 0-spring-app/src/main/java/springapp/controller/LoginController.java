package springapp.controller;

import aggregates.UserAggregate;
import authentication.Sha512Hash;
import org.springframework.beans.factory.annotation.Value;
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
public class LoginController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/login")
    public String loginRender(HttpSession session) {
        UserAggregate user = (UserAggregate) session.getAttribute("user");
        if (user != null) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginActor(Model model, HttpSession session, @RequestParam String username, @RequestParam String password) {
        UserRepository repo = new UserRepositoryBridge();
        AuthenticationService authService = new AuthenticationService(repo);
        UsernameVO usernameVO = new UsernameVO(username);
        PasswordVO passwordVO = new PasswordVO(Sha512Hash.hash(password));

        UserAggregate user;
        if ((user = authService.login(usernameVO, passwordVO)) != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        }
        model.addAttribute("error", "Wrong username or password");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        model.addAttribute("appName", session.getId());
        session.invalidate();
        return "home";
    }
}
