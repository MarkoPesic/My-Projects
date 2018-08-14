package com.shoes.model;

import org.hibernate.Query;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ShoeDao {
 @Autowired
 SessionFactory sessionFactory;
 
 public void save(Shoe shoe){
       Session session = sessionFactory.getCurrentSession();
       //session.beginTransaction(); 
       session.save(shoe); 
       //session.getTransaction().commit();
    }
 
  public void delete(Shoe shoe){
       Session session = sessionFactory.getCurrentSession();
       //session.beginTransaction(); 
       session.delete(shoe);
       //session.getTransaction().commit();
    }
 
         
 
     public List<Shoe> findByCategory(int id){
        Session session = sessionFactory.getCurrentSession();
        List<Shoe> result = session.getNamedQuery("Shoe.findByCategory").setInteger("category", id).list();
        return result;
    }
     
    public List<Shoe> findByPage(int page){
        int perpage = 3;
        Session session = sessionFactory.getCurrentSession();
        List<Shoe> result = session.createQuery("from Shoe").setFirstResult(page*perpage).setMaxResults(perpage).list();
        return result;
    }
 
     public List<Shoe> find(){ 
        Session session = sessionFactory.getCurrentSession(); 
        List<Shoe> result = session.createCriteria(Shoe.class).list(); 
        return result;
    }
         public Shoe geById(int id){
        return (Shoe)sessionFactory.getCurrentSession().get(Shoe.class, id);
    }

         
         public Shoe getPrice(int price){
        return (Shoe)sessionFactory.getCurrentSession().get(Shoe.class, price);
    }
         
         
       public Long pages(){
       return ((Double)Math.ceil((Long)sessionFactory.getCurrentSession().createQuery("select count(id) from Shoe").uniqueResult()/3)).longValue();
    }
       
           public void update(Shoe shoe){
        sessionFactory.getCurrentSession().update(shoe);
    }
           
     public List findById(int id){
     Session session = sessionFactory.getCurrentSession();
     String SQL_QUERY =" from Shoe as s where s.id=? ";
	Query query = session.createQuery(SQL_QUERY);
	query.setParameter(0,id);
        List <Shoe> result = query.list();
        return result;
       }
}
