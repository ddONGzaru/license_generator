package io.manasobi.license;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epapyrus.sdp.commons.utils.DateUtils;

import io.manasobi.commons.constant.Result;


@Controller
@PreAuthorize("hasRole('USER')")
public class LicenseController {
	
	@Autowired
	private LicenseService licenseService; 
	
	
	@RequestMapping(value = "/license/publish", method = RequestMethod.GET)
	public String main(Authentication auth) {
		return "license/publish";
	}
	
	//@PreAuthorize("isAuthenticated()")
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
	
	@RequestMapping(value = "/license/details", method = RequestMethod.GET)
	public String details() {
		return "license/details";
	}
	
	@RequestMapping(value = "/license/details", method = RequestMethod.POST)
	public String details(String licenseKey, Model model) {
		
		LicenseDetails licenseDetails = licenseService.findLiceseDetails(licenseKey);
		
		model.addAttribute("license", licenseDetails.getLicense());
		
		model.addAttribute("createdDate", convertISODateToDate(licenseDetails.getCreatedDate()));
		
		return "license/details-result";
	}
	
	private String convertISODateToDate(Date isoDate) {
		
		DateTime dateTime = new DateTime(isoDate);
		
		String simpleDate = DateUtils.convertDateToString(dateTime.minusHours(9).toDate(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z");
		
		simpleDate = StringUtils.replace(simpleDate, "T", " ");
		simpleDate = StringUtils.remove(simpleDate, "Z");
		
		
		
		return StringUtils.substring(simpleDate, 0, 19);
	}

	@RequestMapping(value = "/license/history", method = RequestMethod.GET)
	public String history(@RequestParam(required=false, defaultValue = "0") int page, 
			@RequestParam(required=false, defaultValue = "10") int size,
			@RequestParam(required=false, defaultValue = "desc") String sort,
			@RequestParam(required=false, defaultValue = "createdDate") String sortField,
			Model model) {
		
		Direction direction = null;
		
		if (StringUtils.equalsIgnoreCase(sort, "asc")) {
			direction = Direction.ASC;			
		} else if (StringUtils.equalsIgnoreCase(sort, "desc")) {
			direction = Direction.DESC;			
		}
		
		if (page > 0) {
			page--;
		}
		
		Pageable pageable = new PageRequest(page, size, new Sort(direction, sortField));
			
		Page<LicenseDetails> pageList = licenseService.findAllLiceseDetails(pageable);

		buildPager(pageList, model);
		
		return "license/history";
	}
/*	@RequestMapping(value = "/license/history", method = RequestMethod.GET)
	public String history(@PageableDefault Pageable pageable, int number, Model model) {
		
		if (pageable.getPageNumber() == 0) {
			pageable = new PageRequest(0, 10, new Sort(Direction.DESC, "createdDate"));			
		}
		
		Page<LicenseDetails> page = licenseService.findAllLiceseDetails(pageable);
		
		buildPager(page, model);
		
		return "license/history";
	}
*/
	@RequestMapping(value = "/license/history", method = RequestMethod.POST)
	public String history(@PageableDefault Pageable pageable, String licenseKey, Model model) {
		
		Page<LicenseDetails> page = licenseService.findAllLiceseDetails(pageable);
		
		buildPager(page, model);
		
		return "license/history";
	}
	
	private void buildPager(Page<LicenseDetails> page, Model model) {
		
		int current = page.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, page.getTotalPages());
	    
	    //List<LicenseDetails> licenseDetailsList = new ArrayList<>();
	    
	    for (LicenseDetails licenseDetails : page.getContent()) {
	    	
	    	Date createdDate = licenseDetails.getCreatedDate();
	    	
	    	licenseDetails.setCreatedDateStr(convertISODateToDate(createdDate));
	    }
	    
	    model.addAttribute("pager", page);
	    model.addAttribute("info", page.getContent());
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
	    model.addAttribute("totalRecordCount", page.getTotalElements());
	    model.addAttribute("recordCountPerPage", page.getSize());
	}
	
	
	
	
}
