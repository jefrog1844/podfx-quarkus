package com.jcr.podfx.business.users.boundary;

import com.jcr.podfx.jwt.GenerateToken;
import com.jcr.podfx.business.users.entity.Credentials;
import com.jcr.podfx.business.users.entity.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class UserFacade {

    @PersistenceContext
    EntityManager em;

    public Credentials signIn(Credentials creds) {
        User user = null;
        if (creds != null && creds.getUsername() != null) {
            user = em.
                    createNamedQuery(User.FIND_BY_USERNAME, User.class).
                    setParameter("username", creds.getUsername()).
                    getSingleResult();
            if (user == null || creds.getPassword() == null || !checkPassword(user.getPassword(), creds.getPassword())) {
                throw new EntityNotFoundException("Invalid credentials!");
            }
        } else {
            throw new EntityNotFoundException("Invalid credentials!");
        }

        String token = null;
        try {
            token = GenerateToken.token(user);
        } catch (Exception ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        creds.setPassword(token);
        return creds;
    }

    public User save(User user) {
        return em.merge(user);
    }

    private boolean checkPassword(String creds, String user) {
        return creds.equals(user);
    }

}
