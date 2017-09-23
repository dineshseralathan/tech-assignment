package com.ds.springboot.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ds.springboot.web.GreetingController;

@RunWith(SpringRunner.class)
public class GreetingControllerTest {
	

    private MockMvc mockMvc;
  

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new GreetingController()).build();
    }

    @Test
    public void testGETMethodForStatus() throws Exception {
        this.mockMvc.perform(get("/greetings").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                

    }
    @Test
    public void testGETMethodForContentType() throws Exception {
        this.mockMvc.perform(get("/greetings").accept(MediaType.APPLICATION_JSON))
                    .andExpect(content().contentType("application/json;charset=ISO-8859-1"));

    }
     
    @Test
    public void testGETMethodContentTypeCompatibleWithJSON() throws Exception {
        this.mockMvc.perform(get("/greetings").accept(MediaType.APPLICATION_JSON))
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }
    
    @Test
    public void testGETMethodForContent() throws Exception {
        this.mockMvc.perform(get("/greetings").accept(MediaType.APPLICATION_JSON))
                              .andExpect(content().string("Hello World"));

    }

}
