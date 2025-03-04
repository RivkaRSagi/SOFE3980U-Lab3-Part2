package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }
	@Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
			.andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
			.andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }


    // new content added by Rivka Sagi

    @Test //if the first binary number is empty for the data api
    public void add3() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("1010"));
    }

    @Test //if the second binary number is empty for the json api
    public void add4() throws Exception{
        this.mvc.perform(get("/add_json").param("operand1","10011").param("operand2", ""))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(10011))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10011))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    @Test //if the binary numbers have leading 0s
    public void add5() throws Exception{
        this.mvc.perform(get("/add_json").param("operand1", "000111").param("operand2", "001010"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    //test cases for multiplication

    @Test //if the first binary number is empty for the data api
    public void mult1() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test //if the second binary number is empty for the json api
    public void mult2() throws Exception{
        this.mvc.perform(get("/multiply_json").param("operand1","10011").param("operand2", ""))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(10011))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }

    @Test //if the binary numbers have leading 0s
    public void mult3() throws Exception{
        this.mvc.perform(get("/multiply_json").param("operand1", "000111").param("operand2", "001010"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1000110))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }

    //test cases for binary AND

    @Test //if the first binary number is empty for the data api
    public void AND1() throws Exception {
        this.mvc.perform(get("/AND").param("operand1", "").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test //if the second binary number is empty for the json api
    public void AND2() throws Exception{
        this.mvc.perform(get("/AND_json").param("operand1","10011").param("operand2", ""))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(10011))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("AND"));
    }

    @Test //if the binary numbers have leading 0s
    public void AND3() throws Exception{
        this.mvc.perform(get("/AND_json").param("operand1", "000111").param("operand2", "001010"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("AND"));
    }


    //test cases for binary OR

    @Test //if the first binary number is empty for the data api
    public void OR1() throws Exception {
        this.mvc.perform(get("/OR").param("operand1", "").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("1010"));
    }

    @Test //if the second binary number is empty for the json api
    public void OR2() throws Exception{
        this.mvc.perform(get("/OR_json").param("operand1","10011").param("operand2", ""))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(10011))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10011))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("OR"));
    }

    @Test //if the binary numbers have leading 0s
    public void OR3() throws Exception{
        this.mvc.perform(get("/OR_json").param("operand1", "000111").param("operand2", "001010"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("OR"));
    }
}
