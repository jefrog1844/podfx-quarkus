package com.jcr.podfx.business.users.control;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.NotFoundException;

import com.jcr.podfx.business.users.entity.Credentials;
import com.jcr.podfx.business.users.entity.User;
import com.jcr.podfx.jwt.GenerateToken;

@ApplicationScoped
public class UserController {

    public Credentials signIn(String username, String password) {
        User user = null;
        if (username != null) {
            Optional<User> optional = User.find("username", username).firstResultOptional();
            user = optional.orElseThrow(() -> new NotFoundException());
            if (!user.doesPasswordMatch(password)) {
                throw new EntityNotFoundException("Invalid username or password!");
            }
        } else {
            throw new EntityNotFoundException("Invalid username or password!");
        }

        String token = null;
        try {
            token = GenerateToken.token(user);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Credentials creds = new Credentials(user.username, token);
        return creds;
    }

}
