package com.api.stepdefinition;

import com.api.model.ProductsDTO;
import com.api.utils.JsonReader;
import com.api.utils.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateProductsStepdefinition {

    private TestContext context;
    private static final Logger LOG = LogManager.getLogger(PagerScenariosStepdefinition.class);

    public CreateProductsStepdefinition(TestContext context) {
        this.context = context;
    }

    @When("create a new product using jsonFile")
    public void createNewProductUsingJsonFile(DataTable dataTable) {
        Map<String, String> fileName = dataTable.asMaps().get(0);
        context.response = context.requestSetup().body(JsonReader.getRequestBody(fileName.get("jsonFileName")))
                .when().post(context.session.get("endpoint").toString());
        if (context.response.getStatusCode() == 201) {
            ProductsDTO newProduct = context.response.getBody().as(ProductsDTO.class);
            context.testData.put("newProduct", newProduct);

            LOG.info("New Product Created: " + newProduct);
        }
    }

    @Then("Validate that if the product was {string}")
    public void validateThatTheProductWas(String created) {
        assertEquals(context.response.getStatusCode() == 201, Boolean.valueOf(created));
    }

    @When("get all products having price_min and price_max set on")
    public void getAllProductsHavingPriceMinAndPriceMaxSetOn(DataTable dataTable){
        Map<String, String> queryParams = dataTable.asMaps().get(0);

        context.response = context.requestSetup().queryParams(queryParams)
                .when().get(context.session.get("endpoint").toString());

        ProductsDTO[] products = context.response.getBody().as(ProductsDTO[].class);
        context.testData.put("products", products);

        LOG.info("Get all products number: " + products.length);
    }

    @Then("check if endpoint provide products having price between the given prices {string}, {string}")
    public void checkIfEndpointProvideProductsHavingPriceBetweenGivenPrices(String min, String max){
        ProductsDTO[] products = context.response.getBody().as(ProductsDTO[].class);
        boolean answer = Arrays.stream(products).allMatch(product -> product.price >= Integer.parseInt(min) && product.price <= Integer.parseInt(max));
        assertTrue("Provided prices are not between given prices!", answer);
        LOG.info("Provided prices are between given prices: " + answer);
    }

}
