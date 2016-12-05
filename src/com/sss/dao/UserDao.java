/**
 * 
 */
package com.sss.dao;

import hibernate.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.sss.pojo.User;

/**
 * @author Santhosh
 *
 */
public class UserDao {
	/**
	 * Fetch user by email
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email){
		if(email == null)
			return null;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria criteria = session.createCriteria(User.class);
			Object obj = criteria.add(Restrictions.eq("email", email)).uniqueResult();
			if(obj == null)
				return null;
			User user = (User)obj;
			tx.commit();
			return user;		
		}finally{
			session.close();
		}
	}

}
