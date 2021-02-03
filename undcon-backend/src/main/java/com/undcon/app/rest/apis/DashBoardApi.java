package com.undcon.app.rest.apis;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.undcon.app.dtos.ItemDashboardInfoDto;
import com.undcon.app.dtos.SaleInfoDto;
import com.undcon.app.dtos.ValueByInterval;
import com.undcon.app.enums.IntervalType;
import com.undcon.app.services.DashBoardService;
import com.undcon.app.services.ExpenseService;
import com.undcon.app.services.IncomeService;
import com.undcon.app.services.PurchaseService;
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

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private IncomeService incomeService;

	@Autowired
	private ExpenseService expenseService;

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
	@Path("/sale/totalSaledProductByInterval")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ValueByInterval> getSaledProductTotal(@QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate, @QueryParam("type") IntervalType type) {
		return saleService.getTotalSaledProductByInterval(startDate, endDate, type);
	}

	@GET
	@Path("/sale/totalSaledServiceByInterval")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ValueByInterval> getSaledServiceTotal(@QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate, @QueryParam("type") IntervalType type) {
		return saleService.getTotalSaledServiceByInterval(startDate, endDate, type);
	}

	@GET
	@Path("/purchase/totalPurchasedProductByInterval")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ValueByInterval> getTotalPurchasedProductByInterval(@QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate, @QueryParam("type") IntervalType type) {
		return purchaseService.getTotalPurchasedProductByInterval(startDate, endDate, type);
	}

	@GET
	@Path("/purchase/totalPurchasedServiceByInterval")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ValueByInterval> getTotalPurchasedServiceByInterval(@QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate, @QueryParam("type") IntervalType type) {
		return purchaseService.getTotalPurchasedServiceByInterval(startDate, endDate, type);
	}

	@GET
	@Path("/income/totalByInterval")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ValueByInterval> totalIncomeByInterval(@QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate, @QueryParam("type") IntervalType type) {
		return incomeService.getTotalByInterval(startDate, endDate, type);
	}

	@GET
	@Path("/expense/totalByInterval")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ValueByInterval> totalExpenseByInterval(@QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate, @QueryParam("type") IntervalType type) {
		return expenseService.getTotalByInterval(startDate, endDate, type);
	}

	@GET
	@Path("/sale/topProductSaled")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemDashboardInfoDto> getTopProductSaled(@QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate, @QueryParam("size") Integer size) {
		size = size == null ? 10 : size;
		return saleService.getTopProductSaled(startDate, endDate, size);
	}

}