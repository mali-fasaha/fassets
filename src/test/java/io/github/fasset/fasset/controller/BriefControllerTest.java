package io.github.fasset.fasset.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//@WebMvcTest // we needed the entire object graph inorder to interact with entity manager factory
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
public class BriefControllerTest {

    private MockMvc mockMvc;
    @Qualifier("briefController")
    @Autowired
    private BriefController briefController;


    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(briefController).build();
    }

    @Test
    public void getCategoryBriefsTemplateWorks() throws Exception {
        this.mockMvc.perform(
                get("/briefs/categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("briefs/categoryBriefs"))
                .andDo(print());
    }
}