package io.manasobi.security;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.mongodb.MongoTimeoutException;

import io.manasobi.commons.constant.Result;

@Controller
public class UserDetailsController {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Authentication auth, Principal principal) {
		
		
		System.out.println(principal.getName());
		
		//return "redirect:/license/history";		
		
		return "license/history";		
	}
	
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
	
	@ExceptionHandler(MongoTimeoutException.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {

		ModelAndView mav = new ModelAndView();
	    
		mav.addObject("msg", exception);
	    mav.addObject("url", req.getRequestURL());
	    mav.setViewName("error");
	    
	    return mav;
	  }
}
