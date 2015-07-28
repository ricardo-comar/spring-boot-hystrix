package br.comar.ricardo.stuff.boot.hystrix;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import br.comar.ricardo.stuff.boot.hystrix.api.NIOConsumer;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(NIOConsumer.class);
	}

}