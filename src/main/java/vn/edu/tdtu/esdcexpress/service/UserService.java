package vn.edu.tdtu.esdcexpress.service;

import vn.edu.tdtu.esdcexpress.model.User;

public interface UserService {
    public boolean loginWithUsernameAndPassword(String username, String password);
    public User save(User user);
    public User findByUsername(String username);
}
