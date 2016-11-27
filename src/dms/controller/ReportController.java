/**
 * 
 */
package dms.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dms.bean.User;
import dms.db.dao.ReportDAO;
import dms.filter.LoggedInFilterBinding;

/**
 * @author Kit
 *
 */
@Path("/report")
@Produces({MediaType.APPLICATION_JSON})
@LoggedInFilterBinding
public class ReportController {
	@Context
	private HttpServletRequest request;

	private ReportDAO reportDAO = new ReportDAO();

	@Path("r1")
	@GET
	public Map<String, Integer> getR1(@QueryParam("from") @DefaultValue("0001-01-01") String from, @QueryParam("to") @DefaultValue("9999-12-30") String to) {
		return reportDAO.getReportGroupByHour(from, to);
	}

	@Path("r2")
	@GET
	public Map<String, Integer> getR2(@QueryParam("from") @DefaultValue("0001-01-01") String from, @QueryParam("to") @DefaultValue("9999-12-30") String to) {
		return reportDAO.getReportGroupByMonth(from, to);
	}

	@Path("r3")
	@GET
	public Map<String, Double> getR3(@QueryParam("from") @DefaultValue("0001-01-01") String from, @QueryParam("to") @DefaultValue("9999-12-30") String to) {
		User user = SessionHelper.getUser(request);
		int airlineId = user.getAirlineId();
		int totalDeparture = reportDAO.getTotalDeparture(airlineId, from, to);
		int onTimeDeparture = reportDAO.getOnTimeDeparture(airlineId, from, to);
		int delayedDeparture = reportDAO.getDelayedDeparture(airlineId, from, to);
		int cancellationDeparture = reportDAO.getCancellationDeparture(airlineId, from, to);
		
		double onTimeRate = 0;
		double delayedRate = 0;
		double cancellationRate = 0;
		if (totalDeparture > 0) {
			onTimeRate = new BigDecimal(onTimeDeparture).divide(new BigDecimal(totalDeparture)).doubleValue();
			delayedRate = new BigDecimal(delayedDeparture).divide(new BigDecimal(totalDeparture)).doubleValue();
			cancellationRate = new BigDecimal(cancellationDeparture).divide(new BigDecimal(totalDeparture)).doubleValue();
		}
		
		Map<String, Double> rateData = new HashMap<>();
		rateData.put("onTime", onTimeRate);
		rateData.put("delay", delayedRate);
		rateData.put("cancel", cancellationRate);
		
		return rateData;
	}

}
