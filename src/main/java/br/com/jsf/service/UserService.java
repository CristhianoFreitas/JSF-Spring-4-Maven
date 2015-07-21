package br.com.jsf.service;

import java.util.List;

import br.com.jsf.model.User;

public interface UserService {

	public void addUser(User user);
    
    public void updateUser(User user);
 
    public void deleteUser(User user);
     
    public User getUserById(int id);
     
    public List<User> getUsers();
    
}
