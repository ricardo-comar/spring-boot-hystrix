package br.comar.ricardo.stuff.boot.hystrix.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ManagedAsync;
import org.springframework.beans.factory.annotation.Autowired;

import br.comar.ricardo.stuff.boot.hystrix.pojo.NIOResponse;
import br.comar.ricardo.stuff.boot.hystrix.service.NIOServices;

@Path("/nio")
@Produces( MediaType.APPLICATION_JSON)
public class NIOConsumer {

	@Autowired
	NIOServices service;

	@GET
	@Path("/new")
	@ManagedAsync
	public void asyncNIO(@Suspended final AsyncResponse asyncResponse) {
		
		NIOResponse resp = service.getResponse();
		asyncResponse.resume(resp);
	}

}