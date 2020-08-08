package com.undcon.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.undcon.app.dtos.BankCheckDto;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.model.BankCheckEntity;
import com.undcon.app.repositories.IBankCheckRepository;

@Component
public class BankCheckService extends AbstractService<BankCheckEntity> {

	@Autowired
	private IBankCheckRepository checkRepository;

	public Page<BankCheckEntity> getAll(String filter, Integer page, Integer size) {
		return super.getAll(BankCheckEntity.class, filter, page, size);
	}

	@Override
	protected JpaRepository<BankCheckEntity, Long> getRepository() {
		return checkRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.BACK_CHECK;
	}

	public void saveBankCheck(BankCheckDto dto) {
		Assert.isTrue(dto.getNumber() != null && dto.getNumber() > 0, "check.number is required");
		Assert.hasText(dto.getPersonDocument(), "check.personDocument is required");
		Assert.hasText(dto.getEmitter(), "check.emitter is required");
		BankCheckEntity check = new BankCheckEntity(null, dto.getNumber(), dto.getEmitter(), dto.getPersonDocument());
		checkRepository.save(check);
	}

}
