package vn.edu.tdtu.esdcexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vn.edu.tdtu.esdcexpress.model.ReturnManagement;
import vn.edu.tdtu.esdcexpress.repository.ReturnManagementRepository;
import vn.edu.tdtu.esdcexpress.service.ReturnManagementService;

@Controller
public class ReturnManagementController {
    @Autowired
    ReturnManagementService returnManagementService;
    @GetMapping("/return-management")
    public String returnManagement() {
        return "return-management";
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
