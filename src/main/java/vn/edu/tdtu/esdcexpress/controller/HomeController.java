package vn.edu.tdtu.esdcexpress.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.tdtu.esdcexpress.model.Order;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.model.UserRegistrationDto;
import vn.edu.tdtu.esdcexpress.service.OrderService;
import vn.edu.tdtu.esdcexpress.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    OrderService orderService;

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/custom-login")
    public String login() {
        System.out.println("Login page");
        return "custom-login";
    }

    @PostMapping("/custom-login")
    public String loginPost(HttpServletRequest request, Model model, @RequestParam("username") String username, @RequestParam("password") String password){
        System.out.println("Username: " + username);
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            System.out.println("Authentication: " + auth);
            if (auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("Login successful 1");
                request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
                request.getSession().setAttribute("accountName", userService.findByUsername(username).getName());
                System.out.println("Login successful 2");
                return "redirect:/";
            }
        } catch (AuthenticationException e) {
            System.out.println("Login failed");

        } catch (Exception e) {
            // Log any other exceptions
            System.out.println("Unexpected error: " + e.getMessage());
        }
        System.out.println("Login failed 2");
        model.addAttribute("loginFailed", true);
        return "custom-login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserRegistrationDto userDto) {
        if(userService.findByUsername(userDto.getUsername()) != null) {
            return "redirect:/register";
        }
        String name = userDto.getFirstName() + " " + userDto.getLastName();
        User newUser = new User();
        newUser.setName(name);
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setBank_name(userDto.getBank_name());
        newUser.setBank_account_holder(userDto.getBank_account_holder());
        newUser.setBank_account_number(userDto.getBank_account_number());
        newUser.setEmail(userDto.getEmail());
        userService.save(newUser);
        return "redirect:/custom-login";
    }

    // Trong controller của bạn
    @GetMapping("/order/export-csv")
    public void exportToCSV(HttpServletResponse response, HttpSession session) throws IOException {
        String accountName = (String) session.getAttribute("accountName");
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=orders.csv");

        User user = userService.getUserByName(accountName);
        List<Order> orders = (List<Order>) orderService.getOrdersByUsername(user);

        // Tạo đối tượng PrintWriter từ response
        PrintWriter writer = response.getWriter();

        // Viết tiêu đề cho các cột trong file CSV
        writer.write("Order ID, Status, Pickup Name, Pickup Phone, Pickup Address, Deliver Name, Deliver Phone, Deliver Address, Parcel Name, Item Quantity, Delivery Instruction, Shipping Fee Payment, Allow Mutual Check, COD, Parcel Value, Collect Type, Shipping Service, Shipping Fee, Create At, Pickup At, Delivered At\n");

        // Duyệt qua danh sách đơn hàng và viết thông tin của mỗi đơn hàng vào file CSV
        for (Order order : orders) {
            writer.write(order.getId() + "," + order.getStatus() + "," + order.getPickup_name() + "," + order.getPickup_phone() + "," + order.getPickup_address() + "," + order.getDeliver_name() + "," + order.getDeliver_phone() + "," + order.getDeliver_address() + "," + order.getParcel_name() + "," + order.getItem_quantity() + "," + order.getDelivery_instruction() + "," + order.getShipping_fee_payment() + "," + order.getAllow_mutual_check() + "," + order.getCod() + "," + order.getParcel_value() + "," + order.getCollect_type() + "," + order.getShipping_service() + "," + order.getShipping_fee() + "," + order.getCreate_at() + "," + order.getPickup_at() + "," + order.getDelivered_at() + "\n");
        }

        writer.flush();
        writer.close();
    }

    @GetMapping("/homepage")
    public String homepage() {
        return "homepage";
    }
}
