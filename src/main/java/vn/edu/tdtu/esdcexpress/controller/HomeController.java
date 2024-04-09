package vn.edu.tdtu.esdcexpress.controller;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
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
    public RedirectView loginPost(HttpServletRequest request, Model model) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(userService.loginWithUsernameAndPasswordInDB(username, password)) {
            return new RedirectView("/");
        }
        return new RedirectView("/login");
    }
}
