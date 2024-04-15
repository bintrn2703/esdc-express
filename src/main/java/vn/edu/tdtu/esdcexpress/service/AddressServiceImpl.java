package vn.edu.tdtu.esdcexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.esdcexpress.model.Address;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.repository.AddressRepository;

import java.util.List;

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

    @Override
    public Address findAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public Address getAddressByPhoneNumber(String phoneNumber) {
        return addressRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public void deleteAddress(Address address) {
        addressRepository.delete(address);
    }

    @Override
    public Iterable<Address> getAddressTypes(User user, String type) {
        return addressRepository.findByUserUsernameAndType(user.getUsername(), type);
    }


}
