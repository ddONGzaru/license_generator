package io.manasobi.license;

import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;

import io.manasobi.commons.constant.Result;

public interface LicenseRepository {

	Result save(LicenseDetails licenseDetails);
	
	LicenseDetails findLicenseDetails(String licenseKey);
	
	@Repository("licenseRepository")
	public class LicenseRepositoryImpl implements LicenseRepository {

		@Autowired
		private MongoCollection licenseDetailsRepo;
		
		@Override
		public Result save(LicenseDetails licenseDetails) {
			
			try {
				
				WriteResult result = licenseDetailsRepo.insert(licenseDetails);
				
				return Result.SUCCESS;
				
			} catch (Exception e) {
				
				return Result.ERROR_103001;
			}
		}

		@Override
		public LicenseDetails findLicenseDetails(String id) {
			
			LicenseDetails licenseDetails = licenseDetailsRepo.findOne("{#: #}", "id", id).as(LicenseDetails.class);
	            
            if (licenseDetails == null) {
                throw new InternalAuthenticationServiceException("존재하지 않는 사용자입니다.");
            }
	            
            return licenseDetails;
		}
		
	}
	
	
}
