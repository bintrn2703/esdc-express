package vn.edu.tdtu.esdcexpress.service;

import vn.edu.tdtu.esdcexpress.model.Order;
import vn.edu.tdtu.esdcexpress.model.User;

public interface OrderService {
    public Iterable<Order> getOrdersByUsername(User user);
    public Order findById(Long id);
    public Order save(Order order);
    public Order getOrderById(Iterable<Order> orders, Long id);
}
