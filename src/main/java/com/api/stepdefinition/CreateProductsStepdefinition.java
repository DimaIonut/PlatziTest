package com.api.stepdefinition;

import com.api.model.ProductsDTO;
import com.api.utils.JsonReader;
import com.api.utils.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Map;

import static org.junit.Assert.assertEquals;

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

}
