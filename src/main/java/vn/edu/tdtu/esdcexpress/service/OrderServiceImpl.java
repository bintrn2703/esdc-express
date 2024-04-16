package vn.edu.tdtu.esdcexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.esdcexpress.model.Order;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.repository.OrderRepository;

import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Override
    public Iterable<Order> getOrdersByUsername(User user) {
        return orderRepository.findByUserUsername(user.getUsername());
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Iterable<Order> orders, Long id) {
        for (Order order : orders) {
            if(Objects.equals(order.getId(), id)) {
                return order;
            }
        }
        return null;
    }
}
