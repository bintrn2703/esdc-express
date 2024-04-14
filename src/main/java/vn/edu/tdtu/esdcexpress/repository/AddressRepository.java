package vn.edu.tdtu.esdcexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.tdtu.esdcexpress.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    public Iterable<Address> findByUserUsername(String username);
}
