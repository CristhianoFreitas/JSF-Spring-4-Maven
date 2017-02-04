package br.com.jsf.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jsf.model.Funcionario;

@Repository
public class FuncionarioDAOImpl implements FuncionarioDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void salvar(final Funcionario funcionario) {
	sessionFactory.getCurrentSession().save(funcionario);
    }

    public void excluir(final Funcionario funcionario) {
	sessionFactory.getCurrentSession().delete(funcionario);
    }

    public void atualizar(final Funcionario funcionario) {
	sessionFactory.getCurrentSession().update(funcionario);
    }

    public Funcionario recuperar(final int id) {
	@SuppressWarnings("unchecked")
	final List<Funcionario> list = sessionFactory.getCurrentSession().createQuery("from Funcionario where id=?")
		.setParameter(0, id).list();
	return list.get(0);
    }

    public List<Funcionario> listar() {
	@SuppressWarnings("unchecked")
	final List<Funcionario> list = sessionFactory.getCurrentSession().createQuery("from Funcionario").list();
	return list;
    }

}
