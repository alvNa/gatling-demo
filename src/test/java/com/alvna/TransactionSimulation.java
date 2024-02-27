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

    {
        var httpProtocol = createHttpProtocol();
        var scenario = createScenario();
        setUpSimulation(httpProtocol, scenario);
    }

    private HttpProtocolBuilder createHttpProtocol(){
        return http
                .baseUrl(urlBase)
                .acceptHeader("application/json")
                .contentTypeHeader("application/json")
                .check(status().is(200));
    }

    private ScenarioBuilder createScenario() {
        return scenario("Transaction Scenario")
                .exec(http("Get transactions")
                        .get("/accounts/12345/transactions"))
                .pause(interval)
                .exec(http("Post transaction")
                        .post("/accounts/67890/transactions"))
                .pause(interval);
    }

    private void setUpSimulation(HttpProtocolBuilder httpProtocol, ScenarioBuilder scn) {
        setUp(scn.injectOpen(atOnceUsers(users)))
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lt(600),
                        global().successfulRequests().percent().gt(95.0),
                        global().responseTime().max().lt(5000),
                        global().responseTime().mean().lt(20),
                        global().responseTime().percentile1().lt(20),
                        global().responseTime().percentile2().lt(30),
                        global().responseTime().percentile3().lt(40),
                        global().responseTime().percentile4().lt(2000)
                );
    }

}
