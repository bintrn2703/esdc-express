package vn.edu.tdtu.esdcexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.tdtu.esdcexpress.model.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    public Iterable<Address> findByUserUsername(String username);
    public Address findByPhoneNumber(String phoneNumber);
    public Iterable<Address> findByUserUsernameAndType(String username, String type);
}
