package com.undcon.app.rest.old;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.undcon.app.model.old.Sale;
import com.undcon.app.old.service.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleRestController {

	private static final Logger log = LoggerFactory.getLogger(SaleRestController.class);
	private static final String DEFAULT_PAGE_NUM = "0";
	private static final String DEFAULT_PAGE_SIZE = "100";

	@Autowired
	private SaleService saleService;

	@RequestMapping(method = GET)
	public Page<Sale>  page(
			@RequestParam(value = "page", required = false, defaultValue = DEFAULT_PAGE_NUM) final Integer page,
			@RequestParam(value = "size", required = false, defaultValue = DEFAULT_PAGE_SIZE) final Integer size) {
		return saleService.getAll(page, size);
	}

	@RequestMapping(method = POST)
	void add(@RequestBody final Sale sale) {
		saleService.create(sale);
	}

}
