package com.example.RentaBikeTestTwo.integration;

import com.example.RentaBikeTestTwo.controller.RentalController;
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
public class RentalControllerIntegrationTest {

    @Autowired
    public RentalController rentalController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(rentalController);
    }

    @Test
    void whenRequestToGetAllRentals_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rentals")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

//    @Test
//    void whenPostRequestEmptyBikeNumber_thenBadRequestResponse() throws Exception {
//
//        String addBikeRequest = "{" +
//                "   \"bikeNumber\" : \"E1\"," +
//                "   \"beginDate\" : \"23-06-2021\"," +
//                "   \"endDate\" : \"24-06-2021\" " +
//                "}";
//        mockMvc.perform(MockMvcRequestBuilders.post("/rentals/\"id\"/addbike")
//                .content(addBikeRequest)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.bikeNumber", Is.is( "Bike number is mandatory.")))
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
//    }

}
