# spring-boot-hystrix

Simple PoC project to integrate my other project [spring-non-blocking-io](https://github.com/ricardo-comar/spring-non-blocking-io) using [Hystrix](https://github.com/Netflix/Hystrix).

Created using Spring Boot, with Maven 3, Spring Batch and [Actuator](http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html#production-ready-endpoints). 

### Usage
- Follow the instructions on [spring-non-blocking-io](https://github.com/ricardo-comar/spring-non-blocking-io) to install and have an instance running on a second terminal.
- Download the repo
- Package with `mvn package`
- And run with `java -jar target/spring-boot-hystrix-0.0.1-SNAPSHOT.jar`
- Call [the api](http://localhost:8090/api/nio/200/300) to perform the integration and receive a response handled by Hystrix. 


### How it works
- An instance will be online in a few seconds. 
- Every time you call the API, it will handle the request and call the same service from [NIO API](http://localhost:8080/api/nio/async) passing on the same parameters, to emulate a call to the service.
- By default, [Hystrix](https://github.com/Netflix/Hystrix) has a configured timeout to 1s. If the NIO API takes longer than 1s to respond, the circuit breaks the call and returns the fallback response.  
- You can also access the [Hystrix Dashboard](http://localhost:8090/hystrix), informing the stream as `http://localhost:8090/hystrix.stream`, delay and title.

### Under the hood
- TODO



### Gatling Test Script
```Scala
package basic

import io.gatling.core.Predef._  
import io.gatling.http.Predef._  
import io.gatling.jdbc.Predef._  
import scala.concurrent.duration._  

class HystrixSimulation extends Simulation {

  //val rampUpTimeSecs = 60
  //val testTimeSecs   = 360

  val rampUpTimeSecs = 5
  val testTimeSecs   = 30
  val noOfUsers      = 300
  val minWaitMs      = 1000 milliseconds
  val maxWaitMs      = 3000 milliseconds

  val baseURL      = "http://localhost:8090"
  val baseName     = "spring-hystrix"
  val requestName  = baseName + "-request"
  val scenarioName = baseName + "-scenario"
  val URI          = "/api/nio/400/600"

  val httpConf = http.baseURL(baseURL)

  val scn = scenario(scenarioName)
    .during(testTimeSecs) {
      exec(
        http(requestName)
          .get(URI)
          .check(status.is(200))
      )
      .pause(minWaitMs, maxWaitMs)
    }

  setUp(
    scn.inject(rampUsers(noOfUsers) over (rampUpTimeSecs))
    ).protocols(httpConf)
}
```