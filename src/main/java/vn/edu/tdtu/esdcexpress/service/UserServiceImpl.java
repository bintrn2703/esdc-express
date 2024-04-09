package vn.edu.tdtu.esdcexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.esdcexpress.model.User;
import vn.edu.tdtu.esdcexpress.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public boolean loginWithUsernameAndPassword(String username, String password){
        User newUser = userRepository.findById(username).orElse(null);
        if(newUser == null) {
            return false;
        }
        return newUser.getPassword().equals(password);
    }
}
