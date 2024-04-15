package vn.edu.tdtu.esdcexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.tdtu.esdcexpress.model.Finance;

public interface FinanceRepository extends JpaRepository<Finance, Long> {
    public Iterable<Finance> findByUserUsername(String username);
//    public Iterable<Finance> findByOrderOrderID(Long orderID);
    @Query("SELECT SUM(f.shipping_fee) FROM Finance f")
    Double getTotalAmount();

    @Query("SELECT SUM(f.parcel_value) FROM Finance f WHERE f.cod = 'yes'")
    Double getToTalParcelValue();
}
