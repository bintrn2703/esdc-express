package vn.edu.tdtu.esdcexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.tdtu.esdcexpress.model.Finance;

public interface FinanceRepository extends JpaRepository<Finance, Long> {
    public Iterable<Finance> findByUserUsername(String username);
//    public Iterable<Finance> findByOrderOrderID(Long orderID);
}
