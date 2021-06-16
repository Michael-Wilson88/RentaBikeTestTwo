package com.example.RentaBikeTestTwo.integration;


import com.example.RentaBikeTestTwo.controller.BikeController;
import com.example.RentaBikeTestTwo.domain.Bike;
import com.example.RentaBikeTestTwo.payload.request.BikeRequest;
import com.example.RentaBikeTestTwo.repository.BikeRepository;
import com.example.RentaBikeTestTwo.service.BikeService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(BikeController.class)

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@ActiveProfiles("test")
public class BikeControllerIntegrationTest {


//    @MockBean
//    BikeService bikeService;
//    @MockBean
//    BikeRequest bikeRequest;
//    @MockBean
//    private BikeRepository bikeRepository;

//    @MockBean
//    AuthorizationService authorizationService;
//    @MockBean
//    UserDetailsImpl userDetails;
//    @MockBean
//    UserDetailsServiceImpl userDetailsService;
//    @MockBean
//    WebSecurityConfig webSecurityConfig;

    @Autowired
    BikeController bikeController;



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
    void whenPostRequestToAddInvalidBike_thenBadRequestResponse() throws Exception {

        String bike = "{\"id\":\"1\",\"bikeNumber\":\"E2\", \"brand\":\"Gazelle\",\"frameNumber\":\"HA1234568\",\"retailPrice\":\"1200\",\"basePrice\":\"20.0\",\"electric\",\"true\"}";



//                "{" +
//                "   \" bikeNumber\" : \"\"," +
//                "   \" brand\" : \"Gazelle\"," +
//                "   \" frameNumber\" : \"HA1234568\"," +
//                "   \" retailPrice\" : \"1200\"," +
//                "   \" basePrice\" : \"20.0\"," +
//                "   \" electric\" : \"true\"," +
//                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/createbike")
                .content(bike)
                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.bikeNumber", Is.is( "Bike number is mandatory.")))
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
    }



}
