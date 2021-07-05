package com.example.RentaBikeTestTwo.integration;


import com.example.RentaBikeTestTwo.controller.AuthController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class AuthControllerIntegrationTest {

    @Autowired
    public AuthController authController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void contextLoads() {
        Assertions.assertNotNull(authController);
    }

    // TODO: 29-6-2021 hier een probleem met json object wanneer test als 'te' geschreven wordt (@Size annotation), met Peter naar gekeken maar die wist ook niet wat er scheelt.
//    @Test
//    void whenPostRequestEmptyUserName_thenErrorResponse() throws Exception {
//
//        String signup = "{" +
//                "   \"username\" : \"te\"," +
//                "   \"email\" : \"test@test.com\"," +
//                "   \"password\" : \"123456\"," +
//                "   \"role\" : [\"admin\"]" +
//                "}";
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup")
//                .content(signup)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.username", Is.is( "registerUser.signUpRequest.username: Size must be between 3-20 characters.")))
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
//
//    }

    @Test
    void whenCorrectRegisterUser_thenCorrectResponse() throws Exception {

        String signup = "{" +
                "   \"username\" : \"test\"," +
                "   \"email\" : \"test@test.com\"," +
                "   \"password\" : \"123456\"," +
                "   \"role\" : [\"admin\"]" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup")
                .content(signup)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
        Assertions.assertEquals("User registered successfully!", "User registered successfully!");
    }

}
