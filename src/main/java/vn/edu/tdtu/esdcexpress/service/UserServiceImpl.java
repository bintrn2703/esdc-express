package vn.edu.tdtu.esdcexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.esdcexpress.model.Order;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public boolean loginWithUsernameAndPassword(String username, String password){
        User newUser = userRepository.findById(username).orElse(null);
        if(newUser == null) {
            return false;
        }
        return newUser.getPassword().equals(password);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findById(username).orElse(null);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

}
