package vn.edu.tdtu.esdcexpress.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.model.UserRegistrationDto;
import vn.edu.tdtu.esdcexpress.service.UserService;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(HttpServletRequest request, Model model) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(userService.loginWithUsernameAndPassword(username, password)) {
            return "redirect:/";
        }
        model.addAttribute("loginFailed", true);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserRegistrationDto userDto) {
        if(userService.findByUsername(userDto.getUsername()) != null) {
            return "redirect:/register";
        }
        String name = userDto.getFirstName() + " " + userDto.getLastName();
        User newUser = new User();
        newUser.setName(name);
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setBank_name(userDto.getBank_name());
        newUser.setBank_account_holder(userDto.getBank_account_holder());
        newUser.setBank_account_number(userDto.getBank_account_number());
        newUser.setEmail(userDto.getEmail());
        userService.save(newUser);
        return "redirect:/login";
    }
}
