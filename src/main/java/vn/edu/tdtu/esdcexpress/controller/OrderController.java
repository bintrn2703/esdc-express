package vn.edu.tdtu.esdcexpress.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vn.edu.tdtu.esdcexpress.model.CreateOrderDto;
import vn.edu.tdtu.esdcexpress.model.Order;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.service.OrderService;
import vn.edu.tdtu.esdcexpress.service.UserService;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

    @GetMapping("/create-order")
    public String createNewOrder(Model model) {
        model.addAttribute("order", new CreateOrderDto());
        return "create-order";
    }

    @PostMapping("/create-order")
    public String createNewOrder(@ModelAttribute("order") CreateOrderDto orderDto, Model model, HttpSession session) {
        String name = (String) session.getAttribute("accountName");
        orderDto.setUser(userService.getUserByName(name));
        Order order = new Order();
        order.setDeliver_name(orderDto.getDeliver_name());
        order.setDeliver_address(orderDto.getDeliver_address());
        order.setDeliver_phone(orderDto.getDeliver_phone());
        order.setPickup_name(orderDto.getPickup_name());
        order.setPickup_address(orderDto.getPickup_address());
        order.setPickup_phone(orderDto.getPickup_phone());
        order.setParcel_name(orderDto.getParcel_name());
        order.setParcel_weight(orderDto.getParcel_weight());
        order.setParcel_dimension(orderDto.getParcel_dimension());
        order.setParcel_value(orderDto.getParcel_value());
        order.setItem_quantity(orderDto.getItem_quantity());
        order.setAllow_mutual_check(orderDto.getAllow_mutual_check());
        order.setCod(orderDto.getCod());
        order.setCollect_type(orderDto.getCollect_type());
        order.setShipping_service(orderDto.getShipping_service());
        order.setShipping_fee_payment(orderDto.getShipping_fee_payment());
        order.setShipping_fee(orderDto.getShipping_fee());
        order.setPickup_at(orderDto.getPickup_at());
        order.setCreate_at(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")).toString());
        order.setDelivered_at(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")).toString());
        order.setStatus("In transit");
        order.setUser(orderDto.getUser());
        orderService.save(order);
        return "redirect:/order";
    }

    @GetMapping("/edit-order/{id}")
    public String editOrder(@PathVariable(value="id") Long id, Model model) {
        Order orderDetail = orderService.findById(id);
        model.addAttribute("orderDetail", orderDetail);
        return "edit-order";
    }
}
