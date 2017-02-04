package br.com.jsf.dao;

import java.util.List;

import br.com.jsf.model.Funcionario;

public interface UserDAO {

    public void salvar(Funcionario user);

    public void atualizar(Funcionario user);

    public void excluir(Funcionario user);

    public Funcionario recuperar(int id);

    public List<Funcionario> listar();
}
