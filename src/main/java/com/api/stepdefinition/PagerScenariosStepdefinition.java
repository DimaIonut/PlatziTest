package com.api.stepdefinition;

import com.api.model.ProductsDTO;
import com.api.utils.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PagerScenariosStepdefinition {
    private TestContext context;
    private static final Logger LOG = LogManager.getLogger(PagerScenariosStepdefinition.class);

    public PagerScenariosStepdefinition(TestContext context) {
        this.context = context;
    }

    @Given("access to endpoint {string}")
    public void accessToEndpoint(String endpoint) {
        context.session.put("endpoint", endpoint);
    }

    @When("get all products having offset and limit set on")
    public void getAllProductsByOffSetAndLimit(DataTable dataTable) {
        Map<String, String> queryParams = dataTable.asMaps().get(0);

        context.response = context.requestSetup().queryParams(queryParams)
                .when().get(context.session.get("endpoint").toString());

        ProductsDTO[] products = context.response.getBody().as(ProductsDTO[].class);
        context.testData.put("products", products);

        LOG.info("Get all products number: " + products.length);
    }


    @Then("check if endpoint provide the expected amount of {string}")
    public void checkIfEndpointProvideTheExpectedAmount(String limit) {
        assertEquals("Provided amount is not match!", Integer.valueOf(limit), Integer.valueOf(context.response.getBody().as(ProductsDTO[].class).length));
        LOG.info("Get all products number: " + limit + ":" + context.response.getBody().as(ProductsDTO[].class).length);
    }

    @When("get all products")
    public void getAllProducts() {

        context.response = context.requestSetup()
                .when().get(context.session.get("endpoint").toString());

        ProductsDTO[] products = context.response.getBody().as(ProductsDTO[].class);
        context.testData.put("products", products);

        LOG.info("Get all products number: " + products.length);
    }

    @Then("validate that all products have price larger than {string}")
    public void validateIfAllProductsHavePriceLargerThan(String number) {
        ProductsDTO[] products = context.response.getBody().as(ProductsDTO[].class);
        boolean answer = Arrays.stream(products).allMatch(product -> product.price > Integer.parseInt(number));
        assertTrue("Provided prices is not larger then 0!", answer);
        LOG.info("Provided prices has prices larger then 0: " + answer);
    }

    @Then("validate that the pager parameters work accurately using {string}")
    public void validateThatPagerParameteresWorkAccurately(String id) {
        ProductsDTO[] products = context.response.getBody().as(ProductsDTO[].class);
        assertEquals("Provided id is not match!", Integer.valueOf(id), Integer.valueOf(products[0].id));
    }
}
