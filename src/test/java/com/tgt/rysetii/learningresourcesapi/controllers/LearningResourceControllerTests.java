package com.tgt.rysetii.learningresourcesapi.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgt.rysetii.learningresourcesapi.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapi.entity.LearningResourceStatus;
import com.tgt.rysetii.learningresourcesapi.service.LearningResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(LearningResourceController.class)
public class LearningResourceControllerTests {

    @MockBean
    private LearningResourceService learningResourceService;

    /**
     *  Spring Boot automatically provides beans like ObjectMapper instance to map to and from JSON,
     *  MockMvc instance to simulate HTTP requests, which we can autowire from the application context
     * */
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllTheAvailableLearningResources() throws Exception {
        List<LearningResource> learningResources = new ArrayList<>();
        LearningResource learningResource1 = new LearningResource(1311, "Test Name 1", 100.0, 120.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(5), LocalDate.now().plusYears(2));
        LearningResource learningResource2 = new LearningResource(1312, "Test Name 2", 120.0, 180.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        learningResources.add(learningResource1);
        learningResources.add(learningResource2);

        String expectedResponse = objectMapper.writeValueAsString(learningResources);

        when(learningResourceService.getLearningResources()).thenReturn(learningResources);

      //  MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/learningresources/v1/"))
           //     .andExpect(status().isOk())
            //    .andReturn();

      //  assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }



}
