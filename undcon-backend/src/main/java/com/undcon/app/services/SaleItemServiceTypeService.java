package com.undcon.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.undcon.app.dtos.ItemRequestDto;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.model.EmployeeEntity;
import com.undcon.app.model.SaleEntity;
import com.undcon.app.model.SaleItemEntity;
import com.undcon.app.model.SaleItemServiceEntity;
import com.undcon.app.model.ServiceTypeEntity;
import com.undcon.app.model.UserEntity;
import com.undcon.app.repositories.ISaleItemServiceRepository;
import com.undcon.app.utils.NumberUtils;

@Component
public class SaleItemServiceTypeService extends AbstractService<SaleItemServiceEntity> {

	@Autowired
	private ISaleItemServiceRepository saleItemServiceRepository;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ServiceTypeService serviceTypeService;
	
	@Transactional
	public SaleItemEntity addItemService(long saleId, ItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);
		SaleEntity sale = saleService.findById(saleId);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		ServiceTypeEntity service = serviceTypeService.findById(itemDto.getItemId());
		if (service == null) {
			throw new UndconException(UndconError.SERVICE_TYPE_NOT_FOUND);
		}

		UserEntity user = userService.getCurrentUser();

		EmployeeEntity employee = user.getEmployee();

		// Se o Front não enviar o funciona´rio
		if (NumberUtils.longIsPositiveValue(itemDto.getEmployeeId())) {
			employee = employeeService.findById(itemDto.getEmployeeId());
		}

		SaleItemServiceEntity item = new SaleItemServiceEntity(null, service, sale, user, employee, service.getPrice(),
				itemDto.getQuantity());

		return saleItemServiceRepository.save(item);
	}

	@Transactional
	public SaleItemEntity updateServiceItem(long saleId, ItemRequestDto itemDto) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		SaleEntity sale = saleService.findById(saleId);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}
		UserEntity user = userService.getCurrentUser();
		ServiceTypeEntity service = serviceTypeService.findById(itemDto.getItemId());

		SaleItemServiceEntity item = saleItemServiceRepository.findOne(itemDto.getId());
		item.setService(service);
		item.setPrice(item.getPrice());
		item.setUser(user);

		return saleItemServiceRepository.save(item);
	}

	public void deleteServiceItem(Long saleId, long itemId) throws UndconException {
		permissionService.checkPermission(ResourceType.SALE);

		SaleEntity sale = saleService.findById(saleId);
		if (sale == null) {
			throw new UndconException(UndconError.SALE_NOT_FOUND);
		}

		SaleItemServiceEntity item = saleItemServiceRepository.findOne(itemId);
		if (item == null) {
			throw new UndconException(UndconError.SALE_ITEM_NOT_FOUND);
		}

		if (!item.getSale().getId().equals(sale.getId())) {
			throw new UndconException(UndconError.SALE_ITEM_NOT_FOUND_IN_THE_SALE);
		}
		saleItemServiceRepository.delete(item);
	}
	
	@Override
	protected JpaRepository<SaleItemServiceEntity, Long> getRepository() {
		return saleItemServiceRepository;
	}

	@Override
	protected ResourceType getResourceType() {
		return ResourceType.SALE;
	}

}
