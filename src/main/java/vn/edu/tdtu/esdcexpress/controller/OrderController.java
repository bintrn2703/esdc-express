package vn.edu.tdtu.esdcexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.tdtu.esdcexpress.model.Order;
import vn.edu.tdtu.esdcexpress.service.OrderService;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/order")
    public String order(Model model) {
        List<Order> orders = (List<Order>) orderService.getAllOrders();
        model.addAttribute("orders", orders);
        model.addAttribute("orderSize", orders.size());
        return "order";
    }
}
