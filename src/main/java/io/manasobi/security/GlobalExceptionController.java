package io.manasobi.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {
 
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleAllException(Exception e) {
 
		ModelAndView model = new ModelAndView("error");
		model.addObject("errMsg", e.getMessage());
 
		return model;
 
	}
	
	@RequestMapping(value = "/error/403")
	public String error_403(Authentication auth, Exception e) {
		
		System.out.println("error-403" + e.getMessage());
		
		return "error/error-403";
	}
	
	@RequestMapping(value = "404")
	public String error_404(Authentication auth, Exception e) {
		
		System.out.println("error-404" + e.getMessage());
		
		return "error/error-404";
	}
	
	/*@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {
 
		ModelAndView model = new ModelAndView("error/generic_error");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());
 
		return model;
 
	}*/
 
}
