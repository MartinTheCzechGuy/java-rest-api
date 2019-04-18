package cz.ucl.jee.taxi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/orders")
public class TaxiService {

	@GET
	@Path("/echo")
	@Produces(MediaType.TEXT_PLAIN)
	public String greet() {
		return "Taxi service on-line";
	}
	
}
