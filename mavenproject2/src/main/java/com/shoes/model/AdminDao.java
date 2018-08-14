package com.shoes.model;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class AdminDao {  
    @Autowired
    SessionFactory sessionFactory;
    
   public List checkLogin(String username, String password){
                        Session session = sessionFactory.getCurrentSession();
			String SQL_QUERY =" from Admin as a where a.username=? and a.password=?";
			Query query = session.createQuery(SQL_QUERY);
			query.setParameter(0,username);
			query.setParameter(1,password);
			List <Admin> result = query.list();
                        return result;
             
       }
    }


