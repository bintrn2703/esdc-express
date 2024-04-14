package vn.edu.tdtu.esdcexpress.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.tdtu.esdcexpress.model.Finance;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.service.FinanceService;
import vn.edu.tdtu.esdcexpress.service.UserService;

import java.util.List;

@Controller
public class FinanceController {
    @Autowired
    UserService userService;
    @Autowired
    FinanceService financeService;
    @GetMapping("/finance")
    public String finance(Model model, HttpSession session) {
        String accountName = (String) session.getAttribute("accountName");
        User user = userService.getUserByName(accountName);
        if(user != null) {
            List<Finance> finances = (List<Finance>) financeService.getFinancesByUsername(user);
//            List<Order> orders = (List<Order>) orderService.getOrdersByUsername(user);
            model.addAttribute("finances", finances);
//            model.addAttribute("orders", orders);
            model.addAttribute("financeSize", finances.size());
        }
        return "finance";
    }
}
