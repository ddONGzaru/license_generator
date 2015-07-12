package io.manasobi.security;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;

import io.manasobi.commons.constant.Result;

@Controller
public class UserDetailsController {

	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@RequestMapping(value = "/reg/user", method = RequestMethod.GET)
	public String register() {
		
		return "register";
	}

	@RequestMapping(value = "/reg/user", method = RequestMethod.POST)
	public String register(String username, String password, String confirmPwd, Model model) {
		
		String viewName = "register";
		
		if (!StringUtils.equals(password, confirmPwd)) {
			
			model.addAttribute("PWD_ERROR_MSG", Result.ERROR_101002.getMessage());
			
		} else {
			
			List<GrantedAuthority> roles = Lists.newArrayList();
			roles.add(new SimpleGrantedAuthority("USER"));
			
			Client client = new Client();
			
			client.setUsername(username);
			client.setPassword(password);
			client.setRoles(roles);
			
			Result result = userDetailsService.register(client);
			
			if (result == Result.ERROR_101001) {
				model.addAttribute("USERNAME_ERROR_MSG", result.getMessage());
			} else {
				viewName = "main";
			}
		}
		
		return viewName;
	}
}
