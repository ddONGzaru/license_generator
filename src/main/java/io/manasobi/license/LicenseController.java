package io.manasobi.license;

import java.security.Principal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epapyrus.sdp.commons.utils.DateUtils;

import io.manasobi.commons.constant.Result;


@Controller
public class LicenseController {
	
	@Autowired
	private LicenseService licenseService; 
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/license/publish", method = RequestMethod.GET)
	public String main(Authentication auth) {
		return "license/publish";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/license/publish", method = RequestMethod.POST)
	public String publish(License license, RedirectAttributes redirectAttributes, Principal principal) {
		
		Result result = Result.EMPTY;
		
		result = licenseService.sendJobTicket(license);
		
		redirectAttributes.addFlashAttribute("licenseGenKey", license.getId());
		
		if (result == Result.ERROR_102001) {
			redirectAttributes.addFlashAttribute("error", result.getMessage());
		}
		
		result = licenseService.saveLicenseDetails(principal.getName(), license);
		
		return "redirect:/license/publish/result";
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/license/publish/result")
	public String publishResult(@ModelAttribute("licenseGenKey") String licenseKey, @ModelAttribute("error") String error, Model model) {
		
		if (licenseKey.equals("")) {
			
			String refreshEventMsg = "[F5] 버튼으로 인한 리프레쉬 이벤트가 발생하였습니다. [발급이력] 메뉴에서 라이센스 생성 키를 확인하세요.";
			licenseKey = refreshEventMsg;
		}
		
		model.addAttribute("licenseGenKey", licenseKey);
		model.addAttribute("error", error);
		
		return "license/publish-result";
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/license/details", method = RequestMethod.GET)
	public String details() {
		return "license/details";
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/license/details", method = RequestMethod.POST)
	public String details(String licenseKey, Model model) {
		
		LicenseDetails licenseDetails = licenseService.findLiceseDetails(licenseKey);
		
		model.addAttribute("license", licenseDetails.getLicense());
		
		model.addAttribute("createdDate", convertISODateToSimpleDate(licenseDetails.getCreatedDate()));
		
		return "license/details-result";
	}
	
	private String convertISODateToSimpleDate(Date isoDate) {
		
		DateTime dateTime = new DateTime(isoDate);
		
		String simpleDate = DateUtils.convertDateToString(dateTime.minusHours(9).toDate(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z");
		
		simpleDate = StringUtils.replace(simpleDate, "T", " ");
		simpleDate = StringUtils.remove(simpleDate, "Z");
		
		return simpleDate;
	}
	
}
