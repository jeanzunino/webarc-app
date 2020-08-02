package com.undcon.app.utils;

import org.springframework.data.domain.PageRequest;

public class PageUtils {

	private static final int PAGE_NUMBER_DEFAULT = 0;
	private static final int PAGE_SIZE_DEFAULT = 10;

	private PageUtils() {
	}

	public static PageRequest createPageRequest(Integer page, Integer size) {
		page = NumberUtils.isNullOrZero(page) ? PAGE_NUMBER_DEFAULT : page;
		size = NumberUtils.isNullOrZero(size) ? PAGE_SIZE_DEFAULT : size;
		return new PageRequest(page, size);
	}
	
}
