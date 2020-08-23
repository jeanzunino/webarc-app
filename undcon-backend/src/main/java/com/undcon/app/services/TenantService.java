package com.undcon.app.services;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.SystemSalesmanEntity;
import com.undcon.app.model.TenantEntity;
import com.undcon.app.multitenancy.DataSourceConfiguration;
import com.undcon.app.multitenancy.DataSourceProperties;
import com.undcon.app.multitenancy.DatabaseSchemaType;
import com.undcon.app.repositories.ITenantRepository;
import com.undcon.app.utils.NumberUtils;

@Component
public class TenantService extends AbstractService<TenantEntity> {

	@Autowired
	private ITenantRepository tenantRepository;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private SystemSalesmanService salesmanService;

	@Autowired
	private DataSourceConfiguration dataSourceConfiguration;

	@Autowired
	private DataSourceProperties dataSourceProperties;
	
	@Autowired
	private EmailSenderService emailService;

	public Page<TenantEntity> getAll(String filter, Integer page, Integer size) throws UndconException {
		permissionService.checkPermission(getResourceType());
		return super.getAll(TenantEntity.class, filter, page, size);
	}

	public TenantEntity findById(Long id) throws UndconException {
		permissionService.checkPermission(ResourceType.TENANT);
		return tenantRepository.findById(id);
	}

	@Override
	protected void validateBeforePost(TenantEntity entity) throws UndconException {
		super.validateBeforePost(entity);
		if (NumberUtils.longIsPositiveValue(entity.getId())) {
			throw new UndconException(UndconError.NEW_REGISTER_INVALID_ID);
		}
		validateName(entity);
		validateSchemaName(entity);
		validateSalesman(entity);
	}

	@Override
	protected void validateBeforeUpdate(TenantEntity entity) throws UndconException {
		super.validateBeforeUpdate(entity);
		validateSalesman(entity);
	}

	@Override
	protected void afterCommitPost(TenantEntity saved) {
		super.afterCommitPost(saved);

		String message = "<b>Novo tenant cadastro.</b>\n\n\n" //
				+ "Nome do Cliente:" + saved.getName() + "\n\n" //
				+ "Att, \nJean Victor Zunino";
		
		emailService.sendEmail(message);
	}
	
	private void validateName(TenantEntity entity) throws UndconException{
		List<TenantEntity> findByIdNotAndName = tenantRepository.findByIdNotAndNameIgnoreCase(entity.getId(), entity.getName());
		if(!findByIdNotAndName.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}
	
	private void validateSchemaName(TenantEntity entity) throws UndconException{
		List<TenantEntity> findByIdNotAndSchema = tenantRepository.findByIdNotAndSchemaNameIgnoreCase(entity.getId(), entity.getSchemaName());
		if(!findByIdNotAndSchema.isEmpty()) {
			throw new UndconException(UndconError.NAME_ALREADY_EXISTS);
		}
	}

	private void validateSalesman(TenantEntity entity) throws UndconException {
		SystemSalesmanEntity salesman = salesmanService.findById(entity.getSalesman().getId());
		if (salesman == null) {
			throw new UndconException(UndconError.SALESMAN_NOT_FOUND);
		}
	}

	@Override
	protected JpaRepository<TenantEntity, Long> getRepository() {
		return tenantRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.TENANT;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createDb(long id) throws UndconException {
		TenantEntity entity = findById(id);
		String tenant = entity.getSchemaName();
		DataSource dataSource = dataSourceProperties.getDatasources().get(tenant);
		DatabaseSchemaType database = DatabaseSchemaType.TENANTS;
		dataSourceConfiguration.migrate(tenant, dataSource, database);
	}

}
