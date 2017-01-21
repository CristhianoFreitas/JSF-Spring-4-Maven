package br.com.jsf.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jsf.model.User;
import br.com.jsf.service.UserService;

@Component(value = "userMB")
@ViewScoped
public class UserMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String CONSULTA = "/faces/aplicacao/funcionario/funcionarioConsulta.jsf";
    private static final String DETALHE = "/faces/aplicacao/funcionario/funcionarioDetalhe.jsf";

    List<User> userList;

    private int id;
    private String name;
    private String surname;

    @Autowired
    UserService userService;

    @PostConstruct
    public void init() {
	this.userList = Collections.emptyList();
	limpar();
    }

    public List<User> filtrar() {
	userList = new ArrayList<User>();
	userList.addAll(userService.getUsers());
	return userList;
    }

    public String salvar() {
	try {
	    final User user = new User();
	    user.setId(getId());
	    user.setName(getName());
	    user.setSurname(getSurname());
	    userService.addUser(user);

	} catch (final Exception e) {
	    e.printStackTrace();
	}
	return CONSULTA;
    }

    public void limpar() {
	this.setId(0);
	this.setName("");
	this.setSurname("");
    }

    public String novo() {
	return DETALHE;
    }

    public String consulta() {
	return CONSULTA;
    }

    public void setUserList(final List<User> userList) {
	this.userList = userList;
    }

    public int getId() {
	return id;
    }

    public void setId(final int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(final String surname) {
	this.surname = surname;
    }

}
