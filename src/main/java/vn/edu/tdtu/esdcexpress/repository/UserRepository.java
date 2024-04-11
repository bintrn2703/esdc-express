package vn.edu.tdtu.esdcexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.tdtu.esdcexpress.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    public User findByName(String name);
}
