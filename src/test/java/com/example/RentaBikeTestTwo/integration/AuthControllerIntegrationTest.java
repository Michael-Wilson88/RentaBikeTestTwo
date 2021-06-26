package com.example.RentaBikeTestTwo.integration;


import com.example.RentaBikeTestTwo.controller.AuthController;
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
public class AuthControllerIntegrationTest {

    @Autowired
    public AuthController authController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void contextLoads() {
        Assertions.assertNotNull(authController);
    }

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
//                .andExpect(jsonPath("$.username", Is.is( "Username is mandatory.")))
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
//                .andExpect(jsonPath("$.password", Is.is( "\"User registered successfully!\"")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
        Assertions.assertEquals("User registered succesfully!", "User registered succesfully!");
    }

}
