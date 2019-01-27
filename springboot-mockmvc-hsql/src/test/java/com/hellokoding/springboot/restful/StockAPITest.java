package com.hellokoding.springboot.restful;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class StockAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Test
    public void findAll() throws Exception {
        Stock stock = new Stock();
        stock.setName("Stock 1");
        stock.setPrice(new BigDecimal(1));

        List<Stock> stocks = Arrays.asList(stock);
        given(stockService.findAll()).willReturn(stocks);

        this.mockMvc.perform(get("/api/v1/stocks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(stocks.size())));
    }
}
