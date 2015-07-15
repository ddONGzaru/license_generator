package io.manasobi.license;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epapyrus.sdp.commons.utils.DateUtils;

import io.manasobi.commons.constant.Result;

public interface LicenseService {

	Result sendJobTicket(License license);
	
	Result saveLicenseDetails(String userName, License license);
	
	LicenseDetails findLiceseDetails(String licenseKey);
	
	
	@Service("licenseService")
	public class LicenseServiceImpl implements LicenseService {

		@Autowired
		private RabbitTemplate rabbitTemplate;
		
		@Autowired
		private LicenseRepository licenseRepository;
		
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
			return UUID.randomUUID().toString();
		}
		
		private String generatoeExpirationDate(int expirationDays) {
			
			String currentDate = DateUtils.convertDateToString(new Date(System.currentTimeMillis()), "yyyy-MM-dd HH:mm:ss");
			
			String expirationDate = DateUtils.addDays(currentDate, expirationDays, "yyyy-MM-dd HH:mm:ss");
			
			return expirationDate;
		}
		

		@Override
		public Result saveLicenseDetails(String userName, License license) {
			
			LicenseDetails licenseDetails = buildLicenseDetails(userName, license);
			
			return licenseRepository.save(licenseDetails);
		}

		private LicenseDetails buildLicenseDetails(String userName, License license) {
			
			Date createdDate = new DateTime().plusHours(9).toDate();
			
			LicenseDetails licenseDetails = new LicenseDetails();
			
			licenseDetails.setId(license.getId());
			licenseDetails.setUserName(userName);
			licenseDetails.setLicense(license);
			licenseDetails.setCreatedDate(createdDate);			
			
			return licenseDetails;
		}

		@Override
		public LicenseDetails findLiceseDetails(String licenseKey) {
			
			return licenseRepository.findLicenseDetails(licenseKey);
		}
		
	}
}
