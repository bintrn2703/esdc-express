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
    public boolean loginWithUsernameAndPasswordInDB(String username, String password) throws Exception {
        User newUser = userRepository.findById(username).orElseThrow(() -> new Exception("User not found"));
        return newUser.getPassword().equals(password);
    }
}
