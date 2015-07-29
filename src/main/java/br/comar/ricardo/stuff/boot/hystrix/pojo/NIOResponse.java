package br.comar.ricardo.stuff.boot.hystrix.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NIOResponse {
	
	private int expensiveTime;

}
