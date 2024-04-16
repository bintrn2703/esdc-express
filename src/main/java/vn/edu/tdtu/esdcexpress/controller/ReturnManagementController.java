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
    UserService userService;
    OrderService orderService;
    @Autowired
    ReturnManagementService returnManagementService;
    @GetMapping("/return-management")
    public String returnManagement(Model model, HttpSession session) {
        String accountName = (String) session.getAttribute("accountName");
        User user = userService.getUserByName(accountName);
        Order order = (Order) orderService.getOrdersByUsername(user);
        if(user != null) {
            List<ReturnManagement> returnManagements = (List<ReturnManagement>) returnManagementService.getReturnManagementByOrder(order);
            model.addAttribute("returnManagements", returnManagements);
            model.addAttribute("returnManagementsSize", returnManagements.size());
        }
        return "return-management";
    }

    @GetMapping("/return-management/{id}")
    public String returnDetail(@PathVariable(value="id") Long id, Model model) {
        ReturnManagement returnDetail = returnManagementService.findById(id);
        model.addAttribute("returnDetail", returnDetail);
        return "return-detail";
    }

    //test image
    @GetMapping("/test-image")
    public ResponseEntity<byte[]> getImage() {
        ReturnManagement image = returnManagementService.findById(2L);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image.getImage());
    }


}
