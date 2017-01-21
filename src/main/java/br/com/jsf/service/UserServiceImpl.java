package br.com.jsf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.jsf.dao.UserDAO;
import br.com.jsf.model.User;

@Component
@Transactional
public class UserServiceImpl implements UserService {
 
	@Autowired
    private UserDAO userDAO;
     
    public void addUser(User user) {
        userDAO.addUser(user);
    }
 
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }
     
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
     
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }
 
    public List<User> getUsers() {    
        return userDAO.getUsers();
    }
 
}
