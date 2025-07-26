package com.springCrud.dao;

import com.springCrud.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void createProduct(Product product){
        this.sessionFactory.getCurrentSession().merge(product); // merge() works for Both Insertion/Updation
    }

    @Transactional
    public List<Product> getProducts(){

        CriteriaBuilder builder = this.sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        criteria.from(Product.class);

        return this.sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
    }

    @Transactional
    public void deleteProduct(int pid){
        Product p = this.sessionFactory.getCurrentSession().get(Product.class, pid);
        this.sessionFactory.getCurrentSession().remove(p);
    }
    @Transactional
    public Product getSingleProduct(int pid){
        return this.sessionFactory.getCurrentSession().get(Product.class, pid);
    }
}
