package io.manasobi.security;

import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MongoDBUserDetailsService implements UserDetailsService {

    @Autowired
    private MongoCollection users;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
    	UserDetails loadedUser;

        try {
        	
            Client client = users.findOne("{#: #}", Client.USERNAME, username).as(Client.class);
            
            if (client == null) {
            	
                throw new InternalAuthenticationServiceException("존재하지 않는 사용자입니다.");
            }
            
            loadedUser = new User(client.getUsername(), client.getPassword(), client.getRoles());
            
        } catch (Exception e) {
        	
        	throw e;
        }
        
        return loadedUser;
    }
}
