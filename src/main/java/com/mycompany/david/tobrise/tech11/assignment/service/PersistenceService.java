package com.mycompany.david.tobrise.tech11.assignment.service;

import com.mycompany.david.tobrise.tech11.assignment.entity.User;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@RequestScoped
public class PersistenceService {
    
    @PersistenceContext
    EntityManager entityManager;
    
    public User saveUser(User user){
        entityManager.persist(user);
        return user;
    }
    
    public User updateUser(User user){
        entityManager.merge(user);
        return user;
    }
    
       public List<User> getAllUsers(){
        return entityManager.createQuery("SELECT user from User user order by user.firstName", User.class).getResultList(); 
    }
    
    public User findById(Long id){
        return entityManager.find(User.class, id);
    }
    
    public void deleteUser(User user){
        if(!entityManager.contains(user)){
            user = entityManager.merge(user);
        }
        entityManager.remove(user);
    }
    
}
