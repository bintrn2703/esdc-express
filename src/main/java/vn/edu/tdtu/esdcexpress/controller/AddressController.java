package vn.edu.tdtu.esdcexpress.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vn.edu.tdtu.esdcexpress.model.Address;
import vn.edu.tdtu.esdcexpress.model.CreateAddressDto;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.service.AddressService;
import vn.edu.tdtu.esdcexpress.service.UserService;

import java.util.List;

@Controller
public class AddressController {
    @Autowired
    AddressService addressService;
    @Autowired
    UserService userService;
    @GetMapping("/address")
    public String address(Model model, HttpSession session) {
        String accountName = (String) session.getAttribute("accountName");
        User user = userService.getUserByName(accountName);
        if(user != null) {
            List<Address> addresses = (List<Address>) addressService.getAddressesByUsername(user);
            model.addAttribute("addresses", addresses);
            model.addAttribute("addressSize", addresses.size());
        }
        model.addAttribute("addressDto", new CreateAddressDto());
        return "address-list";
    }

    @PostMapping("/address")
    public String createNewAddress(@ModelAttribute("addressDto") CreateAddressDto addressDto, Model model, HttpSession session) {
        String name = (String) session.getAttribute("accountName");
        addressDto.setUser(userService.getUserByName(name));
        Address address = new Address();
        address.setContact_name(addressDto.getContact_name());
        address.setPhone_number(addressDto.getPhone_number());
        address.setAddress(addressDto.getAddress());
        address.setType(addressDto.getType());
        address.setPostal_code(addressDto.getPostal_code());
        address.setDelivery_instruction(addressDto.getDelivery_instruction());
        address.setUser(addressDto.getUser());
        addressService.save(address);
        return "redirect:/address";
    }

    @PostMapping("/edit-address")
    public String editAddress(HttpServletRequest request, Model model) {
        String contact_name = request.getParameter("contact-name");
        String phone_number = request.getParameter("phone-number");
        String address = request.getParameter("address");
        String type = request.getParameter("type");
        String postal_code = request.getParameter("postal-code");
        String delivery_instruction = request.getParameter("delivery-instruction");
        Long id = Long.parseLong(request.getParameter("id"));
        Address addressObj = addressService.findAddressById(id);
        addressObj.setContact_name(contact_name);
        addressObj.setPhone_number(phone_number);
        addressObj.setAddress(address);
        addressObj.setType(type);
        addressObj.setPostal_code(postal_code);
        addressObj.setDelivery_instruction(delivery_instruction);
        addressService.save(addressObj);
        return "redirect:/address";
    }
}
