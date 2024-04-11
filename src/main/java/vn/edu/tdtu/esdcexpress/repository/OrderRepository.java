package vn.edu.tdtu.esdcexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.tdtu.esdcexpress.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public Iterable<Order> findByUserUsername(String username);
}
