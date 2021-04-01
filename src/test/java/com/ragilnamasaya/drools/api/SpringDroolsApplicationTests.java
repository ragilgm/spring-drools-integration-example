package com.ragilnamasaya.drools.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@EnableWebMvc
@ComponentScan(basePackages = "com.ragilnamasaya.drools.api")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDroolsApplicationTests {


	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void whenCardTypeIsBCA_AndPriceGretherThan10000_ThenReturnDiscountIsTen() throws Exception {
		Order order = new Order();
		order.setCardType("BCA");
		order.setName("ragil");
		order.setPrice(11000);
		mockMvc
				.perform(post("/order")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(order)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.discount", is(10)));
	}
	@Test
	public void whenCardTypeIsBCA_AndPriceLessThan10000_ThenReturnDiscountIsZero() throws Exception {
		Order order = new Order();
		order.setCardType("BCA");
		order.setName("ragil");
		order.setPrice(9000);
		mockMvc
				.perform(post("/order")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(order)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.discount", is(0)));
	}

	@Test
	public void whenCardTypeIsBNI_AndPriceGratherThan15000_ThenReturnDiscountIsZero() throws Exception {
		Order order = new Order();
		order.setCardType("BNI");
		order.setName("ragil");
		order.setPrice(16000);
		mockMvc
				.perform(post("/order")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(order)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.discount", is(8)));
	}

	@Test
	public void whenCardTypeIsBNI_AndPriceLessThan15000_ThenReturnDiscountIsZero() throws Exception {
		Order order = new Order();
		order.setCardType("BNI");
		order.setName("ragil");
		order.setPrice(14000);
		mockMvc
				.perform(post("/order")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(order)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.discount", is(0)));
	}


	@Test
	public void whenCardTypeIsBRI_AndPriceGratherThan15000_ThenReturnDiscountIsZero() throws Exception {
		Order order = new Order();
		order.setCardType("BRI");
		order.setName("ragil");
		order.setPrice(16000);
		mockMvc
				.perform(post("/order")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(order)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.discount", is(15)));
	}

	@Test
	public void whenCardTypeIsBRI_AndPriceLessThan15000_ThenReturnDiscountIsZero() throws Exception {
		Order order = new Order();
		order.setCardType("BRI");
		order.setName("ragil");
		order.setPrice(14000);
		mockMvc
				.perform(post("/order")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(order)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.discount", is(0)));
	}


}
