package br.com.jsf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.jsf.dao.UserDAO;
import br.com.jsf.model.Funcionario;

@Component
@Transactional
public class UserServiceImpl implements UserService {
 
	@Autowired
    private UserDAO userDAO;
     
    public void salvar(Funcionario user) {
        userDAO.salvar(user);
    }
 
    public void excluir(Funcionario user) {
        userDAO.excluir(user);
    }
     
    public void atualizar(Funcionario user) {
        userDAO.atualizar(user);
    }
     
    public Funcionario recuperar(int id) {
        return userDAO.recuperar(id);
    }
 
    public List<Funcionario> listar() {    
        return userDAO.listar();
    }
 
}
