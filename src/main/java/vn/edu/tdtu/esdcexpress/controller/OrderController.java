package vn.edu.tdtu.esdcexpress.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.tdtu.esdcexpress.model.*;
import vn.edu.tdtu.esdcexpress.service.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    FinanceService financeService;
    @Autowired
    AddressService addressService;
    @Autowired
    ReturnManagementService returnManagementService;
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
    public String createNewOrder(Model model, HttpSession session) {
        String accountName = (String) session.getAttribute("accountName");
        User user = userService.getUserByName(accountName);

        List<Address> pickupType = (List<Address>) addressService.getAddressTypes(user, "pickup");
        List<Address> deliveryType = (List<Address>) addressService.getAddressTypes(user,"delivery");
        model.addAttribute("pickupType", pickupType);
        model.addAttribute("deliveryType", deliveryType);
        model.addAttribute("order", new CreateOrderDto());
        return "create-order";
    }

    @PostMapping("/create-order")
    public String createNewOrder(@ModelAttribute("order") CreateOrderDto orderDto, Model model, HttpSession session, @RequestParam("file-input") MultipartFile file) throws IOException {
        String name = (String) session.getAttribute("accountName");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedNow = now.format(formatter);
        orderDto.setUser(userService.getUserByName(name));
        if (file.isEmpty()) {
            model.addAttribute("error", "Please select a file to upload");
            return "create-order";
        }
        byte[] imageBytes = file.getBytes();

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
        order.setPickup_at(formattedNow);
        order.setCreate_at(formattedNow);
        order.setDelivered_at(formattedNow);
        order.setStatus("In transit");
        order.setUser(orderDto.getUser());
        order.setParcel_image(imageBytes);
        orderService.save(order);

        Finance finance = new Finance();
        finance.setOrder(order);
        finance.setUser(orderDto.getUser());
        finance.setCreate_at(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")).toString());
        finance.setWeight(orderDto.getParcel_weight());
        finance.setDimension(orderDto.getParcel_dimension());
        finance.setShipping_fee(orderDto.getShipping_fee());
        finance.setParcel_value(orderDto.getParcel_value());
        finance.setCod(orderDto.getCod());
        financeService.save(finance);

        return "redirect:/order";
    }

    @PostMapping("/order/cancel")
    public String cancelOrder(@RequestBody List<Long> ids) {
        for(Long id : ids) {
            Order order = orderService.findById(id);
            order.setStatus("Cancelled");
            orderService.save(order);
        }
        return "redirect:/order";
    }

    @GetMapping("/edit-order/{id}")
    public String editOrder(@PathVariable(value="id") Long id, Model model, HttpSession session) {
        String accountName = (String) session.getAttribute("accountName");
        User user = userService.getUserByName(accountName);

        List<Address> pickupType = (List<Address>) addressService.getAddressTypes(user, "pickup");
        List<Address> deliveryType = (List<Address>) addressService.getAddressTypes(user,"delivery");
        model.addAttribute("pickupType", pickupType);
        model.addAttribute("deliveryType", deliveryType);
        Order orderDetail = orderService.findById(id);
        model.addAttribute("orderDetailNew", orderDetail);
        System.out.println(orderDetail.getDeliver_name());
        System.out.println(orderDetail.getDeliver_address());
        System.out.println(orderDetail.getPickup_address());
        model.addAttribute("updateOrder", new CreateOrderDto());
        return "edit-order";
    }

    @PostMapping("/edit-order/{id}")
    public String editOrder(@PathVariable(value="id") Long id, HttpServletRequest request, Model model) {
        String pickup_name = request.getParameter("pickup-name");
        String pickup_address = request.getParameter("pickup-address");
        String pickup_phone = request.getParameter("pickup-phone");
        String deliver_name = request.getParameter("deliver-name");
        String deliver_address = request.getParameter("deliver-address");
        String deliver_phone = request.getParameter("deliver-phone");
        String parcel_name = request.getParameter("parcel-name");
        float parcel_weight = Float.parseFloat(request.getParameter("parcel-weight"));
        String parcel_dimension = request.getParameter("parcel-dimension");
        float parcel_value = Float.parseFloat(request.getParameter("parcel-value"));
        int item_quantity = Integer.parseInt(request.getParameter("item-quantity"));
        String allow_mutual_check = request.getParameter("allow-mutual-check");
        String cod = request.getParameter("COD");
        String collect_type = request.getParameter("collect-type");
        String shipping_service = request.getParameter("shipping-service");
        String shipping_fee_payment = request.getParameter("shipping-fee-payment");
        Order order = orderService.findById(id);
        order.setPickup_name(pickup_name);
        order.setPickup_address(pickup_address);
        order.setPickup_phone(pickup_phone);
        order.setDeliver_name(deliver_name);
        order.setDeliver_address(deliver_address);
        order.setDeliver_phone(deliver_phone);
        order.setParcel_name(parcel_name);
        order.setParcel_weight(parcel_weight);
        order.setParcel_dimension(parcel_dimension);
        order.setParcel_value(parcel_value);
        order.setItem_quantity(item_quantity);
        order.setAllow_mutual_check(allow_mutual_check);
        order.setCod(cod);
        order.setCollect_type(collect_type);
        order.setShipping_service(shipping_service);
        order.setShipping_fee_payment(shipping_fee_payment);
        order.setShipping_fee(0);
        order.setStatus("In transit");
        orderService.save(order);
        return "redirect:/order";
    }

    @GetMapping("/return-order/{id}")
    public String returnOrder(@PathVariable(value = "id") Long id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("orderDetail", order);
        return "return-submit";
    }

    @PostMapping("/return-order/{id}")
    public String returnOrder(@PathVariable(value = "id") Long id , HttpServletRequest request, @RequestParam("file-input") MultipartFile file, Model model) throws IOException{
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String formattedNow = now.format(formatter);
            Order order = orderService.findById(id);
            byte[] imageBytes = file.getBytes();
            String message = request.getParameter("message");
            // Lưu imageBytes vào cơ sở dữ liệu
            ReturnManagement returnManagement = new ReturnManagement();
            returnManagement.setMessage(message);
            returnManagement.setImage(imageBytes);
            returnManagement.setCreated_at(formattedNow);
            returnManagement.setOrder(order);
            /*double imageSimilarity = ImageSimilarityCheck(, );
            returnManagement.setSimilarity(imageSimilarity * 100);*/
            returnManagementService.save(returnManagement);



        } catch (Exception e) {
            model.addAttribute("error", "Error while uploading image");
            System.out.println(e.getMessage());
            return "redirect:/return-order/{id}";
        }
        return "redirect:/return-management";
    }

    public String getImagePath(int id) {
        String filePath = null;
        String url = "jdbc:postgresql://35.202.14.32:5432/postgres";
        String username = "postgres";
        String password = "abc123";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT image FROM return_management WHERE id = ?")) {
            String tempFilePath = "path/to/temp/image";
            statement.setLong(1, id);
            statement.executeUpdate();
            filePath = tempFilePath;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static Double ImageSimilarityCheck (String imagePath1, String imagePath2) {
        // Load OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Đường dẫn tới hai hình ảnh cần so sánh
        /*String imagePath1 = "C:\\Users\\phatp\\IdeaProjects\\Test\\pexels-photo-8947493.jpeg";
        String imagePath2 = "C:\\Users\\phatp\\IdeaProjects\\Test\\pexels-photo-8947493-1.jpeg";*/

        // Đọc hai hình ảnh
        Mat image1 = Imgcodecs.imread(imagePath1);
        Mat image2 = Imgcodecs.imread(imagePath2);

        // Chuyển đổi hình ảnh thành ảnh grayscale
        Mat grayImage1 = new Mat();
        Mat grayImage2 = new Mat();
        Imgproc.cvtColor(image1, grayImage1, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(image2, grayImage2, Imgproc.COLOR_BGR2GRAY);

        // Điều chỉnh kích thước của hai hình ảnh để đảm bảo cùng kích thước
        Size size = new Size(300, 300); // Kích thước mới cho hình ảnh
        Imgproc.resize(grayImage1, grayImage1, size);
        Imgproc.resize(grayImage2, grayImage2, size);

        // Tính histogram của hai hình ảnh
        Mat histImage1 = new Mat();
        Mat histImage2 = new Mat();
        Imgproc.calcHist(List.of(new Mat[]{grayImage1}), new MatOfInt(0), new Mat(), histImage1, new MatOfInt(256), new MatOfFloat(0, 256));
        Imgproc.calcHist(List.of(new Mat[]{grayImage2}), new MatOfInt(0), new Mat(), histImage2, new MatOfInt(256), new MatOfFloat(0, 256));

        // Chuẩn hóa histogram
        Core.normalize(histImage1, histImage1, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        Core.normalize(histImage2, histImage2, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        // Tính hệ số tương đồng giữa hai histogram
        double similarity = Imgproc.compareHist(histImage1, histImage2, Imgproc.HISTCMP_CORREL);

        // In ra kết quả
        return similarity;
    }

}
