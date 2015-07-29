package br.comar.ricardo.stuff.boot.hystrix.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.comar.ricardo.stuff.boot.hystrix.pojo.NIOResponse;

@Component
public class NIOServices {

	public NIOResponse getResponse(Integer min, Integer max) {
		RestTemplate restTemplate = new RestTemplate();
		NIOResponse resp = restTemplate.getForObject(
				"http://localhost:8080/nio/async?minMs=" + min + "&maxMs=" + max, NIOResponse.class);

		return resp;
	}

}
