package br.com.jsf.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jsf.model.User;
import br.com.jsf.service.UserService;

@Component(value="userMB")
@ViewScoped
public class UserMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "successo";
	private static final String ERROR = "erro";

	@Autowired
	UserService userService;

	List<User> userList;

	private int id;
	private String name;
	private String surname;

	public String addUser() {
		try {
			User user = new User();
			user.setId(getId());
			user.setName(getName());
			user.setSurname(getSurname());
			userService.addUser(user);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ERROR;
	}

	public void reset() {
		this.setId(0);
		this.setName("");
		this.setSurname("");
	}

	public List<User> getUserList() {
		userList = new ArrayList<User>();
		userList.addAll(userService.getUsers());
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
