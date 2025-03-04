package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }


    // new content added by Rivka Sagi

    @Test //if the first parameter has more digits than the second parameter
        public void postParameter2() throws Exception {
        this.mvc.perform(post("/").param("operand1","1000111011").param("operator", "+").param("operand2", "111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1001000010"))
                .andExpect(model().attribute("operand1", "1000111011"));
    }

    @Test //if the first operand has leading 0s in a binary addition post
        public void postParameter3() throws Exception {
        this.mvc.perform(post("/").param("operand1","00011").param("operator", "+").param("operand2", "1001"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1100"))
                .andExpect(model().attribute("operand1", "00011"));
    }

    @Test //if the first operand is empty
        public void postEmptyParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","").param("operator","+").param("operand2","10011"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "10011"))
                .andExpect(model().attribute("operand1",""));
    }

    // 3 tests for the multiply function

    @Test //if the first parameter has more digits than the second parameter
    public void postMult1() throws Exception {
        this.mvc.perform(post("/").param("operand1","100011").param("operator", "*").param("operand2", "111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "11110101"))
                .andExpect(model().attribute("operand1", "100011"));
    }
    @Test //if the first operand is empty
    public void postMult2() throws Exception {
        this.mvc.perform(post("/").param("operand1","").param("operator","*").param("operand2","10011"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "0"))
                .andExpect(model().attribute("operand1",""));
    }
    @Test //if the first binary number has leading 0s
    public void postMult3() throws Exception {
        this.mvc.perform(post("/").param("operand1","00011").param("operator","*").param("operand2","1001"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "11011"))
                .andExpect(model().attribute("operand1","00011"));
    }


    // 3 tests for the AND function

    @Test //if the first parameter has more digits than the second parameter
    public void postAND1() throws Exception {
        this.mvc.perform(post("/").param("operand1","100011").param("operator", "&").param("operand2", "111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "11"))
                .andExpect(model().attribute("operand1", "100011"));
    }
    @Test //if the first operand is empty
    public void postAND2() throws Exception {
        this.mvc.perform(post("/").param("operand1","").param("operator","&").param("operand2","10011"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "0"))
                .andExpect(model().attribute("operand1",""));
    }
    @Test //if the first binary number has leading 0s
    public void postAND3() throws Exception {
        this.mvc.perform(post("/").param("operand1","00011").param("operator","&").param("operand2","1001"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1"))
                .andExpect(model().attribute("operand1","00011"));
    }


    // 3 tests for the OR function

    @Test //if the first parameter has more digits than the second parameter
    public void postOR1() throws Exception {
        this.mvc.perform(post("/").param("operand1","100011").param("operator", "|").param("operand2", "111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "100111"))
                .andExpect(model().attribute("operand1", "100011"));
    }
    @Test //if the first operand is empty
    public void postOR2() throws Exception {
        this.mvc.perform(post("/").param("operand1","").param("operator","|").param("operand2","10011"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "10011"))
                .andExpect(model().attribute("operand1",""));
    }
    @Test //if the first binary number has leading 0s
    public void postOR3() throws Exception {
        this.mvc.perform(post("/").param("operand1","00011").param("operator","|").param("operand2","1001"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1011"))
                .andExpect(model().attribute("operand1","00011"));
    }
}