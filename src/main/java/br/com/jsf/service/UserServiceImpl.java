package br.com.jsf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jsf.dao.UserDAO;
import br.com.jsf.model.User;

@Service
public class UserServiceImpl implements UserService {
 
	@Autowired
    private UserDAO userDAO;
     
    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }
 
    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }
     
    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
     
    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }
 
    @Override
    public List<User> getUsers() {    
        return userDAO.getUsers();
    }
 
}
