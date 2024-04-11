package vn.edu.tdtu.esdcexpress.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.tdtu.esdcexpress.model.Order;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.service.OrderService;
import vn.edu.tdtu.esdcexpress.service.UserService;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @GetMapping("/order")
    public String order(Model model, HttpSession session) {
        String accountName = (String) session.getAttribute("accountName");
        User user = userService.getUserByName(accountName);
        if(user != null) {
            List<Order> orders = (List<Order>) orderService.getOrdersByUsername(user);
            model.addAttribute("orders", orders);
            model.addAttribute("orderSize", orders.size());
        }
        return "order";
    }

    @GetMapping("/order/{id}")
    public String orderDetail(@PathVariable(value="id") Long id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("orderDetail", order);
        return "order-id";
    }
}
