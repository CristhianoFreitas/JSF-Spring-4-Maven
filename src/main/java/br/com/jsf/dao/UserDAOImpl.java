package br.com.jsf.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.jsf.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public void deleteUser(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	public User getUserById(int id) {
		List<User> list = sessionFactory.getCurrentSession().createQuery("from User where id=?").setParameter(0, id).list();
		return (User) list.get(0);
	}

	public List<User> getUsers() {
		List<User> list = sessionFactory.getCurrentSession().createQuery("from User").list();
		return list;
	}

}
