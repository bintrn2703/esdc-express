package vn.edu.tdtu.esdcexpress.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReturnManagementController {
    @GetMapping("/return-management")
    public String returnManagement() {
        return "return-management";
    }
}
