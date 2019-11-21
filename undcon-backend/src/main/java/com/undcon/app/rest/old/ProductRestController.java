package com.undcon.app.rest.old;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.undcon.app.model.ProductEntity;
import com.undcon.app.old.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductRestController {

	private static final Logger log = LoggerFactory.getLogger(ProductRestController.class);
	private static final String DEFAULT_PAGE_NUM = "0";
	private static final String DEFAULT_PAGE_SIZE = "100";

	@Autowired
	private ProductService service;

	@RequestMapping(method = GET)
	public Page<ProductEntity>  page(
			@RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUM) final Integer page,
			@RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) final Integer size) {
		return service.getAll(page, size);
	}

	@RequestMapping(method = POST)
	ProductEntity save(@RequestBody final ProductEntity reading) {
		return service.save(reading);
	}

	@RequestMapping(method = PUT)
	ProductEntity update(@RequestBody final ProductEntity reading) {
		return service.save(reading);
	}
}
