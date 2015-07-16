package io.manasobi.license.pager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.manasobi.license.LicenseDetails;

@Repository
public interface PagerRepository extends MongoRepository<LicenseDetails, String> {

	@Override
	Page<LicenseDetails> findAll(Pageable pageable);

}
