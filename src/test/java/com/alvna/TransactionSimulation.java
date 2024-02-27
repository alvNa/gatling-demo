package com.alvna;

import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;


public class TransactionSimulation extends Simulation {
    private String urlBase= "http://localhost:8080";
    private int users = 500;
    private int interval = 5;

    HttpProtocolBuilder httpProtocol = http
            .baseUrl(urlBase)
            .acceptHeader("application/json")
            //.doNotTrackHeader("1")
            //.acceptLanguageHeader("en-US,en;q=0.5")
            //.acceptEncodingHeader("gzip, deflate")
            //.userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")
            ;

    ScenarioBuilder scn;

    {
        scn = scenario("Transaction Scenario")
                .exec(http("Get transactions")
                        .get("/accounts/12345/transactions"))
                .pause(interval)
                .exec(http("Get transactions 2")
                        .get("/accounts/67890/transactions"))
                .pause(interval)
        ;
        setUp(
                scn.injectOpen(atOnceUsers(users))
        ).protocols(httpProtocol);
    }
}
