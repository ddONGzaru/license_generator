package io.manasobi.license;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.manasobi.commons.constant.Result;
import io.manasobi.license.pager.PagerRepository;
import io.manasobi.utils.DateUtils;

public interface LicenseService {

	Result sendJobTicket(License license);
	
	Result saveLicenseDetails(String userName, License license);
	
	LicenseDetails findLiceseDetails(String licenseKey);
	
	Page<LicenseDetails> findAllLiceseDetails(Pageable pageable);
	
	String findLicenseKey(String genKey);
	
	@Service("licenseService")
	public class LicenseServiceImpl implements LicenseService {

		@Autowired
		private RabbitTemplate rabbitTemplate;
		
		@Autowired
		private LicenseRepository licenseRepo;
		
		@Resource
		private PagerRepository pagerRepo;
		
		@Override
		public Result sendJobTicket(License license) {
			
			checkAndConvertLicenseInfo(license);
			
			try {
				
				rabbitTemplate.convertAndSend(license);
				
				return Result.SUCCESS;
				
			} catch (AmqpException e) {
				
				Result error = Result.ERROR_102001;
				error.setMessage(e.getMessage());
				
				return error;				
			}
		}
		
		private void checkAndConvertLicenseInfo(License license) {
			
			license.setId(generateLicenseID());
			
			if (StringUtils.equals(license.getType(), "02")) {				
				license.setExpirationDate(generatoeExpirationDate(license.getExpirationDays()));				
			} else {
				license.setExpirationDate("-");
			}
			
			if (StringUtils.equals(license.getType(), "01")) {
				license.setType("Production");
			} else if (StringUtils.equals(license.getType(), "02")) {
				license.setType("Trial");
			} else if (StringUtils.equals(license.getType(), "03")) {
				license.setType("Developer");
			}			
				
		}	
		
		private String generateLicenseID() {
			return StringUtils.upperCase(UUID.randomUUID().toString());
		}
		
		private String generatoeExpirationDate(int expirationDays) {
			
			String currentDate = DateUtils.convertDateToString(new Date(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss");
			
			String expirationDate = DateUtils.addDays(currentDate, expirationDays, "yyyy-MM-dd HH:mm:ss");
			
			return expirationDate;
		}
		

		@Override
		public Result saveLicenseDetails(String userName, License license) {
			
			LicenseDetails licenseDetails = buildLicenseDetails(userName, license);
			
			return licenseRepo.save(licenseDetails);
		}

		private LicenseDetails buildLicenseDetails(String userName, License license) {
			
			Date createdDate = new DateTime().plusHours(9).toDate();
			
			LicenseDetails licenseDetails = new LicenseDetails();
			
			licenseDetails.setGenKey(license.getId());
			licenseDetails.setUserName(userName);
			licenseDetails.setLicense(license);
			licenseDetails.setCreatedDate(createdDate);			
			
			return licenseDetails;
		}

		@Override
		public LicenseDetails findLiceseDetails(String licenseKey) {
			
			return licenseRepo.findLicenseDetails(licenseKey);
		}

		@Override
		public Page<LicenseDetails> findAllLiceseDetails(Pageable pageable) {
			
			return pagerRepo.findAll(pageable);
		}

		@Override
		public String findLicenseKey(String genKey) {
			
			LicenseDetails licenseDetails = licenseRepo.findLicenseDetails(genKey);
			
			return licenseDetails.getKey();
		}
		
	}
}
