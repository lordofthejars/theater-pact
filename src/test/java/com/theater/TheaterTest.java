package com.theater;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

public class TheaterTest {
    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("Movie", "localhost", 8080, this);

    @Pact(consumer="Theater")
    public RequestResponsePact createFragment(PactDslWithProvider builder) {
        PactDslJsonBody body = new PactDslJsonBody()
            .stringType("Name", "Avengers: Infinity War");

        return builder
            .uponReceiving("Movie details")
                .path("/movie/1")
                .method("GET")
            .willRespondWith()
                .status(200)
                .body(body)
            .toPact();
    }

    @Test
    @PactVerification
    public void getsFighter() {
        Theater theater = new Theater();

        Movie movie = theater.getMovie();

        assertEquals(movie.Name, "Avengers: Infinity War");
    }
}
