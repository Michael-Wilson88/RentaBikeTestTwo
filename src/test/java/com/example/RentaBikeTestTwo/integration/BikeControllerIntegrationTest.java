package com.example.RentaBikeTestTwo.integration;


import com.example.RentaBikeTestTwo.controller.BikeController;
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
public class BikeControllerIntegrationTest {


    @Autowired
    public BikeController bikeController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void contextLoads() {
        Assertions.assertNotNull(bikeController);
    }

    @Test
    void whenRequestToGetAllBikes_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bikes").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void whenPostRequestToAddEmptyBikeNumber_thenBadRequestResponse() throws Exception {


               String bike = "{" +
                "   \"bikeNumber\" : \"\"," +
                "   \"brand\" : \"Gazelle\"," +
                "   \"frameNumber\" : \"HA1234568\"," +
                "   \"retailPrice\" : 1200," +
                "   \"basePrice\" : 20.0," +
                "   \"electric\" : true" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/createbike")
                .content(bike)
                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.bikeNumber", Is.is( "Bike number is mandatory.")))
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
    }

    @Test
    void whenPostRequestEmptyFrameNumber_thenBadRequestResponse() throws Exception {


        String bike = "{" +
                "   \"bikeNumber\" : \"E1\"," +
                "   \"brand\" : \"Gazelle\"," +
                "   \"frameNumber\" : \"\"," +
                "   \"retailPrice\" : 1200," +
                "   \"basePrice\" : 20.0," +
                "   \"electric\" : true" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/createbike")
                .content(bike)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.frameNumber", Is.is( "Frame number is mandatory.")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
    }

//    @Test
//    void whenPostRequestNoBasePrice_thenBadRequestResponse() throws Exception {
//// TODO: 23-6-2021 wachten op antwoord stack overflow
//
//        String bike = "{" +
//                "   \"bikeNumber\" : \"E1\"," +
//                "   \"brand\" : \"Gazelle\"," +
//                "   \"frameNumber\" : \"HA1234568\"," +
//                "   \"retailPrice\" : 1200," +
//                "   \"basePrice\" : , " +
//                "   \"electric\" : true" +
//                "}";
//        mockMvc.perform(MockMvcRequestBuilders.post("/createbike")
//                .content(bike)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.basePrice", Is.is( "Base price is mandatory.")))
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
//    }

}
