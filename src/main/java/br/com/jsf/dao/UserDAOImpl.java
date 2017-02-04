package br.com.jsf.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jsf.model.Funcionario;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void salvar(final Funcionario user) {
	sessionFactory.getCurrentSession().save(user);
    }

    public void excluir(final Funcionario user) {
	sessionFactory.getCurrentSession().delete(user);
    }

    public void atualizar(final Funcionario user) {
	sessionFactory.getCurrentSession().update(user);
    }

    public Funcionario recuperar(final int id) {
	final List<Funcionario> list = sessionFactory.getCurrentSession().createQuery("from User where id=?")
		.setParameter(0, id).list();
	return list.get(0);
    }

    public List<Funcionario> listar() {
	final List<Funcionario> list = sessionFactory.getCurrentSession().createQuery("from User").list();
	return list;
    }

}
