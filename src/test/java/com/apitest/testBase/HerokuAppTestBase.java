package com.apitest.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerokuAppTestBase {
    protected RequestSpecification spec02;//interface den obje uretemeyiz

    //Her testten once calismasi icin
    @Before
    public void setup() {
        spec02 = new RequestSpecBuilder().//polimorphizim
                setBaseUri("https://restful-booker.herokuapp.com").
                build();
    }
}
