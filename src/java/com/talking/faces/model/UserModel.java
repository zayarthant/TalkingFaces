package com.talking.faces.model;

import com.talking.faces.entity.UserAccount;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserModel {

    @PersistenceContext(unitName = "TALKING_FACESPU")
    private EntityManager em;

    public UserAccount createUserAccount(String name, String password, String email, String profileImage) {
        UserAccount user = new UserAccount(name, password, email, profileImage);
        em.persist(user);
        return user;
    }

    public UserAccount getUserById(long index) {
        return em.find(UserAccount.class, index);
    }

    public UserAccount getUerInform(String email, String password) {
        return getUserList(email, password).get(0);
    }

    public boolean loginUserAccount(String email, String password) {
        int userAccount = getUserList(email, password).size();
        return userAccount > 0;
    }

    private List<UserAccount> getUserList(String email, String password) {
        return em.createQuery("SELECT e FROM UserAccount e WHERE e.email = ?1 AND e.password = ?2", UserAccount.class)
                .setParameter(1, email).setParameter(2, password).getResultList();
    }

}
