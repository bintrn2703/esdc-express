package vn.edu.tdtu.esdcexpress.service;

import vn.edu.tdtu.esdcexpress.model.Address;
import vn.edu.tdtu.esdcexpress.model.User;

public interface AddressService {
    public Iterable<Address> getAddressesByUsername(User user);
    public Address save(Address address);
}
