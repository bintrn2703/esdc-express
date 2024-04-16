package vn.edu.tdtu.esdcexpress.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.tdtu.esdcexpress.model.Order;
import vn.edu.tdtu.esdcexpress.model.ReturnManagement;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.repository.ReturnManagementRepository;
import vn.edu.tdtu.esdcexpress.service.OrderService;
import vn.edu.tdtu.esdcexpress.service.ReturnManagementService;
import vn.edu.tdtu.esdcexpress.service.UserService;

import java.util.List;

@Controller
public class ReturnManagementController {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    ReturnManagementService returnManagementService;
    @GetMapping("/return-management")
    public String returnManagement(Model model, HttpSession session) {
        String accountName = (String) session.getAttribute("accountName");
        User user = userService.getUserByName(accountName);
        Iterable<Order> orders = orderService.getOrdersByUsername(user);
        if(user != null) {
            List<ReturnManagement> returnManagements = (List<ReturnManagement>) returnManagementService.getReturnManagementByOrder(orders);
            model.addAttribute("returnManagements", returnManagements);
            model.addAttribute("returnManagementsSize", returnManagements.size());
        }
        return "return-management";
    }

    @GetMapping("/return-management/{id}")
    public String returnDetail(@PathVariable(value="id") Long id, Model model) {
        ReturnManagement returnDetail = returnManagementService.findById(id);
        Order order = orderService.findById(returnDetail.getOrder().getId());
        model.addAttribute("returnDetail", returnDetail);
        model.addAttribute("order",order);
        return "return-detail";
    }

    @GetMapping("/img/order/{orderId}")
    public ResponseEntity<byte[]> getImage1(@PathVariable(value="orderId") Long orderId) {
        Order order = orderService.findById(orderId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(order.getParcel_image());

    }

    @GetMapping("/img/return/{returnId}")
    public ResponseEntity<byte[]> getImage2(@PathVariable(value="returnId") Long returnId) {
//        Order order = orderService.findById(orderId);
        ReturnManagement returnDetail = returnManagementService.findById(returnId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(returnDetail.getImage());

    }


    //test image
    /*@GetMapping("/test-image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable(value="id") Long id) {
        ReturnManagement image = returnManagementService.findById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image.getImage());
    }*/


}
