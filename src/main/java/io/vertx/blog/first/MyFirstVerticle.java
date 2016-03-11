package io.vertx.blog.first;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

@Api
@Path("/api")
public class MyFirstVerticle extends AbstractVerticle {

	// Store our product
	private Map<Integer, Whisky> products = new LinkedHashMap<>();

	// Create some product
	private void createSomeData() {
		Whisky bowmore = new Whisky("Bowmore 15 Years Laimrig", "Scotland, Islay");
		products.put(bowmore.getId(), bowmore);
		Whisky talisker = new Whisky("Talisker 57° North", "Scotland, Island");
		products.put(talisker.getId(), talisker);
		Whisky laphroaig = new Whisky("Laphroaig  10 Year Old Single Malt", "Scotland, Islay");
		products.put(laphroaig.getId(), laphroaig);
	}

	/*@Override
	public void start(Future<Void> fut) {

		createSomeData();

		// Create a router object.
		Router router = Router.router(vertx);

		// Rest of the method

		router.route("/").handler(routingContext -> {
			HttpServerResponse response = routingContext.response();
			response.putHeader("content-type", "text/html").end("<h1>Hello from my first Vert.x 3 application</h1>");
		});

		// Serve static resources from the /assets directory
		router.route("/assets/*").handler(StaticHandler.create("assets"));

		router.get("/api/whiskies").handler(this::getAll);
		
		//Sanket's code
		String portProperty = System.getenv("VCAP_APP_PORT");
		int port = config().getInteger("http.port", 8080);
		if (portProperty != null) {
			port = Integer.parseInt(portProperty);
			System.out.println("my cloud foundary Port is " + port);
		}
		System.out.println("Port is " + port);
		vertx.createHttpServer().requestHandler(router::accept).listen(
				// Retrieve the port from the configuration,
				// default to 8080.
				port, result -> {
					if (result.succeeded()) {
						fut.complete();
					} else {
						fut.fail(result.cause());
					}
				});

	}*/
	
	@GET
	@Path("/whiskies")
	@Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "GET Whisky")
	private void getAll(RoutingContext routingContext) {
		/*public void getAsync(@Suspended final AsyncResponse asyncResponse, @Context Vertx vertx){
		vertx..runOnContext(aVoid -> {
			Whisky bowmore = new Whisky("Bowmore 15 Years Laimrig", "Scotland, Islay");
			products.put(bowmore.getId(), bowmore);
			asyncResponse.resume(bowmore);*/
			routingContext.response().putHeader("content-type", "application/json; charset=utf-8")
				.end(Json.encodePrettily(products.values()));
			
		//});
	}
	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "POST Whisky")
    public Whisky postJson(@ApiParam Whisky in) {
        return in;
    }
}