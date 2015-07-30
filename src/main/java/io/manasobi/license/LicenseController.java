package io.manasobi.license;

import java.io.OutputStream;
import java.io.StringReader;
import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.manasobi.commons.constant.Result;
import io.manasobi.utils.DateUtils;


@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping(value = "/license")
public class LicenseController {
	
	@Autowired
	private LicenseService licenseService; 
	
	
	@RequestMapping(value = "/publish", method = RequestMethod.GET)
	public String main(Authentication auth) {
		return "license/publish";
	}
	
	//@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public String publish(License license, RedirectAttributes redirectAttributes, Principal principal) {
		
		Result result = Result.EMPTY;
		
		result = licenseService.sendJobTicket(license);
		
		redirectAttributes.addFlashAttribute("licenseGenKey", license.getId());
		
		if (result == Result.ERROR_102001) {
			redirectAttributes.addFlashAttribute("error", result.getMessage());
		}
		
		String userName = principal == null ? "" : principal.getName();
		
		result = licenseService.saveLicenseDetails(userName, license);
		
		return "redirect:/license/publish/result";
	}
	
	@RequestMapping(value = "/publish/result")
	public String publishResult(@ModelAttribute("licenseGenKey") String licenseKey, @ModelAttribute("error") String error, Model model) {
		
		if (licenseKey.equals("")) {
			
			String refreshEventMsg = "[F5] 버튼으로 인한 리프레쉬 이벤트가 발생하였습니다. [발급이력] 메뉴에서 라이센스 생성 키를 확인하세요.";
			licenseKey = refreshEventMsg;
		}
		
		model.addAttribute("licenseGenKey", licenseKey);
		model.addAttribute("error", error);
		
		return "license/publish-result";
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String details() {
		return "license/details";
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.POST)
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

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String history_get(@PageableDefault(sort = "createdDate", direction = Direction.DESC) Pageable pageable,	Model model) {
		
		//curl -v http://localhost:8080/people/search/nameStartsWith?name=K&sort=name&name.dir=desc
		
		//pageable = new PageRequest(page, size, new Sort(direction, sortField));
			
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
	@RequestMapping(value = "/history", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	@ResponseBody
	public void download(String genKey, HttpServletResponse response) {
		
		LicenseDetails licenseDetails = licenseService.findLiceseDetails(genKey);
		
		String licenseKey = licenseDetails.getKey();
		String licenseFileName = licenseDetails.getLicense().getSiteName() + ".cer";
		
		StringReader reader = new StringReader(licenseKey);
		OutputStream outStream = null;
		
		try {
 
			response.setContentLength(licenseKey.length());
			response.setContentType("application/download; utf-8");			
 
			// response header
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", licenseFileName);
			
			response.setHeader(headerKey, headerValue);
 
			// Write response
			outStream = response.getOutputStream();
			
			IOUtils.copy(reader, outStream);
 
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(outStream);
		}
		
	}
	
}
