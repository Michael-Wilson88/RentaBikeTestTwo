package com.example.RentaBikeTestTwo.integration;

import com.example.RentaBikeTestTwo.controller.CustomerController;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class CustomerControllerIntegrationTest {

    @Autowired
    public CustomerController customerController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(customerController);
    }

    @Test
    void whenRequestToGetAllCustomers_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void whenPostRequestToAddEmptyFirstName_thenBadRequestResponse() throws Exception {

        String customer = "{" +
                "   \"firstName\" : \"\"," +
                "   \"lastName\" : \"van Dam\"," +
                "   \"age\" : \"60\"," +
                "   \"phoneNumber\" : \"0622334455\"," +
                "   \"emailAddress\" : \"kees@RentaBike.com\"," +
                "   \"passportNumber\" :  \"HA72419BZ\"," +
                "   \"country\" : \"Netherlands\"," +
                "   \"address\" : \"HavenStraat 22\" " +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/createcustomer")
                .content(customer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.firstName", Is.is( "First name is mandatory.")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
    }

    @Test
    void whenPostRequestToAddEmptyLastName_thenBadRequestResponse() throws Exception {

        String customer = "{" +
                "   \"firstName\" : \"Kees\"," +
                "   \"lastName\" : \"\"," +
                "   \"age\" : \"60\"," +
                "   \"phoneNumber\" : \"0622334455\"," +
                "   \"emailAddress\" : \"kees@RentaBike.com\"," +
                "   \"passportNumber\" :  \"HA72419BZ\"," +
                "   \"country\" : \"Netherlands\"," +
                "   \"address\" : \"HavenStraat 22\" " +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/createcustomer")
                .content(customer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.lastName", Is.is( "Last name is mandatory.")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
    }

    @Test
    void whenPostRequestToAddEmptyPassportNumber_thenBadRequestResponse() throws Exception {

        String customer = "{" +
                "   \"firstName\" : \"Kees\"," +
                "   \"lastName\" : \"van Dam\"," +
                "   \"age\" : \"60\"," +
                "   \"phoneNumber\" : \"0622334455\"," +
                "   \"emailAddress\" : \"kees@RentaBike.com\"," +
                "   \"passportNumber\" :  \"\"," +
                "   \"country\" : \"Netherlands\"," +
                "   \"address\" : \"HavenStraat 22\" " +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/createcustomer")
                .content(customer)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.passportNumber", Is.is( "Passport number is mandatory.")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
    }
}
