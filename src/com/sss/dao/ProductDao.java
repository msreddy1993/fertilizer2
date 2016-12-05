/**
 * 
 */
package com.sss.dao;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.sss.pojo.Product;
import com.sss.pojo.User;
import com.sss.util.UserUtil;

/**
 * @author agile
 *
 */
public class ProductDao {
	
	public List<Product> getAllProducts(){
		User user = UserUtil.getUserFromSession();
		List<Product> list = new ArrayList<Product>();
		if(user == null)
			return list;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try{
			Criteria ctr = session.createCriteria(Product.class);
			ctr.add(Restrictions.eq("userId", user.getId()));
			List<Object> objList = ctr.list();
			tx.commit();
			if(objList != null && objList.size() > 0){
				for(Object obj : objList){
					list.add((Product) obj);
				}
			}
			return list;
		}finally{
			session.close();
		}
	}

}
