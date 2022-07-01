/*
 * Copyright 2011-2022 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cmd.hms.patient.test;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import java.time.Duration;

public class PatientSimulation extends Simulation {

  HttpProtocolBuilder httpProtocol =
      http
          // Here is the root for all relative URLs
          .baseUrl("http://35.225.27.133:8090/odata")
          // Here are the common headers
          .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
          .doNotTrackHeader("1")
          .acceptLanguageHeader("en-US,en;q=0.5")
          .acceptEncodingHeader("gzip, deflate")
          .userAgentHeader(
              "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0");

  // A scenario is a chain of requests and pauses
  ScenarioBuilder scn =
      scenario("Scenario Name")
          .exec(http("request_1").get("/"))
          // Note that Gatling has recorded real time pauses
          .pause(7)
          .exec(http("request_2").get("/Genders(1)"))
          .pause(2)
          .exec(http("request_3").get("/Genders(2)"))
          .pause(3)
          .exec(http("request_4").get("/Contacts(1)"))
          .pause(2)
          .exec(http("request_5").get("/Addresss(1)"))
          .pause(Duration.ofMillis(670))
          .exec(http("request_6").get("/Contacts"))
          .pause(Duration.ofMillis(629))
          .exec(http("request_7").get("/Addresss"))
          .pause(Duration.ofMillis(734))
          .exec(http("request_8").get("/Titles"))
          .pause(5)
          .exec(http("request_9").get("/$metadata"))
          .pause(1)
          .exec(
              http("request_10")
                  // Here's an example of a POST request
                  .post("/Genders")
                  .header("Content-Type", "application/json")
                  .body(StringBody("{\n  \"Title\": \"Something\" \n}"))
                  .check(status().is(201))
          );

  {
    //setUp(scn.injectOpen(atOnceUsers(1)).protocols(httpProtocol));
    setUp(scn.injectOpen(constantUsersPerSec(50).during(Duration.ofSeconds(15)))).protocols(httpProtocol);;
  }
}