package com.undcon.app.rest.apis;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.dtos.ProductSaledInfoDto;
import com.undcon.app.dtos.SaleInfoDto;
import com.undcon.app.services.DashBoardService;
import com.undcon.app.services.SaleService;

/**
 * Api para os DashBoards
 */
@Component
@Path("/dashboards")
public class DashBoardApi {

	@Autowired
	private DashBoardService service;

	@Autowired
	private SaleService saleService;
	
	@GET
	@Path("/countCustomersTotal")
	@Produces(MediaType.APPLICATION_JSON)
	public Long getCountCustomersTotal() {
		return service.countCustomersTotal();
	}
	
	@GET
	@Path("/countProductsTotal")
	@Produces(MediaType.APPLICATION_JSON)
	public Long getCountProductsTotal() {
		return service.countProductsTotal();
	}
	
	@GET
	@Path("/countProvidersTotal")
	@Produces(MediaType.APPLICATION_JSON)
	public Long getCountProvidersTotal() {
		return service.countProvidersTotal();
	}
	
	@GET
	@Path("/sale/total")
	@Produces(MediaType.APPLICATION_JSON)
	public SaleInfoDto getSaleTotal() {
		return saleService.getTotalSale();
	}

	@GET
	@Path("/sale/topProductSaled")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductSaledInfoDto> getTopProductSaled() {
		return saleService.getTopProductSaled(true);
	}
	
}