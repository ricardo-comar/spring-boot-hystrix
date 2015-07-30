package br.comar.ricardo.stuff.boot.hystrix.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.comar.ricardo.stuff.boot.hystrix.pojo.NIOResponse;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Component
public class NIOServices {

	@HystrixCommand(fallbackMethod = "getResponseFallback", groupKey="nioServices")
	public NIOResponse getResponse(Integer min, Integer max) {
		RestTemplate restTemplate = new RestTemplate();
		NIOResponse resp = restTemplate.getForObject(
				"http://localhost:8080/api/nio/async?minMs=" + min + "&maxMs="
						+ max, NIOResponse.class);

		return resp;
	}

	public NIOResponse getResponseFallback(Integer min, Integer max) {
		return new NIOResponse(-1);
	}

}
