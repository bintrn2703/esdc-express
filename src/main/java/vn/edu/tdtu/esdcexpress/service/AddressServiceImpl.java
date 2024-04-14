package vn.edu.tdtu.esdcexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.esdcexpress.model.Address;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.repository.AddressRepository;
@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    AddressRepository addressRepository;
    @Override
    public Iterable<Address> getAddressesByUsername(User user) {
        return addressRepository.findByUserUsername(user.getUsername());
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
