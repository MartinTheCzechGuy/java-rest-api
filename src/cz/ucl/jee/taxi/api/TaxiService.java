package cz.ucl.jee.taxi.api;

import cz.ucl.jee.taxi.dao.Dao;
import cz.ucl.jee.taxi.entities.Order;

import javax.ejb.Local;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/orders")
public class TaxiService {

	@Inject
	private Dao dao;

	@GET
	@Path("/echo")
	@Produces(MediaType.TEXT_PLAIN)
	public String greet() {
		return "Taxi service on-line";
	}


	/**
	 * User inquires about a taxi, method creates a new Order without an assigned car and return suggested time and price to the user
	 * @param order
	 * @return
	 */
	@POST
	@Path("/request")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> newInquiry(Order order){
		int suggestedPrice = (int) (Math.random() * 50 + 1);
		order.setOrderPrice(suggestedPrice);
		dao.saveOrder(order);

		Map<String, String> suggestedData = new HashMap<>();
		suggestedData.put("suggestedTime", order.getArrivalTime().toLocalTime().toString());
		suggestedData.put("suggestedPrice", "$ " + suggestedPrice);

		return suggestedData;
	}

	/**
	 * If there already is the same request without an assigned car, updates it. Otherwise, a new order is created
	 * @param order
	 * @return
	 */
	@PUT
	@Path("/order")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Order newOrder(@QueryParam("orderNumber") int orderNumber, Order order){

		Order existingOrder = dao.findOrderById(orderNumber);

		int carNumber = (int) (Math.random() * 100 + 1);

		if (existingOrder != null){
			order = existingOrder;
			if (existingOrder.getTaxiNumber() == null) {
				order.setTaxiNumber(carNumber);
			}
		} else {
			order.setTaxiNumber(carNumber);
			order.setOrderPrice((int) (Math.random() * 50 + 1));
			dao.saveOrder(order);
		}
		return order;
	}

	/**
	 * List all orders
	 * @return
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> showAllOrders(){
		return dao.getOrders();
	}

	/**
	 * Delete order with given ID
	 * @param orderNumber
	 * @return
	 */
	@DELETE
	@Path("/delete")
	public String deleteOrder(@QueryParam("orderNumber") int orderNumber) {
		if (dao.deleteOrder(orderNumber)){
			return "Order deleted";
		} else throw new NotFoundException("Order with given ID not found.");
	}
}

