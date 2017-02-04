package br.com.jsf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.jsf.dao.FuncionarioDAO;
import br.com.jsf.model.Funcionario;

@Component
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioDAO funcionarioDAO;

    public void salvar(final Funcionario funcionario) {
	funcionarioDAO.salvar(funcionario);
    }

    public void excluir(final Funcionario funcionario) {
	funcionarioDAO.excluir(funcionario);
    }

    public void atualizar(final Funcionario funcionario) {
	funcionarioDAO.atualizar(funcionario);
    }

    public Funcionario recuperar(final int id) {
	return funcionarioDAO.recuperar(id);
    }

    public List<Funcionario> listar() {
	return funcionarioDAO.listar();
    }

}
