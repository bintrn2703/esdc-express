package vn.edu.tdtu.esdcexpress.service;

import vn.edu.tdtu.esdcexpress.model.Address;
import vn.edu.tdtu.esdcexpress.model.User;

import java.util.List;

public interface AddressService {
    public Iterable<Address> getAddressesByUsername(User user);
    public Address save(Address address);
    public Address findAddressById(Long id);
    public Address getAddressByPhoneNumber(String phoneNumber);
    public void deleteAddress(Address address);
    public Iterable<Address> getAddressTypes(User user, String type);
}
