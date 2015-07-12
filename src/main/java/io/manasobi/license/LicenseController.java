package io.manasobi.license;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LicenseController {
	
	
	
	
	@RequestMapping(value = {"/1", "/publish"}, method = RequestMethod.GET)
	public String publishLicense() {
		return "main";
	}
	
	
	
}
