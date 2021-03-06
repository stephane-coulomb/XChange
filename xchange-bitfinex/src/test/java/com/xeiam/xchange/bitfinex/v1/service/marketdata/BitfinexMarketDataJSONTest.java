package com.xeiam.xchange.bitfinex.v1.service.marketdata;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xeiam.xchange.bitfinex.v1.BitfinexAdapters;
import com.xeiam.xchange.bitfinex.v1.dto.marketdata.BitfinexDepth;
import com.xeiam.xchange.bitfinex.v1.dto.marketdata.BitfinexLendDepth;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.Order.OrderType;

public class BitfinexMarketDataJSONTest {

  @Test
  public void testLendbookMarketData() throws IOException {

    InputStream resourceAsStream = BitfinexMarketDataJSONTest.class.getResourceAsStream("/v1/marketdata/example-marketdepth-lendbook-data.json");
    BitfinexLendDepth lendDepth = new ObjectMapper().readValue(resourceAsStream, BitfinexLendDepth.class);

    assertEquals(lendDepth.getAsks().length, 50);
    assertEquals(lendDepth.getBids().length, 50);
  }

  @Test
  public void testMarketDepth() throws Exception {

    InputStream resourceAsStream = BitfinexMarketDataJSONTest.class.getResourceAsStream("/v1/marketdata/example-marketdepth-data.json");
    BitfinexDepth depthRaw = new ObjectMapper().readValue(resourceAsStream, BitfinexDepth.class);
    BitfinexAdapters.OrdersContainer asksOrdersContainer = BitfinexAdapters.adaptOrders(depthRaw.getAsks(), CurrencyPair.BTC_EUR, OrderType.ASK);
    BitfinexAdapters.OrdersContainer bidsOrdersContainer = BitfinexAdapters.adaptOrders(depthRaw.getBids(), CurrencyPair.BTC_EUR, OrderType.BID);

    assertEquals(new BigDecimal("851.87"), asksOrdersContainer.getLimitOrders().get(0).getLimitPrice());
    assertEquals(new BigDecimal("849.59"), bidsOrdersContainer.getLimitOrders().get(0).getLimitPrice());

    assertThat(asksOrdersContainer.getTimestamp()).isEqualTo(1387060992000L);
    assertThat(bidsOrdersContainer.getTimestamp()).isEqualTo(1387060480000L);

  }
}
