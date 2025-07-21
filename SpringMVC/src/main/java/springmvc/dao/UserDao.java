package springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springmvc.model.User;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void registerUser(User user){
        System.out.println("------Inside UserDao registerUser() method,about to persist------");
        this.sessionFactory.getCurrentSession().persist(user);
        System.out.println("------Inside UserDao registerUser() method, persisted successfully!!------");
    }
}
