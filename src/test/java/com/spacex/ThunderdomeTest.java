package com.spacex;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

public class ThunderdomeTest {
    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("Thunderdome", "localhost", 8080, this);

    @Pact(consumer="SpaceX")
    public RequestResponsePact createFragment(PactDslWithProvider builder) {
        PactDslJsonBody body = new PactDslJsonBody()
            .stringType("Name", "Mel Gibson");

        return builder
            .uponReceiving("Fighter details")
                .path("/fighter/1")
                .method("GET")
            .willRespondWith()
                .status(200)
                .body(body)
            .toPact();
    }

    @Test
    @PactVerification
    public void getsFighter() {
        Thunderdome thunderdome = new Thunderdome();

        Fighter fighter = thunderdome.getFighter();

        assertEquals(fighter.Name, "Mel Gibson");
    }
}
