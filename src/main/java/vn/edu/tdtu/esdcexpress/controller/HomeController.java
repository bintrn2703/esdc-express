package vn.edu.tdtu.esdcexpress.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.model.UserRegistrationDto;
import vn.edu.tdtu.esdcexpress.service.UserService;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/custom-login")
    public String login() {
        System.out.println("Login page");
        return "custom-login";
    }

    @PostMapping("/custom-login")
    public String loginPost(HttpServletRequest request, Model model, @RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println("Username: " + username);
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            System.out.println("Authentication: " + auth);
            if (auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("Login successful 1");
                request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
                request.getSession().setAttribute("accountName", userService.findByUsername(username).getName());
                System.out.println("Login successful 2");
                return "redirect:/";
            }
        } catch (AuthenticationException e) {
            System.out.println("Login failed");

        } catch (Exception e) {
            // Log any other exceptions
            System.out.println("Unexpected error: " + e.getMessage());
        }
        System.out.println("Login failed 2");
        model.addAttribute("loginFailed", true);
        return "custom-login";
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
        return "redirect:/custom-login";
    }
}
