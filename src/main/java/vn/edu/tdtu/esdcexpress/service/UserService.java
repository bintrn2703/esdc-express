package vn.edu.tdtu.esdcexpress.service;

public interface UserService {
    public boolean loginWithUsernameAndPasswordInDB(String username, String password) throws Exception;
}
