package vn.edu.tdtu.esdcexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.tdtu.esdcexpress.model.Order;
import vn.edu.tdtu.esdcexpress.model.ReturnManagement;

public interface ReturnManagementRepository extends JpaRepository<ReturnManagement, Long> {
    public Iterable<ReturnManagement> findByOrderId(Long orderID);
}
