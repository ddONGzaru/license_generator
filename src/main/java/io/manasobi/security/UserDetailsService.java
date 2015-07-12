package io.manasobi.security;

import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.manasobi.commons.constant.Result;

public interface UserDetailsService {

	Result register(Client client);
	
	
	@Service("userDetailsService")
	public class UserDetailsServiceImpl implements UserDetailsService {

		@Autowired
		private UserDetailsRepository userDetailsRepo;
		
		@Autowired
	    private MongoCollection users;
		
		@Override
		public Result register(Client client) {

			if (exists(client.getUsername())) {
				
				return Result.ERROR_101001;
			}
			
			int resultNum = userDetailsRepo.save(client);
			
			return resultNum > 0 ? Result.SUCCESS : Result.ERROR_101002;
		}
		
		private boolean exists(String username) {
			
			Client client = users.findOne("{#: #}", Client.USERNAME, username).as(Client.class);
            
			return client != null ? true : false;
		}
		
	}
	
	
}
