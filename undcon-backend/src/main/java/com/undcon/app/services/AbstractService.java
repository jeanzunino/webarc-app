package com.undcon.app.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

import com.undcon.app.enums.UndconError;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.utils.PageUtils;

public abstract class AbstractService<T> {

	protected abstract JpaRepository<T, Long> getRepository();

	public List<T> getAll(String name, Integer page, Integer size) {
		if (StringUtils.isEmpty(name)) {
			return getRepository().findAll(PageUtils.createPageRequest(page, size)).getContent();
		}
		return findAllByName(name, PageUtils.createPageRequest(page, size));
	}

	public T findById(Long id) {
		return getRepository().findOne(id);
	}

	public T validateAndGet(Long id) throws UndconException {
		T entity = findById(id);
		if (entity == null) {
			throw new UndconException(getNotFoundError());
		}
		return entity;
	}

	protected abstract UndconError getNotFoundError();

	protected abstract List<T> findAllByName(String name, PageRequest createPageRequest);

}
