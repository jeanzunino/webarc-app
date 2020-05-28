package com.undcon.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.undcon.app.enums.ResourceType;
import com.undcon.app.exceptions.UndconException;
import com.undcon.app.filtering.PredicateBuilder;
import com.undcon.app.utils.PageUtils;

@Component
public abstract class AbstractService<T> {

	@Autowired
	protected PermissionService permissionService;

	protected abstract JpaRepository<T, Long> getRepository();

	protected abstract ResourceType getResourceType();

	protected void validateBeforePost(T entity) throws UndconException {
	}

	protected void validateBeforeUpdate(T entity) throws UndconException {

	}

	protected void validateBeforeDelete(T entity) throws UndconException {

	}

	protected Page<T> getAll(Class<? extends T> clazz, String filter, Integer page, Integer size) {
		String clazzName = clazz.getSimpleName();
		clazzName = clazzName.substring(0, 1).toLowerCase().concat(clazzName.substring(1));
		PathBuilder<T> pathBuilder = new PathBuilder<T>(clazz, clazzName);
		Predicate predicate = new PredicateBuilder<T>(pathBuilder).buildFilteredResult(filter);
		QueryDslPredicateExecutor<T> repo = (QueryDslPredicateExecutor<T>) getRepository();
		return repo.findAll(predicate, PageUtils.createPageRequest(page, size));
	}

	public T findById(Long id) throws UndconException {
		return getRepository().findOne(id);
	}

	public T persist(T entity) throws UndconException {
		permissionService.checkPermission(getResourceType());
		validateBeforePost(entity);
		return getRepository().save(entity);
	}

	public T update(T entity) throws UndconException {
		permissionService.checkPermission(getResourceType());
		validateBeforeUpdate(entity);
		return getRepository().save(entity);
	}

	public void delete(long id) throws UndconException {
		permissionService.checkPermission(getResourceType());
		T entity = findById(id);
		validateBeforeDelete(entity);
		getRepository().delete(id);
	}
}
