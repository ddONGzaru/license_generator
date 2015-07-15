package io.manasobi.security;

import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;

public interface UserDetailsRepository {

	int save(Client client);
	
	@Repository("userDetailsRepository")
	public class UserDetailsRepositoryImpl implements UserDetailsRepository {

		@Autowired
	    private MongoCollection users;
		
		@Override
		public int save(Client client) {
			
			WriteResult result = users.save(client);
			
			return result.getN();
			
		}

	}
	
}
