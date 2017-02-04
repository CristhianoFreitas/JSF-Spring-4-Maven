package br.com.jsf.service;

import java.util.List;

import br.com.jsf.model.Funcionario;

public interface FuncionarioService {

    public void salvar(Funcionario funcionario);

    public void atualizar(Funcionario funcionario);

    public void excluir(Funcionario funcionario);

    public Funcionario recuperar(int id);

    public List<Funcionario> listar();

}
