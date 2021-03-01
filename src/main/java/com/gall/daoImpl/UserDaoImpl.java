package com.gall.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.gall.dao.UserDao;
import com.gall.model.LoginUser;


@Repository
@Transactional
public class UserDaoImpl  implements UserDao  {
	
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public LoginUser getGetUserDetails(String username) {
		TypedQuery<LoginUser> query = entityManager.createQuery("from LoginUser where username=:userName", LoginUser.class);
		query.setParameter("userName", username);
		LoginUser user = query.getSingleResult();
		return user;
	}

}
