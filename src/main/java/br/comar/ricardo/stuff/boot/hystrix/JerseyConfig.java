package br.comar.ricardo.stuff.boot.hystrix;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import br.comar.ricardo.stuff.boot.hystrix.api.NIOAPI;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(NIOAPI.class);
	}

}