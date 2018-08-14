package com.shoes.model;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class OrdersDao {
    @Autowired
    SessionFactory sessionFactory;
    
    public void save(Orders order){
       Session session = sessionFactory.getCurrentSession();
       //session.beginTransaction(); 
       session.save(order);
       //session.getTransaction().commit();
    }
    
    public void delete(Orders orderr){
       Session session = sessionFactory.getCurrentSession();
       //session.beginTransaction(); 
       session.delete(orderr); 
       //session.getTransaction().commit();
    }
    
       public List<Orders> find(){
       Session session = sessionFactory.getCurrentSession();
       List<Orders> result = session.createCriteria(Orders.class).list(); 
       return result;
    }
       
       
       public List findById(int id){
                        Session session = sessionFactory.getCurrentSession();
			String SQL_QUERY =" from Orders as o where o.id=? ";
			Query query = session.createQuery(SQL_QUERY);
			query.setParameter(0,id);
                        List <Orders> result = query.list();
                        return result;
       }
       
        public Orders geById(int id){
        return (Orders)sessionFactory.getCurrentSession().get(Orders.class, id);
    }
}
